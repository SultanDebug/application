package com.hzq.democlient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author Huangzq
 * @title: TestConsumer
 * @projectName applications
 * @date 2019/12/13 15:40
 */
@Component
@RabbitListener(queues = "QUEUE_B")
@Slf4j
public class TestConsumer {
    @RabbitHandler
    public void process(String msg){
        log.info("收到B队列消息："+msg);
    }
}
