package com.hzq.rabbitservice.consumer;

import com.hzq.rabbitservice.config.RabbitQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 * @author Huangzq
 * @title: TestConsumer
 * @projectName applications
 * @date 2019/12/13 15:40
 */
@Component
@RabbitListener(queues = RabbitQueue.QUEUE_A)
@Slf4j
public class TestConsumer /*implements ChannelAwareMessageListener*/ {
    @RabbitHandler
    public void process(String msg) {

        int i = (int) (Math.random()*10);
        if(i%3 == 0){
            log.info("收到A队列消息："+msg);
        }else{
            throw new RuntimeException("测试异常");
        }

    }

    /*@Override
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        Integer consumeTimes = (Integer) headers.get("x-consume-times");
        if (consumeTimes != null) {
            //消费次数+1
            consumeTimes = consumeTimes + 1;
        } else {
            consumeTimes = 1;
        }
        headers.put("x-consume-times", consumeTimes);
        try {
            if(consumeTimes == 1){
                throw new Exception("第一次失败");
            }else{
                String s = new String(message.getBody());
                System.out.println("收到消息："+s);
                channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
            }
        }catch (Exception e){
            channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
        }

    }*/
}
