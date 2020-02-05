package com.hzq.netty.gupao.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-05
 */
public class ZkDemo {
    public static void main(String[] args) {
        //重试策略
        RetryPolicy policy = new ExponentialBackoffRetry(1000,3);

        //创建curatorFramework

        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("192.168.1.40:2181")
//                        .authorization("digest","user:pass".getBytes())
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(policy)
                        .build();


        //连接zk

        curatorFramework.start();

        try {
            watcher(curatorFramework);
            watcherChild(curatorFramework);
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }



//        setData(curatorFramework);
//        update(curatorFramework);
    }

    public static void setData(CuratorFramework curatorFramework){
        try {
            curatorFramework.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath("/hzq/dataversion","test".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void update(CuratorFramework curatorFramework){
        Stat stat = new Stat();
        try {
            byte[] bytes = curatorFramework
                    .getData()
                    .storingStatIn(stat)
                    .forPath("/hzq/dataversion");
            curatorFramework.setData()
                    .withVersion(stat.getAversion())
                    .forPath("/hzq/dataversion","updatedata".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void nodeAcl(CuratorFramework curatorFramework){
        List<ACL> list = new ArrayList<>();

        ACL acl = new ACL(ZooDefs.Perms.READ,new Id("digest","user:pass"));

        list.add(acl);

        try {
            curatorFramework.create().withMode(CreateMode.PERSISTENT).withACL(list).forPath("/auth","pass".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * PathChildCache
     * NodeCache
     * TreeCache
     * @param curatorFramework
     */
    public static void watcher(CuratorFramework curatorFramework) throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework,"/hzq/dataversion");

        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("node changed :"+nodeCache.getPath()+" , 数据修改："+new String(nodeCache.getCurrentData().getData()));
            }
        };

        nodeCache.getListenable().addListener(nodeCacheListener);

        nodeCache.start();
    }

    public static void watcherChild(CuratorFramework curatorFramework) throws Exception {
        PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework,"/hzq/dataversion",true);

        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework1, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println("监听类型："+pathChildrenCacheEvent.getType()+"监听节点："
                        +pathChildrenCacheEvent.getData().getPath()
                        +"监听节点：" +new String(pathChildrenCacheEvent.getData().getData()));
            }
        };

        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);

        pathChildrenCache.start(PathChildrenCache.StartMode.NORMAL);
    }

}
