package com.hzq.demoservice.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * rabbitmq配置
 * @author Huangzq
 * @title: RabbitMqConfig
 * @projectName applications
 * @date 2019/6/10 11:04
 */
@Slf4j
//@Configuration
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class RabbitMqConfig {

    private String host;

    private int port;

    private String username;

    private String password;


    public static final String EXCHANGE_A = "exchange_A";
    public static final String EXCHANGE_B = "exchange_B";
    public static final String EXCHANGE_C = "exchange_C";


    public static final String QUEUE_A = "QUEUE_A";
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    public static final String ROUTINGKEY_A = "routingKey_A";
    public static final String ROUTINGKEY_B = "routingKey_B";
    public static final String ROUTINGKEY_C = "routingKey_C";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

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

    /*@Bean
    public DirectExchange demoExchange(){
        return new DirectExchange(EXCHANGE_B);
    }

    @Bean
    public Queue demoQueue(){
        return new Queue(QUEUE_B,true);
    }

    @Bean
    public Binding demoBinding(){
        return BindingBuilder.bind(demoQueue()).to(demoExchange()).with(ROUTINGKEY_B);
    }*/

    /******************示例队列*********************/


}
