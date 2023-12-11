package com.hzq.common.thread;

import com.hzq.common.utils.AsynUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author Huangzq
 * @description
 * @date 2023/12/8 17:37
 */
public class ThreadTest {
    /**
     * 线程空时兼完全异步
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/11/1 10:26
     */
    @Test
    public void timeLimit() {
        long start = System.nanoTime();
        CompletableFuture<String> future = new CompletableFuture<>();

        new Thread(
                () -> {
                    try {
                        Thread.sleep(1050);
                        future.complete("test");
                    } catch (Exception e) {

                    }
                }
        ).start();

        try {

            Thread.sleep(1000);

            String s = future.get(1, TimeUnit.MILLISECONDS);
            System.out.println("结果：" + s);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("耗时：" + (System.nanoTime() - start));
    }


    @Test
    public void asynTest() {
        ExecutorService service = new ThreadPoolExecutor(10
                , 20
                , 60L
                , TimeUnit.SECONDS
                , new LinkedBlockingDeque<>()
                , new ThreadPoolExecutor.CallerRunsPolicy());
        List<Supplier<String>> list = new ArrayList<>();

        long start = System.nanoTime();
        Supplier<String> s1 = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "s1";
        };

        Supplier<String> s2 = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "s2";
        };

        list.add(s1);
        list.add(s2);

        CompletableFuture<List<String>> listCompletableFuture = AsynUtil.excuteTask(list, service);

        List<String> list1 = null;
        try {
            list1 = listCompletableFuture.get(1500, TimeUnit.MILLISECONDS);
            System.out.println(list1);
        } catch (Exception e) {
            System.out.println("main异常：" + e);
        }

        System.out.println("耗时：" + (System.nanoTime() - start));
    }
}
