package com.hzq.common.thread;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

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
                        Thread.sleep(200);
                        future.complete("test");
                    } catch (Exception e) {

                    }
                }
        ).start();

        try {

            Thread.sleep(50);

            String s = future.get(200, TimeUnit.MILLISECONDS);
            System.out.println("结果：" + s);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("耗时：" + (System.nanoTime() - start));
    }
}
