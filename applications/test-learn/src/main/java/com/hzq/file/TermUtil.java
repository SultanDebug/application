package com.hzq.file;

import java.security.MessageDigest;

/**
 * @author Huangzq
 * @description
 * @date 2022/11/3 17:03
 */
public class TermUtil {
    public static final long signatureTerm(String s) {
        try {
            byte[] btInput = s.getBytes("utf-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            byte[] reduceMd = new byte[8];
            int j = 0;

            for(int step = 2; j < md.length; j += step) {
                reduceMd[j / 2] = (byte)(md[j] + md[j + 1]);
            }

            j = reduceMd.length;
            long retNumber = 0L;

            for(int i = 0; i < j; ++i) {
                long oneByte = (long)reduceMd[i];
                int bitNumber = i * 8;
                retNumber += oneByte << bitNumber;
            }

            if (retNumber < 0L) {
                retNumber = 0L - retNumber;
            }

            return retNumber;
        } catch (Exception var12) {
            var12.printStackTrace();
            return 0L;
        }
    }

    public static void main(String[] args) {
        String[] strs = "1,测试,hzq,#$@!,我是中国人我爱中国".split(",");

        for(int i = 0; i < strs.length; ++i) {
            System.out.println(strs[i] + ":" + signatureTerm(strs[i]));
        }
    }
}
