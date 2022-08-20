package com.hzq.algo;

import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.json.JSONUtil;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/17 10:52
 */
public class ThreadConcurrentCall {

    public static void main(String[] args) {
        all();
    }

    public static void all(){
        List<String> list = Collections.synchronizedList(Lists.newArrayList());

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 10, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

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

            threadPoolExecutor.execute(() -> {
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
            });
        }
        /*try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        System.out.println(JSONUtil.toJsonStr(list));
        threadPoolExecutor.shutdown();
    }
}
