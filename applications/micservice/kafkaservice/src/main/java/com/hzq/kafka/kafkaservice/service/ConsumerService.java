package com.hzq.kafka.kafkaservice.service;

import com.hzq.kafka.kafkaservice.config.KafkaConstant;
import com.hzq.kafka.kafkaservice.msgbean.MsgBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.Properties;

/**
 * @author Huangzq
 * @title: ConsumerService
 * @projectName applications
 * @date 2019/8/31 16:14
 */
@Component
@Slf4j
public class ConsumerService {
    @KafkaListener(topics = {KafkaConstant.TEST_TOPIC})
    public void consumerMsg(ConsumerRecord<?,?> record){
        Optional<?> msg = Optional.ofNullable(record.value());

        if(msg.isPresent()){
            MsgBean msgBean = (MsgBean) msg.get();

            log.info("<<<<<<<<<<<<<<bean：{}>>>>>>>>>>>>",msgBean);
            log.info("<<<<<<<<<<<<<<optional：{}>>>>>>>>>>>>",msg);
            log.info("<<<<<<<<<<<<<<ConsumerRecord：{}>>>>>>>>>>>>",record);

        }

    }

    /*public static void main(String[] s){
        Properties props = new Properties();
        props.put("bootstrap.servers", "129.204.207.113:9092");
        props.put("group.id", "1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test_topic"));
        while (true) {
            System.out.println("poll start...");
            ConsumerRecords<String, String> records = consumer.poll(100);
            int count = records.count();
            System.out.println("the numbers of topic:" + count);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
        }
    }*/
}
