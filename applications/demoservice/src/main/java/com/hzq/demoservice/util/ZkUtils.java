package com.hzq.demoservice.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Huangzq
 * @title: ZkUtils
 * @projectName applications
 * @date 2019/7/4 18:52
 */
public class ZkUtils {
    public static CuratorFramework getCon(){

        RetryPolicy policy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                        .connectString("192.168.1.40:2181")
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(policy)
                        .build();

        curatorFramework.start();

        return curatorFramework;

    }

    public static void zkSetTest() throws Exception{

        CuratorFramework curatorFramework = getCon();

        try {
            curatorFramework.getData().forPath("/hzq");
        }catch (Exception e){
            curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath("/hzq");
        }

        Runnable runnable = ()->{
            try {
                System.out.println(Thread.currentThread()+"生成数据");
                String root = "test Info";
                curatorFramework.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/hzq/",root.getBytes());

                Thread.sleep(1000);

                System.out.println(Thread.currentThread()+"生成数据完成");

            } catch (Exception e) {
                e.printStackTrace();
            }
        };


        ExecutorService executorService1 = Executors.newFixedThreadPool(10);

        for(int i=0;i<10;i++){
            executorService1.execute(runnable);
        }

        executorService1.shutdown();


    }

    public static byte[] zkGetTest(String path) throws Exception{

        System.out.println( "*******开始消费*******");

        Runnable runnable1 = ()->{
            try {
                System.out.println(Thread.currentThread()+"读数据");
                List<String> list = getCon().getChildren().forPath("/hzq");

                list.sort(String::compareTo);

                for (String s : list) {
                    String path1 = "/hzq".concat("/").concat(s);

                    byte[] tmp = zkGetTest(path1);

                    zkDelTest(path1);

                    Thread.sleep(1000);

                    System.out.println(Thread.currentThread()+"删除成功");
                }

            } catch (Exception e) {
                System.out.println(Thread.currentThread()+"数据操作异常");
//                    e.printStackTrace();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(runnable1);

        executorService.shutdown();

        return null;

    }

    public static void zkDelTest(String path) throws Exception{

        CuratorFramework curatorFramework = getCon();

        curatorFramework.delete().forPath(path);

    }



    public static void main(String[] args) {


        try {


//            zkSetTest();

            zkGetTest(null);



            /*zkDelTest("/hzq");

            System.out.println( new String (zkGetTest("/hzq")));*/

        }catch (Exception e){

            System.out.println("未知异常");

//            e.printStackTrace();
        }

    }

}
