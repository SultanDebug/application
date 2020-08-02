package com.hzq.zkcore.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * zk基础api
 * @author Huangzq
 * @title: ZkUtils
 * @projectName applications
 * @date 2019/7/4 18:52
 */
@Slf4j
@Component
public class ZkService<T> {
    @Autowired
    private CuratorFramework curatorFramework;


    /*public static CuratorFramework getCon(){

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

    }*/

    public String set(String rootPath , String path , T para) throws Exception{
        //没有根节点  创建根节点
        try {
            curatorFramework.getData().forPath(rootPath);
        }catch (Exception e){
            curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(rootPath);
        }

        //生成顺序节点
        log.info("生成数据");
        //序列化对象
        ByteArrayOutputStream byteArrayOutputStream = new    ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeObject(para);

        String root = byteArrayOutputStream.toString("ISO-8859-1");

        String result = curatorFramework.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(rootPath+path,root.getBytes());
        log.info("生成数据完成"+result);

        return result;
    }

    public T get( String path) throws Exception {
        byte[] tmp = curatorFramework.getData().forPath(path);
        //反序列化对象
        String res = new String(tmp);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(res.getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        T t = null;
        t = (T) objectInputStream.readObject();

        return t;
    }

    public List<String> getAllNode(String root) throws Exception {
        List<String> list = curatorFramework.getChildren().forPath(root);

        list.sort(String::compareTo);

        return list;
    }

    public void zkDel(String path) throws Exception{

        curatorFramework.delete().forPath(path);

    }

    /*public T get( String path) throws Exception {

        log.info( "*******开始消费*******");

        CountDownLatch countDownLatch = new CountDownLatch(1);

        T t = null;

        while (true){
            try {
                log.info("读数据");
                List<String> list = curatorFramework.getChildren().forPath(rootPath);

                list.sort(String::compareTo);

                String path = rootPath.concat("/").concat(list.get(list.size()-1));

                byte[] tmp = curatorFramework.getData().forPath(path);

                zkDel(path);

                countDownLatch.countDown();

                log.info(Thread.currentThread()+"删除成功");

                //反序列化对象
                String res = new String(tmp);

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(res.getBytes("ISO-8859-1"));
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

                t = (T) objectInputStream.readObject();

                return t;

            } catch (Exception e) {
                System.out.println("获取队列数据失败");
//                    e.printStackTrace();
                countDownLatch.await();
            }
        }
    }*/





    public static void zkTest(String[] args) {

        ZkService<String> test = new ZkService<>();

        try {

            /*Users users1 = new Users();

            users1.setId(1);
            users1.setUserName("黄震强1");

            Users users2 = new Users();

            users2.setId(2);
            users2.setUserName("黄震强2");

            Users users3 = new Users();

            users3.setId(3);
            users3.setUserName("黄震强3");

            test.push(users1);
            test.push(users2);
            test.push(users3);*/

//            Users users = test.pop();
//            System.out.println(users.getId()+"/"+users.getUserName());


            ExecutorService executorService = Executors.newFixedThreadPool(4);

            Runnable runnable = () -> {
                try {
//                    Users users = test.pop();
//                    if(users != null){
//                        System.out.println("******获取结果******"+users.getId()+"/"+users.getUserName());
//                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            executorService.execute(runnable);
            executorService.execute(runnable);
            executorService.execute(runnable);
            executorService.execute(runnable);

            executorService.shutdown();

        }catch (Exception e){

//            System.out.println("未知异常");

            e.printStackTrace();
        }

    }

}
