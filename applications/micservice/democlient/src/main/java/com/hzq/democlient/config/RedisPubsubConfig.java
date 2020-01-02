package com.hzq.democlient.config;

import com.hzq.democlient.utils.RedisSerialUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @author Huangzq
 * @title: RedisPubsubConfig
 * @projectName qs-saas
 * @date 2019/12/31 10:45
 */
@Configuration
public class RedisPubsubConfig {
    @Bean
    public RedisMessageListenerContainer getContain(RedisConnectionFactory redisConnectionFactory,
                                                    MessageListener listener){

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);

        container.addMessageListener(listener, new PatternTopic(RedisSerialUtils.WEBSOCKET_PUBSUB_TOPIC));

        return container;
    }
}
