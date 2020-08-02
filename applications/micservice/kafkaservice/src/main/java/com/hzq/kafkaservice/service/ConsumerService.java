package com.hzq.kafkaservice.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hzq.kafkaservice.config.KafkaConstant;
import com.hzq.kafkaservice.msgbean.MsgBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 消息消费
 * @author Huangzq
 * @title: ConsumerService
 * @projectNme applications
 * @date 2019/8/31 16:14
 */
@Component
@Slf4j
public class ConsumerService {

    private Gson gson = new GsonBuilder().create();

    /*@KafkaListener(topics = {KafkaConstant.TEST_TOPIC}
            ,topicPartitions = {@TopicPartition(topic = KafkaConstant.TEST_TOPIC,partitions = {"0","1","2"})}
            ,groupId = "group1",concurrency = "3")*/
    @KafkaListener(topics = {KafkaConstant.TEST_TOPIC})
    public void consumerMsg0(ConsumerRecord<?,?> record){
        Optional<?> msg = Optional.ofNullable(record.value());

        if(msg.isPresent()){
            MsgBean msgBean = gson.fromJson ((String) msg.get(),MsgBean.class) ;
            log.info("<<<<<<<<<<<<<<consumerMsg0：{}>>>>>>>>>>>>",record);

        }

    }

    /*@KafkaListener(topics = {KafkaConstant.TEST_TOPIC}
            ,topicPartitions = {@TopicPartition(topic = KafkaConstant.TEST_TOPIC,partitions = {"1"})},groupId = "group2")
    public void consumerMsg1(ConsumerRecord<?,?> record){
        Optional<?> msg = Optional.ofNullable(record.value());

        if(msg.isPresent()){
            MsgBean msgBean = gson.fromJson ((String) msg.get(),MsgBean.class) ;
            log.info("<<<<<<<<<<<<<<consumerMsg1：{}>>>>>>>>>>>>",record);

        }

    }*/

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
