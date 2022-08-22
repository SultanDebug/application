package com.hzq.util;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;

import java.io.IOException;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/22 15:06
 */
public class GoogleAuthenticatorTest {
    public static void main(String[] args) throws ChecksumException, NotFoundException, IOException, FormatException {
        //生成密码
        /*String s = GoogleAuthenticator.genSecret();
        System.out.println(s);*/
//        System.out.println("二维码地址："+GoogleAuthenticator.genQrcode());

        //解析二维码
        String s = QrcodeUtil.decodeQRCodeImage();
        System.out.println(s);

        //认证
//        Boolean testuser = GoogleAuthenticator.authcode("757252", "IZT7QJTHNCHW7PMV");
//        Boolean testuser = GoogleAuthenticator.authcode("392407", "IZT7QJTHNCHW7PMV");
//        System.out.println(testuser);
    }
}
