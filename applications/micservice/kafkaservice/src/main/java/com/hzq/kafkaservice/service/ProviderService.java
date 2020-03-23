package com.hzq.kafkaservice.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzq.kafkaservice.config.KafkaConstant;
import com.hzq.kafkaservice.msgbean.MsgBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * @author Huangzq
 * @title: ProviderService
 * @projectName applications
 * @date 2019/8/31 16:14
 */
@Component
@Slf4j
public class ProviderService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    public void sendMsg(String para) {
        MsgBean msgBean = new MsgBean();
        msgBean.setId(System.currentTimeMillis());
        msgBean.setMsg(UUID.randomUUID().toString() + ":" + para);
        msgBean.setSendTime(new Date());
        kafkaTemplate.send(KafkaConstant.TEST_TOPIC, gson.toJson(msgBean));

        int i = new Double(Math.random() * 3 ).intValue();

        String key = UUID.randomUUID().toString();
//        kafkaTemplate.send(KafkaConstant.TEST_TOPIC,i ,key,gson.toJson(msgBean));

        log.info("<<<<<<<<<<发送完成partition:{},key:{},body:{}>>>>>>>>>>",i,key, msgBean);
    }

    /*private void execMsgSend() throws  Exception{
        Properties props = new Properties();
        props.put("bootstrap.servers", "129.204.207.113:9092");
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> procuder = new KafkaProducer<String, String>(props);

        String topic = "test_topic";
        for (int i = 1; i <= 10; i++) {
            String value = " this is another message_" + i;
            ProducerRecord<String,String> record = new ProducerRecord<String, String>(topic,i+"",value);
            procuder.send(record,new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());
                }
            });
            System.out.println(i+" ----   success");
            Thread.sleep(1000);
        }
        System.out.println("send message over.");
        procuder.close();
    }
    public static void main(String[] args) throws  Exception{
        ProviderService test1 = new ProviderService();
        test1.execMsgSend();
    }*/
}
