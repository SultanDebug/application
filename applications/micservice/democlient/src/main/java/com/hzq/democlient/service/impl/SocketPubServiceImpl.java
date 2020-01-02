package com.hzq.democlient.service.impl;

import com.hzq.democlient.SocketReqDTO;
import com.hzq.democlient.service.SocketPubService;
import com.hzq.democlient.utils.RedisSerialUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @author Huangzq
 * @title: SocketPubServiceImpl
 * @projectName qs-saas
 * @date 2019/12/31 10:33
 */
@Service
public class SocketPubServiceImpl implements SocketPubService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String pubMsg(SocketReqDTO socketReqDTO) {

        //序列化消息
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = RedisSerialUtils.getSerial();

        byte[] msgTmp =jackson2JsonRedisSerializer.serialize(socketReqDTO);

        //发送消息
        redisTemplate.convertAndSend(RedisSerialUtils.WEBSOCKET_PUBSUB_TOPIC, new String(msgTmp));

        return socketReqDTO.getContent();
    }
}
