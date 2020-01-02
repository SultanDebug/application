package com.hzq.democlient.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzq.democlient.SocketReqDTO;
import com.hzq.democlient.utils.RedisSerialUtils;
import com.hzq.democlient.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Huangzq
 * @title: WebsocketListener redis消息监听服务
 * @projectName qs-saas
 * @date 2019/12/30 16:54
 */
@Slf4j
@Component
public class WebsocketListener implements MessageListener {

    /*@Autowired
    private WebSocketServer webSocketServer;*/

    /*@Autowired
    private GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer;*/

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 收到sub消息
     * @param message
     * @param bytes
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        String tmp = new String(bytes);
        log.info("byte数组内容：{}",tmp);

        //序列化消息
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = RedisSerialUtils.getSerial();

        byte[] body = message.getBody();
        byte[] channel = message.getChannel();

        JSONArray jsonArray = JSONArray.parseArray(jackson2JsonRedisSerializer.deserialize(body).toString());

        String topic = new String(channel);

        log.info("实体：{}，信道：{}", JSONObject.toJSONString(jsonArray.getObject(1, SocketReqDTO.class)),topic);

        //发送消息
        WebSocketServer.sendInfo(JSONObject.toJSONString(jsonArray.getObject(1,SocketReqDTO.class)),jsonArray.getObject(1,SocketReqDTO.class).getCid());

    }

}
