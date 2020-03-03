package com.hzq.netty.gupao.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-18
 */
public class Zkwatcher implements Watcher {

    private static ZooKeeper zooKeeper;

    static {
        try {
            zooKeeper = new ZooKeeper("192.168.0.40:2181",30000,new Zkwatcher());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 每次监听节点值最小的
     * 临时有序节点
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("节点："+ watchedEvent.getPath()+",类型："+watchedEvent.getType());

        if(watchedEvent.getType() == Event.EventType.NodeDeleted){
            try {
                List<String> children = zooKeeper.getChildren("/watchnode", false);
                Collections.sort(children);

                if(!CollectionUtils.isEmpty(children)){
                    zooKeeper.exists("/watchnode/"+children.get(0),true);
                }

            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
