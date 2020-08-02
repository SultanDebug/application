package com.hzq.democlient.server;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket框架抽象类
 * @author Huangzq
 * @title: SocketController
 * @projectName applications
 * @date 2019/8/6 15:59
 */
@Slf4j
public abstract class WebsocketAbstractServer {
    /*整个业务全局连接数*/
    static int onlineCount=0;

    /*整个业务全局连接池*/
    static CopyOnWriteArraySet<WebsocketAbstractServer> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

    /*连接标识*/
    abstract String getSid();

    /*连接会话*/
    abstract Session getSession();

    /**
     * 暂不考虑用户对应的服务器  可把用户业务id对应的ip存redis 发送的时候比对ip决定是否在此机器上找会话
     */

    /**
     * 如果有sid则单独推送  没有则全量客户端推送
     * @param msg
     * @param sid
     */
    public static void sendInfo(String msg,String sid) {
        //换成map  省循环  经测试map不能保存同用户多客户端的问题
        /*if(sid==null){

            for (Map.Entry<String, WebSocketServer> stringWebSocketServerEntry : webSocketServers.entrySet()) {
                try {
                    WebSocketServer webSocketServer = stringWebSocketServerEntry.getValue();
                    webSocketServer.sendMsg(msg);
                }catch (IOException e){
                    log.error(e.getMessage());
                }
            }
        }else if(webSocketServers.get(sid) != null && sid.equals(webSocketServers.get(sid).sid)){
            log.info("针对推送："+sid+" 内容："+msg);
            webSocketServers.get(sid).sendMsg(msg);
        }*/


        for (WebsocketAbstractServer obj : copyOnWriteArraySet) {

            try {
                if(sid==null){
                    obj.sendMsg(msg);
                }else if(sid.equals(obj.getSid())){
                    log.info("针对推送："+sid+" 内容："+msg);
                    obj.sendMsg(msg);
                }
            }catch (IOException e){
                log.info("error:"+ obj.getSid());
            }

        }
    }

    /**
     * 连接数获取
     * @return
     */
    static synchronized int getCount(){return onlineCount;}

    /**
     * 新增连接
     */
    static synchronized void addCount(){
        onlineCount++;}

    /**
     * 连接关闭
     */
    static synchronized void subCount(){
        onlineCount--;}

    /**
     * 业务发送消息
     * @param msg
     * @throws IOException
     */
    abstract public void sendMsg(String msg) throws IOException;

    /**
     * 连接打开回调  业务代码需加 @OnOpen 注解
     * @param session
     * @param sid
     */
    abstract public void onOpen(Session session , String sid);

    /**
     * 连接关闭回调  业务代码需加 @OnClose 注解
     */
    abstract public void onClose();

    /**
     * 接收消息  业务代码需加 @OnMessage 注解
     * @param msg
     * @param session
     * @throws IOException
     */
    abstract public void OnMessage(String msg, Session session) throws IOException;

    /**
     * 连接异常 业务代码需加 @OnError 注解
     * @param session
     * @param error
     */
    abstract public void onError(Session session, Throwable error);



}
