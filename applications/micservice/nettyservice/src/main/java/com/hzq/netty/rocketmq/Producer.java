package com.hzq.netty.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.UUID;

/**
 * @author Huangzq
 * @title: Producer
 * @projectName applications
 * @date 2020/4/22 17:16
 */
public class Producer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        TransactionMQProducer mqProducer = new TransactionMQProducer("my_producer");
        mqProducer.setNamesrvAddr("192.168.1.45:9876");

        mqProducer.setTransactionListener(new TxListener());

        mqProducer.start();
        for (int i = 0; i < 20; i++) {

            Message message = new Message("my_topic","my_tag", UUID.randomUUID().toString()+"_"+i,("message-"+i).getBytes());
            mqProducer.sendMessageInTransaction(message,message.getKeys());

            Thread.sleep(1000);
        }

//        mqProducer.shutdown();
    }
}
