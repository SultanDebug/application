package com.hzq.zkservice.service.impl;

import com.hzq.zkservice.service.ZkOptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.BackgroundPathable;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Huangzq
 * @title: ZkOptServiceImpl
 * @projectName applications
 * @date 2020/1/19 9:37
 */
@Service
@Slf4j
public class ZkOptServiceImpl implements ZkOptService {
    @Autowired
    private CuratorFramework curatorFramework;

    private String status = "success";

    @Override
    public String zkWatch(String path) {
        String s = null;
        try {
            BackgroundPathable<byte[]> backgroundPathable = curatorFramework.getData().usingWatcher(new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    log.info("收到事件：{}",watchedEvent);
                }
            });
            s = new String(backgroundPathable.forPath(path));
            log.info("数据：{}",s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }



    @Override
    public String zkadd(String path, String msg) {
        //没有根节点  创建根节点
        try {
            curatorFramework.getData().forPath(path);
        }catch (Exception e){
            try {
                curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(path);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        if(StringUtils.isNotBlank(msg)){
            try {
                curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(path,msg.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return status;
    }

    @Override
    public String zkDel(String path) {

        try {
            curatorFramework.delete().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public String zkUpdate(String path, String msg) {

        try {
            curatorFramework.setData().forPath(path,msg.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    @Override
    public String zkQuery(String path) {

        String result = null;

        try {
            byte[] bytes = curatorFramework.getData().forPath(path);
            if(bytes != null && bytes.length > 0){
                result = new String(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
