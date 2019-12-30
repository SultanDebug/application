package com.hzq.rabbitservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author Huangzq
 * @title: RabbitQueue
 * @projectName applications
 * @date 2019/12/13 15:37
 */
@Component
public class RabbitQueue {
    public static final String EXCHANGE_A = "exchange_A";
    public static final String EXCHANGE_B = "exchange_B";
    public static final String EXCHANGE_C = "exchange_C";


    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    public static final String ROUTINGKEY_A = "routingKey_A";
    public static final String ROUTINGKEY_B = "routingKey_B";
    public static final String ROUTINGKEY_C = "routingKey_C";

    /******************默认队列*********************/

    @Bean
    public DirectExchange defaultExchange(){
        return new DirectExchange(EXCHANGE_A);
    }

    @Bean
    public Queue defaultQueue(){
        return new Queue(QUEUE_A,true);
    }

    @Bean
    public Binding defaultBinding(){
        return BindingBuilder.bind(defaultQueue()).to(defaultExchange()).with(ROUTINGKEY_A);
    }



    /******************默认队列*********************/

    /******************示例队列*********************/

    @Bean
    public DirectExchange demoExchange(){
        return new DirectExchange(EXCHANGE_A);
    }

    @Bean
    public Queue demoQueue(){
        return new Queue(QUEUE_B,true);
    }

    @Bean
    public Binding demoBinding(){
        return BindingBuilder.bind(demoQueue()).to(demoExchange()).with(ROUTINGKEY_A);
    }

    /******************示例队列*********************/
}
