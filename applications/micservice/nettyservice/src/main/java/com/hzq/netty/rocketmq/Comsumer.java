package com.hzq.netty.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author Huangzq
 * @title: Comsumer
 * @projectName applications
 * @date 2020/4/22 17:17
 */
public class Comsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my_comsumer");
        consumer.setNamesrvAddr("192.168.1.45:9876");

        consumer.subscribe("my_topic","*");

        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {

                list.stream().forEach(o->{
                    System.out.println(o.getKeys()+"***"+new String(o.getBody()));
                });

                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        consumer.start();

    }
}
