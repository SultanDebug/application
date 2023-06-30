package com.hzq.thread;

import com.google.common.collect.Lists;
import com.hankcs.hanlp.model.crf.crfpp.Pair;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author Huangzq
 * @description
 * @date 2023/4/17 16:08
 */
@Slf4j
public class ThreadCombination {
    static CountDownLatch countDownLatch = new CountDownLatch(3);
    public static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            1,
            1,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1),
            t -> {
                Thread tt = new Thread(t);
                tt.setName("my-thread-" + tt.getId());
                tt.setUncaughtExceptionHandler((Thread ttt, Throwable e) -> {
                    log.error("[{}]:捕获到异常为：", ttt.getName(), e);
                });
                return tt;
            },
            new ThreadPoolExecutor.AbortPolicy() {
                @Override
                public void rejectedExecution(Runnable r, ThreadPoolExecutor pool) {
                    countDownLatch.countDown();
                    //r.run();
                    log.error("拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", pool.getTaskCount(),
                            pool.getActiveCount(), pool.getQueue().size(), pool.getCompletedTaskCount());
                }
            });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        comf();
//        test();
        THREAD_POOL_EXECUTOR.shutdown();

    }

    public static void comf() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("");

        CompletableFuture<String> c1 = completableFuture.thenApplyAsync((s) -> {
            log.info("[" + "c1" + "]");
            log.error("c1拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", THREAD_POOL_EXECUTOR.getTaskCount(),
                    THREAD_POOL_EXECUTOR.getActiveCount(), THREAD_POOL_EXECUTOR.getQueue().size(), THREAD_POOL_EXECUTOR.getCompletedTaskCount());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "[" + s + "c1" + "]";
        }, THREAD_POOL_EXECUTOR).exceptionally(throwable -> {
            log.error("c1异常", throwable);
            return "c1-err";
        });

        CompletableFuture<String> c2 = completableFuture.thenApplyAsync((s) -> {
            log.info("[" + "c2" + "]");
            log.error("c2拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", THREAD_POOL_EXECUTOR.getTaskCount(),
                    THREAD_POOL_EXECUTOR.getActiveCount(), THREAD_POOL_EXECUTOR.getQueue().size(), THREAD_POOL_EXECUTOR.getCompletedTaskCount());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "[" + s + "c2" + "]";
        }, THREAD_POOL_EXECUTOR);

        CompletableFuture<String> c3 = c1.thenCombineAsync(c2, (s, s2) -> {
            log.info("[" + "c3" + "]");
            log.error("c1拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", THREAD_POOL_EXECUTOR.getTaskCount(),
                    THREAD_POOL_EXECUTOR.getActiveCount(), THREAD_POOL_EXECUTOR.getQueue().size(), THREAD_POOL_EXECUTOR.getCompletedTaskCount());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "[" + s + s2 + "c3" + "]";
        }, THREAD_POOL_EXECUTOR);

        CompletableFuture<String> c4 = c1.thenApplyAsync((s) -> {
            log.info("[" + "c4" + "]");
            log.error("c4拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", THREAD_POOL_EXECUTOR.getTaskCount(),
                    THREAD_POOL_EXECUTOR.getActiveCount(), THREAD_POOL_EXECUTOR.getQueue().size(), THREAD_POOL_EXECUTOR.getCompletedTaskCount());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "[" + s + "c4" + "]";
        }, THREAD_POOL_EXECUTOR);

        CompletableFuture<String> c5 = c2.thenApplyAsync((s) -> {
            log.info("[" + "c5" + "]");

            log.error("c5拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", THREAD_POOL_EXECUTOR.getTaskCount(),
                    THREAD_POOL_EXECUTOR.getActiveCount(), THREAD_POOL_EXECUTOR.getQueue().size(), THREAD_POOL_EXECUTOR.getCompletedTaskCount());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "[" + s + "c5" + "]";
        }, THREAD_POOL_EXECUTOR);

        CompletableFuture<Void> all = CompletableFuture.allOf(c3, c4, c5);

        CompletableFuture<String> res = all.thenApplyAsync(unused -> {
            log.info("[all]");
            String join3 = c3.join();
            String join4 = c4.join();
            String join5 = c5.join();
            log.info(join3 + join4 + join5);
            return join3 + join4 + join5;
        }, THREAD_POOL_EXECUTOR);
        String s = null;
        s = res.get();
        /*try {
            s = res.get(1000L, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            log.error("error:",e);
        }*/
        long end = System.currentTimeMillis() - start;
        log.info(s + "-" + end);
    }

    public static void test() throws InterruptedException {
        THREAD_POOL_EXECUTOR.execute(()->{
            try {
                log.info("c1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.error("c1拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", THREAD_POOL_EXECUTOR.getTaskCount(),
                        THREAD_POOL_EXECUTOR.getActiveCount(), THREAD_POOL_EXECUTOR.getQueue().size(), THREAD_POOL_EXECUTOR.getCompletedTaskCount());
            }catch (Exception e){
                throw e;
            }finally {
                countDownLatch.countDown();
            }

        });

        THREAD_POOL_EXECUTOR.execute(()->{
            try {
                log.info("c2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.error("c2拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", THREAD_POOL_EXECUTOR.getTaskCount(),
                        THREAD_POOL_EXECUTOR.getActiveCount(), THREAD_POOL_EXECUTOR.getQueue().size(), THREAD_POOL_EXECUTOR.getCompletedTaskCount());
            }catch (Exception e){
                throw e;
            }finally {
                countDownLatch.countDown();
            }
        });

        THREAD_POOL_EXECUTOR.execute(()->{
            try {
                log.info("c3");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.error("c3拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", THREAD_POOL_EXECUTOR.getTaskCount(),
                        THREAD_POOL_EXECUTOR.getActiveCount(), THREAD_POOL_EXECUTOR.getQueue().size(), THREAD_POOL_EXECUTOR.getCompletedTaskCount());
            }catch (Exception e){
                throw e;
            }finally {
                countDownLatch.countDown();
            }
        });

        countDownLatch.await();
    }

    public static void cf() throws ExecutionException, InterruptedException {

        List<Pair<Integer, Boolean>> chain = Lists.newArrayList(
                new Pair<>(1, true),
                new Pair<>(2, true),
                new Pair<>(3, false),
                new Pair<>(4, false),
                new Pair<>(5, true),
                new Pair<>(6, true)
        );
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("");
        for (Pair<Integer, Boolean> pair : chain) {
            if (pair.getValue()) {
                completableFuture.thenApplyAsync(s -> {
                    System.out.println("任务：" + pair.getKey());
                    return s += pair.getKey();
                });
            } else {
                //completableFuture.the
            }
        }

    }

}
