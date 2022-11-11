package com.hzq.file;

import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.json.JSONUtil;
import com.xiaoleilu.hutool.lang.Tuple;
import javafx.geometry.Pos;
import javafx.util.Pair;
import lombok.Data;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huangzq
 * @description
 * @date 2022/10/26 14:59
 */
public class FileUtil {

    @Data
    static public class Position{
        private int docId;
        private short fieldId;
        private short offset;

        public Position(int docId, short fieldId, short offset) {
            this.docId = docId;
            this.fieldId = fieldId;
            this.offset = offset;
        }
    }

    static long position = 0L;

    public static void writeData(Long term , List<Position> pos){
        String filePath = "C:\\Users\\zhenqiang.huang\\Desktop\\searchprocess\\pydata\\binary_data.mf";
        try {
            RandomAccessFile raf = new RandomAccessFile(filePath, "rw");
            FileChannel fc = raf.getChannel();
            long posTmp = 12 + (8L * pos.size());
            long size = posTmp * 8;
            MappedByteBuffer mbf = fc.map(FileChannel.MapMode.READ_WRITE, position, posTmp);

            /*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(data.getBytes(StandardCharsets.UTF_8));*/

            int count = pos.size();
            mbf.putLong(term);
            mbf.putInt(count);
            for (Position po : pos) {
                int docId = po.getDocId();
                short fieldId = po.getFieldId();
                short offset = po.getOffset();
                mbf.putInt(docId);
                mbf.putShort(fieldId);
                mbf.putShort(offset);
            }
            position += posTmp;
            raf.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readData(){
        String filePath = "C:\\Users\\zhenqiang.huang\\Desktop\\searchprocess\\pydata\\binary_data.mf";
        try {

            /*String line = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8.name());
            System.out.println(line);*/

            RandomAccessFile raf = new RandomAccessFile(filePath, "r");
            FileChannel fc = raf.getChannel();
            MappedByteBuffer mbf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

            Map<Long,List<Position>> map = new HashMap<>();
            while(mbf.hasRemaining()){
                Long term = mbf.getLong();
                int count = mbf.getInt();
                List<Position> list = new ArrayList<>();
                for (int i1 = 0; i1 < count; i1++) {
                    int docId = mbf.getInt();
                    short fieldId = mbf.getShort();
                    short offset = mbf.getShort();
                    Position pos = new Position(docId,fieldId,offset);
                    list.add(pos);
                }
                map.put(term,list);
            }

            System.out.println(JSONUtil.toJsonStr(map));

            raf.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        writeData(1L, Lists.newArrayList(new Position(1,(short)1,(short)2),new Position(2,(short) 3,(short) 4)));
        writeData(2L, Lists.newArrayList(new Position(3,(short)5,(short)6),new Position(4,(short) 7,(short) 8)));

        readData();

    }
}
