package com.hzq.democlient.server;

import com.hzq.democlient.config.WebSocketServerConst;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * ws测试业务2
 * @author Huangzq
 * @title: SocketController
 * @projectName applications
 * @date 2019/8/6 15:59
 */
@ServerEndpoint("/test2/{sid}")
@Component(WebSocketServerConst.SERVER_TEST_SERVER_2_CODE)
@Slf4j
@Getter
public class BussTwoServerImpl extends WebsocketAbstractServer{

    private Session session;

    private String sid = "";

    /**
     * 暂不考虑用户对应的服务器
     */

    @OnOpen
    public void onOpen(Session session , @PathParam("sid") String sid){
        try {
            sid = URLDecoder.decode(sid,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            return;
        }
        this.sid = sid;
        this.session = session;

        copyOnWriteArraySet.add(this);

        addCount();

        log.info("连接打开，监听："+sid+"当前在线人数："+getCount());

        try {
            sendMsg("connect success！");
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

    @OnClose
    public void onClose(){
        copyOnWriteArraySet.remove(this);
        subCount();
        log.info("连接关闭，监听关闭："+sid+"当前在线人数："+getCount());
    }

    @OnMessage
    public void OnMessage(String msg, Session session) throws IOException {
        log.info("收到客户端："+sid+" 消息："+msg);
        this.session.getBasicRemote().sendText(msg);
    }

    @OnError
    public void onError(Session session, Throwable error){
        log.error("异常："+error.getMessage());
    }

    /**
     * 发送消息
     * @param msg
     * @throws IOException
     */
    public void sendMsg(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

}
