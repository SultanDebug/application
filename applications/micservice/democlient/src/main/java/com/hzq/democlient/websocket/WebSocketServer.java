package com.hzq.democlient.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by sultan on 2018/10/9.
 */
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {
    private static int onlineCount=0;

    private static CopyOnWriteArraySet<WebSocketServer> webSocketServers = new CopyOnWriteArraySet<WebSocketServer>();

    private Session session;

    private String sid = "";

    @OnOpen
    public void onOpen(Session session , @PathParam("sid") String sid){
        try {
            sid = URLDecoder.decode(sid,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        this.sid = sid;
        this.session = session;

        webSocketServers.add(this);

        addCount();

        System.out.println("连接打开，监听："+sid+"当前在线人数："+getCount());

        try {
            sendMsg("连接成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(){
        webSocketServers.remove(this);
        subCount();
        System.out.println("连接关闭，监听关闭："+sid+"当前在线人数："+getCount());
    }

    @OnMessage
    public void OnMessage(String msg, Session session){
        System.out.println("收到客户端："+sid+" 消息："+msg);
        for (WebSocketServer webSocketServer : webSocketServers) {
            try {
                webSocketServer.sendMsg(this.sid+":"+msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 如果有sid则单独推送  没有则全量客户端推送
     * @param msg
     * @param sid
     */
    public static void sendInfo(String msg,@PathParam("sid") String sid) {
        //换成map  省循环
        for (WebSocketServer webSocketServer : webSocketServers) {
            try {
                if(sid==null){
                    webSocketServer.sendMsg(msg);
                }else if(sid.equals(webSocketServer.sid)){
                    System.out.println("针对推送："+sid+" 内容："+msg);
                    webSocketServer.sendMsg(webSocketServer.sid+":"+msg);
                }
            }catch (IOException e){
                System.out.println("error:"+webSocketServer.sid);
                continue;
            }

        }
    }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("异常："+error.getStackTrace());
    }

    public void sendMsg(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    public static synchronized int getCount(){return WebSocketServer.onlineCount;}

    public static synchronized void addCount(){WebSocketServer.onlineCount++;}

    public static synchronized void subCount(){WebSocketServer.onlineCount--;}


}
