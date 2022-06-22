package com.hzq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Desc TODO
 * @Author Huang
 * @Date 2022/6/22 11:58
 **/
public class Comsumer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.50.50:9092,192.168.50.50:9092,193.168.50.50:9094");
        properties.put("group.id","my-consumer-group");
        properties.put("enable.auto.commit","true");
        properties.put("auto.commit.interval.ms","1000");
        properties.put("auto.offset.reset","earliest");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Arrays.asList("mytopic"));
        try {
            while (true){
                ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : poll) {
                    System.out.println(
                            String.format("offset=%s,key=%s,value=%s,partition=%s",record.offset(),record.key(),record.value(),record.partition())
                    );
                }
            }
        }catch (Exception e){
            System.out.println(e);
            consumer.close();
        }
        consumer.close();
    }
}
