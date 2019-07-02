package com.hzq.demoservice.service.producer;

import com.hzq.demoservice.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

import java.util.UUID;

/**
 * @author Huangzq
 * @title: MsgProducer
 * @projectName applications
 * @date 2019/6/10 11:53
 */
@Component
@Slf4j
public class MsgProducer implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback(this::confirm);
    }

    public void sendMsg(String msg){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE_A,RabbitMqConfig.ROUTINGKEY_A,msg,correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("回调id："+correlationData);
        if(ack){
            log.info("消费成功");
        }else{
            log.info("消费失败："+cause);
        }
    }
}
