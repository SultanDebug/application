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
    public static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
            1,
            5,
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
                    log.error("拒绝策略:: 总线程数：{}， 活动线程数：{}， 排队线程数：{}， 执行完成线程数：{}", pool.getTaskCount(),
                            pool.getActiveCount(), pool.getQueue().size(), pool.getCompletedTaskCount());
                }
            });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        comf();
        THREAD_POOL_EXECUTOR.shutdown();

    }

    public static void comf() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        CompletableFuture<String> completableFuture = CompletableFuture.completedFuture("");

        CompletableFuture<String> c1 = completableFuture.thenApplyAsync((s) -> {
            log.info("[" + "c1" + "]");
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
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "[" + s + "c2" + "]";
        }, THREAD_POOL_EXECUTOR);

        CompletableFuture<String> c3 = c1.thenCombineAsync(c2, (s, s2) -> {
            log.info("[" + "c3" + "]");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "[" + s + s2 + "c3" + "]";
        }, THREAD_POOL_EXECUTOR);

        CompletableFuture<String> c4 = c1.thenApplyAsync((s) -> {
            log.info("[" + "c4" + "]");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "[" + s + "c4" + "]";
        }, THREAD_POOL_EXECUTOR);

        CompletableFuture<String> c5 = c2.thenApplyAsync((s) -> {
            log.info("[" + "c5" + "]");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "[" + s + "c5" + "]";
        }, THREAD_POOL_EXECUTOR);

        CompletableFuture<Void> all = CompletableFuture.allOf(c3, c4, c5);

        CompletableFuture<String> res = all.thenApplyAsync(unused -> {
            String join3 = c3.join();
            String join4 = c4.join();
            String join5 = c5.join();
            log.info(join3 + join4 + join5);
            return join3 + join4 + join5;
        }, THREAD_POOL_EXECUTOR);
        String s = res.get();
        long end = System.currentTimeMillis() - start;
        log.info(s + "-" + end);
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
