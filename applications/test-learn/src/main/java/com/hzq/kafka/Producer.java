package com.hzq.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Desc TODO
 * @Author Huang
 * @Date 2022/6/22 11:58
 **/
public class Producer {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("bootstrap.servers","192.168.50.50:9092,192.168.50.50:9092,193.168.50.50:9094");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("acks","1");
        properties.put("retries","3");
        properties.put("batch.size","16384");
        properties.put("linger.ms","5");
        properties.put("buffer.memory","33554432");
        properties.put("max.block.ms","3000");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        try {

            for (int i = 0; i < 10; i++) {
                producer.send(new ProducerRecord<String,String>(
                        "mytopic",
                        Integer.toString(i),
                        Integer.toString(i)
                ));
                System.out.println("send:"+i);
            }
        }catch (Exception e){
            System.out.println(e);
            producer.close();
        }
        producer.close();
    }
}
