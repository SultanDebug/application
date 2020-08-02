package com.hzq.democlient.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hzq.democlient.SocketReqDTO;
import com.hzq.democlient.config.WebSocketServerConst;
import com.hzq.democlient.server.BussOneServerImpl;
import com.hzq.democlient.server.BussTwoServerImpl;
import com.hzq.democlient.utils.RedisSerialUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * ws-redis消息监听
 * @author Huangzq
 * @titl: WebsocketListener redis消息监听服务
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

        SocketReqDTO socketReqDTO = jsonArray.getObject(1, SocketReqDTO.class);

        log.info("实体：{}，信道：{}", JSONObject.toJSONString(socketReqDTO),topic);

        //发送消息
        /*WebSocketServerDict.WebSocketEnum webSocketEnum = WebSocketServerDict.WebSocketEnum.getByCode(socketReqDTO.getServer());

        if(webSocketEnum == null){
            throw new BusinessException("服务代码错误");
        }*/
        /**
         * 消息发送  暂没做到适配 扩展有侵入性
         */
        if(WebSocketServerConst.SERVER_TEST_SERVER_1_CODE.equals(socketReqDTO.getServer())){
            BussOneServerImpl.sendInfo(JSONObject.toJSONString(socketReqDTO),socketReqDTO.getCid());
        }else if(WebSocketServerConst.SERVER_TEST_SERVER_2_CODE.equals(socketReqDTO.getServer())){
            BussTwoServerImpl.sendInfo(JSONObject.toJSONString(socketReqDTO),socketReqDTO.getCid());
        }

    }

}
