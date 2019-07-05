package com.hzq.demoservice.service.rabbitmq;

import com.hzq.demoservice.config.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Huangzq
 * @title: MsgReceiver
 * @projectName applications
 * @date 2019/6/10 11:54
 */
@Component
@RabbitListener(queues = RabbitMqConfig.QUEUE_A)
@Slf4j
public class MsgReceiver {

    @RabbitHandler
    public void process(String msg){
        log.info("收到B队列消息："+msg);
    }

}
