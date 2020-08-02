package com.hzq.common.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 邮件发送
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-05-15
 */
public class MailSend {
    //levwihpryiujbcjh  --POP3
    //djsxvdaximrkbccj  --IMAP

    public static void main(String[] args) throws MessagingException, InterruptedException {
        String senderUser = "hzqsultan@qq.com";
        String senderPsw = "2013hzqsultan";

        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host","smtp.qq.com");

        properties.setProperty("mail.transport.protocol","smtp");

        properties.setProperty("mail.smtp.auth","true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("hzqsultan@qq.com","levwihpryiujbcjh");
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = session.getTransport();

        //连接服务器
        transport.connect("smtp.qq.com","hzqsultan@qq.com","levwihpryiujbcjh");

        for(int i = 0;i<5;i++){
            //创建邮件对象
            MimeMessage mimeMessage = new MimeMessage(session);

            //邮件发送人
            mimeMessage.setFrom(new InternetAddress("hzqsultan@qq.com"));

            //邮件接收人  2294906182
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress("1131878031@qq.com"));

            //邮件标题
            mimeMessage.setSubject("第"+(i+1)+"封邮件");

            //邮件内容
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mimeMessage.setContent("每隔5秒一封，这是第"+(i+1)+"封邮件，时间是："+sdf.format(new Date()),"text/html;charset=UTF-8");

            //发送邮件
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());

            Thread.sleep(10000);
        }

        //关闭连接
        transport.close();
    }
}
