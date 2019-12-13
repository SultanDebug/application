package com.hzq.rabbitservice.provider;

import com.hzq.rabbitservice.config.RabbitQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Huangzq
 * @title: TestProvider
 * @projectName applications
 * @date 2019/12/13 15:40
 */
@Component
@Slf4j
public class TestProvider implements RabbitTemplate.ConfirmCallback {
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public TestProvider(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this::confirm);
    }

    public void sendMsg(String msg){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitQueue.EXCHANGE_A,RabbitQueue.ROUTINGKEY_A,msg,correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("回调id："+correlationData+"，ack："+ack+"，cause："+cause);
        if(ack){
            log.info("消费成功");
        }else{
            log.info("消费失败："+cause);
        }
    }
}
