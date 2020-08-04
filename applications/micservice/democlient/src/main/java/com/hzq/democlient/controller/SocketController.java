package com.hzq.democlient.controller;

import com.hzq.common.aop.ResultResponse;
import com.hzq.democlient.SocketReqDTO;
import com.hzq.democlient.WebsocketInterface;
import com.hzq.democlient.service.SocketPubService;
import com.hzq.democlient.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * ws基础api
 * @author Huangzq
 * @title: SocketController
 * @projectName applications
 * @date 2019/8/6 15:59
 */
@Controller
@RequestMapping("/checkcenter")
public class SocketController implements WebsocketInterface {

    @Autowired
    private SocketPubService pubService;

    //推送数据测试接口
    @ResponseBody
    @GetMapping("/socket/push/{cid}")
    public ResultResponse<String> pushToWeb(@PathVariable String cid, String message) {
        try {
            cid = URLDecoder.decode(cid,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        WebSocketServer.sendInfo(message,cid);
        return ResultResponse.success(cid);
    }

    /**
     * 数据推送  支持多实例
     * @param socketReqDTO
     * @return
     */
    @Override
    public ResultResponse<String> sender(@RequestBody SocketReqDTO socketReqDTO) {
//        rabbitTemplate.convertAndSend(RabbitmqConstants.WEBSOCKET_QUEUE,socketReqDTO);
        return ResultResponse.success(pubService.pubMsg(socketReqDTO));
    }
}
