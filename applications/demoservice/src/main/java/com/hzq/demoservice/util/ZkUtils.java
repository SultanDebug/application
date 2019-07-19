package com.hzq.demoservice.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Huangzq
 * @title: ZkUtils
 * @projectName applications
 * @date 2019/7/4 18:52
 */
@Slf4j
public class ZkUtils<T> {
    private String rootPath = "/zkQueue";

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

    public void push(T para) throws Exception{

        CuratorFramework curatorFramework = getCon();
        //没有根节点  创建根节点
        try {
            curatorFramework.getData().forPath(rootPath);
        }catch (Exception e){
            curatorFramework.create().withMode(CreateMode.PERSISTENT).forPath(rootPath);
        }

        //生成顺序节点
        System.out.println("生成数据");
        //序列化对象
        ByteArrayOutputStream byteArrayOutputStream = new    ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteArrayOutputStream);
        out.writeObject(para);

        String root = byteArrayOutputStream.toString("ISO-8859-1");

//        if(root.)

        String result = curatorFramework.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(rootPath+"/",root.getBytes());
        System.out.println("生成数据完成"+result);


    }

    public T pop() throws Exception {

        System.out.println( "*******开始消费*******");
        CuratorFramework curatorFramework = getCon();

        CountDownLatch countDownLatch = new CountDownLatch(1);

        T t = null;

        while (true){
            try {
                System.out.println("读数据");
                List<String> list = curatorFramework.getChildren().forPath(rootPath);

                list.sort(String::compareTo);

                String path = rootPath.concat("/").concat(list.get(list.size()-1));

                byte[] tmp = curatorFramework.getData().forPath(path);

                zkDel(path);

                countDownLatch.countDown();

                System.out.println(Thread.currentThread()+"删除成功");

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
    }

    private static void zkDel(String path) throws Exception{

        CuratorFramework curatorFramework = getCon();

        curatorFramework.delete().forPath(path);

    }



    public static void main(String[] args) {

        ZkUtils<Users> test = new ZkUtils<>();

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
                    Users users = test.pop();
                    if(users != null){
                        System.out.println("******获取结果******"+users.getId()+"/"+users.getUserName());
                    }


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
