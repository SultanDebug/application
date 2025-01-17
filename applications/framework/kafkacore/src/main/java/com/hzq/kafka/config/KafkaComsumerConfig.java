package com.hzq.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * kafka配置类
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-21
 */
//@Component
public class KafkaComsumerConfig {

    private static final Logger log= LoggerFactory.getLogger(KafkaComsumerConfig.class);

    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.50.50:9092");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        //一次拉取消息数量
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "5");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

//    @Bean("batchContainerFactory")
    public ConcurrentKafkaListenerContainerFactory listenerContainer() {
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps()));
        //设置并发量，小于或等于Topic的分区数
        container.setConcurrency(3);
        //设置为批量监听
        container.setBatchListener(true);
        return container;
    }

    /*@Bean
    public NewTopic batchTopic() {
        return new NewTopic("mytopic.a", 3, (short) 3);
    }*/


//    @KafkaListener(id = "batch",clientIdPrefix = "batch",topics = {"mytopic.a"},containerFactory = "batchContainerFactory")
    public void batchListener(List<String> data) {
        log.info("topic.quick.batch  receive : ");
        for (String s : data) {
            log.info("核心消费者：{}",s);
        }
    }
}
