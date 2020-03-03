package com.hzq.netty.gupao.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-18
 */
public class ZkCilentDemo {

    static ZooKeeper zooKeeper;


    static {
        try {
            zooKeeper = new ZooKeeper("192.168.0.40:2181",30000,new Zkwatcher());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
//        ZooKeeper zooKeeper = new ZooKeeper("192.168.0.40:2181",30000,new ZkCilentDemo());

        String root = "/watchnode/1";

        if(zooKeeper.exists(root,false) == null){
            zooKeeper.create(root,"1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        }

        Thread.sleep(1000);

        zooKeeper.exists(root,new Zkwatcher());

        System.in.read();


    }

    /*@Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("时间类型："+watchedEvent.getType());
        if(watchedEvent.getType() == Event.EventType.NodeDataChanged){
            try {
                zooKeeper.exists(watchedEvent.getPath(),true);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }*/
}
