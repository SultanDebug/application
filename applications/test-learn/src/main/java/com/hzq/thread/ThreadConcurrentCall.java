package com.hzq.thread;

import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/17 10:52
 */
@Slf4j
public class ThreadConcurrentCall {

    public static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            10,
            100,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100),
            t -> {
                Thread tt = new Thread(t);
                tt.setName("my-thread");

                tt.setUncaughtExceptionHandler((Thread ttt, Throwable e) -> {
                    System.out.println("[" + ttt.getName() + "]:捕获到异常为：" + e.getMessage());
                });
                return tt;
            },
            new ThreadPoolExecutor.AbortPolicy() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor pool) {
                    log.warn("拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", pool.getTaskCount(),
                            pool.getActiveCount(), pool.getQueue().size(), pool.getCompletedTaskCount());
                }
            });


    public static void main(String[] args) {
        all();
    }

    public static void all() {
        List<String> list = Collections.synchronizedList(Lists.newArrayList());

        AtomicInteger integer = new AtomicInteger(1);

        //不阻塞业务线程  阻塞主线程
        CountDownLatch countDownLatch = new CountDownLatch(3);

        //栅栏数不能大于线程池核心数，否则阻塞核心线程导致死锁
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        //限流令牌
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            /*try {
                CompletableFuture.supplyAsync(()->{
                    try {
                        //获取令牌
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("开始"+ finalI);
                    try {
                        //栅栏阻塞
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (BrokenBarrierException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("放行"+ finalI);
                    list.add(integer.getAndAdd(1) + "");
                    //非阻塞式任务完成信号
                    countDownLatch.countDown();
                    semaphore.release();
                    return true;
                }, threadPoolExecutor).get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }*/

            /*System.out.println("开始"+ finalI);
            Callable<String> callable = ()-> integer.getAndAdd(1) + "";

//            Future<String> future = new FutureTask<>(callable);

            try {
                list.add(callable.call());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("放行"+ finalI);*/

//            countDownLatch.countDown();

            THREAD_POOL_EXECUTOR.execute(() -> {
                try {
                    //获取令牌
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("开始" + finalI);
                try {
                    //栅栏阻塞
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("放行" + finalI);
                list.add(integer.getAndAdd(1) + "");
                //非阻塞式任务完成信号
                countDownLatch.countDown();
                semaphore.release();
            });
        }
        /*try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        System.out.println(JSONUtil.toJsonStr(list));
        THREAD_POOL_EXECUTOR.shutdown();
    }
}
