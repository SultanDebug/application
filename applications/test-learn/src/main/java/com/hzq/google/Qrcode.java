package com.hzq.google;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/23 09:22
 */
public class Qrcode {
    public static void main(String[] args) {
        String str = "我是中国人 我爱中国";
//        testGraph();

//        drawQrcode(toBinaryArray(str));

        /*String[] decodeCharArr = decodeCharArr();
        for (int i = 0; i < decodeCharArr.length; i++) {
            System.out.println(decodeCharArr[i]);
        }*/

        String bin = "0110001000010001";
        int i = Integer.parseInt(bin,2);
        char c = (char) i;
        System.out.println(c);


    }

    public static void testGraph(){
        BufferedImage bufferedImage = new BufferedImage(200,200,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = (Graphics2D) bufferedImage.getGraphics();
        graphic.setColor(Color.WHITE);
        graphic.fillRect(0,0,200,200);

        for (int i = 0; i < 200; i++) {
            if(i%2==0){
                graphic.setColor(Color.BLACK);
            }else{
                graphic.setColor(Color.WHITE);
            }
            graphic.fillRect(0,i,200,1);
        }

        /*graphic.setColor(Color.BLACK);
        graphic.fillRect(0,0,100,100);

        graphic.setColor(Color.BLUE);
        graphic.fillRect(0,100,100,100);

        graphic.setColor(Color.GRAY);
        graphic.fillRect(100,0,100,100);

        graphic.setColor(Color.GREEN);
        graphic.fillRect(100,100,100,100);*/


        try {
            ImageIO.write(bufferedImage,"JPEG",new FileOutputStream("D:\\draw-qrcode.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int width = 320;
    static int heigh = 200;
    static int wp = width/16;
    static int hp ;

    public static String[] toBinaryArray(String source){
        char[] chars = source.toCharArray();
        hp = heigh/source.length();
        int rowSize = source.length() * hp;
        String [] binArr = new String[rowSize];

        int i = 0;
        for (char aChar : chars) {
            String s = Integer.toBinaryString(aChar);
            while(s.length()<16){
                s = "0"+s;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (char c : s.toCharArray()) {
                for (int j = 0; j < wp; j++) {
                    stringBuilder.append(c);
                }
            }

            /*while(s.length()<16){
                s = "0"+s;
            }*/

            for (int j = 0; j < hp; j++) {
                int row = i*hp+j;
                binArr[row] = stringBuilder.toString();
            }
            i++;

//            binArr[i++] = stringBuilder.toString();
        }



        for (String s : binArr) {
            System.out.println(s);
        }

        return binArr;
    }

    public static void drawQrcode(String [] binArr){
        BufferedImage bufferedImage = new BufferedImage(width,heigh,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphic = (Graphics2D) bufferedImage.getGraphics();
        graphic.setColor(Color.WHITE);
        graphic.fillRect(0,0,width,heigh);
        for (int i = 0; i < binArr.length; i++) {
            String s = binArr[i];
            char[] chars = s.toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char ch = chars[j];

                if(ch == '0'){
                    graphic.setColor(Color.WHITE);
                }else{
                    graphic.setColor(Color.BLACK);
                }
                graphic.fillRect(j,i,1,1);
            }
        }

        try {
            ImageIO.write(bufferedImage,"JPEG",new FileOutputStream("D:\\draw-qrcode.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    public static String[] decodeCharArr(){
        String path = "D:\\draw-qrcode.jpg";
        try {
            BufferedImage read = ImageIO.read(new FileInputStream(path));

            int w = read.getWidth();
            int h = read.getHeight();

            String[] res = new String[h];

            for (int i = 0; i < h; i++) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < w; j++) {
                    Color color = new Color(read.getRGB(j,i));
                    int gray = (color.getRed() + color.getBlue() + color.getGreen())/3;
                    if(gray>150){
                        builder.append("0");
                    }else{
                        builder.append("1");
                    }
                }
                res[i] = builder.toString();
            }

            return res;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
