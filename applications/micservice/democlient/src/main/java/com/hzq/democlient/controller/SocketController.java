package com.hzq.democlient.controller;

import com.hzq.democlient.WebsocketInterface;
import com.hzq.democlient.service.SocketPubService;
import com.hzq.democlient.SocketReqDTO;
import com.hzq.democlient.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by sultan on 2018/10/9.
 */
@Controller
@RequestMapping("/checkcenter")
public class SocketController implements WebsocketInterface {

    @Autowired
    private SocketPubService pubService;

    //推送数据测试接口
    @ResponseBody
    @GetMapping("/socket/push/{cid}")
    public String pushToWeb(@PathVariable String cid, String message) {
        try {
            cid = URLDecoder.decode(cid,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        WebSocketServer.sendInfo(message,cid);
        return cid;
    }

    /**
     * 数据推送  支持多实例
     * @param socketReqDTO
     * @return
     */
    @Override
    public String sender(@RequestBody SocketReqDTO socketReqDTO) {
//        rabbitTemplate.convertAndSend(RabbitmqConstants.WEBSOCKET_QUEUE,socketReqDTO);
        return pubService.pubMsg(socketReqDTO);
    }
}
