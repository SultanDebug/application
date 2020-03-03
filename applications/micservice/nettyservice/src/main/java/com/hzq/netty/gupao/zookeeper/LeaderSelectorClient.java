package com.hzq.netty.gupao.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-12
 */
public class LeaderSelectorClient extends LeaderSelectorListenerAdapter implements Closeable {

    //当前进程
    private String name;

    //leader选举api
    private LeaderSelector leaderSelector;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public LeaderSelectorClient(){}

    public LeaderSelectorClient(String name){
        this.name = name;
    }

    public void setLeaderSelector(LeaderSelector leaderSelector) {
        this.leaderSelector = leaderSelector;
    }

    public void start(){
        //开始竞争leader
        leaderSelector.start();
    }

    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        //如果进入当前方法，说明当前进程获得了锁，获得锁以后，此方法被调用
        //这个方法执行完，锁释放
        System.out.println(name+"现在是leader。");

        //阻塞当前进程，防止leader丢失
        countDownLatch.await();
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    public static void main(String[] args) throws IOException {
        //重试策略
        RetryPolicy policy = new ExponentialBackoffRetry(1000,3);

        //创建curatorFramework

        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("192.168.0.40:2181")
//                        .authorization("digest","user:pass".getBytes())
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(policy)
                        .build();


        //连接zk
        curatorFramework.start();

        LeaderSelectorClient leaderSelectorClient = new LeaderSelectorClient("clientA");

        LeaderSelector leaderSelector = new LeaderSelector(curatorFramework,"/leaders",leaderSelectorClient);

        leaderSelectorClient.setLeaderSelector(leaderSelector);

        leaderSelectorClient.start();

        System.in.read();


    }
}
