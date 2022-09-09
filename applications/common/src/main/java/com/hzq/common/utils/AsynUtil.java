package com.hzq.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Supplier;

import static com.hzq.common.utils.CommonConstants.TRACE_ID;

/**
 * @author Huangzq
 * @description
 * @date 2022/9/7 14:52
 */
@Slf4j
public class AsynUtil<R> {
    private AsynUtil() {
    }

    /**
     * Description:
     *  带返回值的任务执行，汇总到列表返回
     * @param
     * @return
     * @author Huangzq
     * @date 2022/9/9 9:42
     */
    public static<R> List<R> submitToList(ExecutorService executorService , Callable<R> ... callables) throws InterruptedException {
        CountDownLatch downLatch  = new CountDownLatch(callables.length);
        String traceId = MDC.get(TRACE_ID);
        List<R> list = Collections.synchronizedList(new ArrayList<>());
        for (Callable<R> callable : callables) {
            AsynCallable<R> asynCallable = new AsynCallable<R>(callable,traceId){
                @Override
                public void doCall() throws Exception {
                    try {
                        R call = this.callable.call();
                        list.add(call);
                    }finally {
                        downLatch.countDown();
                    }
                }
            };
            executorService.execute(asynCallable);
        }
        downLatch.await();
        return list;
    }

    /**
     * Description:
     *  无返回值任务执行，任务完成状态同步阻塞
     * @param
     * @return
     * @author Huangzq
     * @date 2022/9/9 9:43
     */
    public static void executeSync (ExecutorService executorService , Callable<Object> ... callables) throws InterruptedException {
        CountDownLatch downLatch  = new CountDownLatch(callables.length);
        String traceId = MDC.get(TRACE_ID);
        for (Callable<Object> callable : callables) {
            AsynCallable<Object> asynCallable = new AsynCallable<Object>(callable,traceId){
                @Override
                public void doCall() throws Exception {
                    try {
                        this.callable.call();
                    }finally {
                        downLatch.countDown();
                    }
                }
            };
            executorService.execute(asynCallable);
        }
        downLatch.await();
    }

    /**
     * Description:
     *  无返回值任务执行，非阻塞异步执行
     * @param
     * @return
     * @author Huangzq
     * @date 2022/9/9 9:44
     */
    public static void executeNonSync (ExecutorService executorService , Callable<Object> ... callables) throws InterruptedException {
        String traceId = MDC.get(TRACE_ID);
        for (Callable<Object> callable : callables) {
            AsynCallable<Object> asynCallable = new AsynCallable<Object>(callable,traceId){
                @Override
                public void doCall() throws Exception {
                    this.callable.call();
                }
            };
            executorService.execute(asynCallable);
        }
    }

    /**
     * Description:
     *  带返回值的任务，限制执行总时长
     * @param
     * @return
     * @author Huangzq
     * @date 2022/9/9 9:45
     */
    public static<R> List<R> submitToListOnTime(ExecutorService executorService ,Long timeOut, List<Supplier<R>> suppliers) throws InterruptedException, ExecutionException, TimeoutException {
        CountDownLatch downLatch  = new CountDownLatch(suppliers.size());
        String traceId = MDC.get(TRACE_ID);
        List<R> list = Collections.synchronizedList(new ArrayList<>());

        CompletableFuture.allOf(
                suppliers.stream()
                .map(o-> CompletableFuture.runAsync(new AsynSupplier<R>(traceId,o) {
                    @Override
                    public void doGet() {
                        try {
                            R r = this.supplier.get();
                            list.add(r);
                        }finally {
                            downLatch.countDown();
                        }
                    }
                }, executorService))
                .toArray(CompletableFuture<?>[]::new)
        ).get(timeOut,TimeUnit.SECONDS);

        downLatch.await();
        return list;
    }

    public abstract static class AsynSupplier<R> implements Runnable {
        /**
         * 日志id
         * */
        protected String traceId;

        /**
         * 任务逻辑
         * */
        protected Supplier<R> supplier;

        protected AsynSupplier(String traceId,Supplier<R> supplier) {
            this.traceId = traceId;
            this.supplier = supplier;
        }

        @Override
        public void run(){
            try {
                //线程变量透传
                MDC.put(TRACE_ID,traceId);
                this.doGet();
            }catch (Exception e){
                throw new RuntimeException("运行异常："+e.getMessage());
            }finally {
                //线程变量清理
                MDC.remove(TRACE_ID);
            }
        }
        public abstract void doGet();
    }

    public abstract static class AsynCallable<R> implements Runnable {
        /**
         * 任务逻辑
         * */
        protected Callable<R> callable;

        /**
         * 暂不启用
         * */
        protected String key;

        /**
         * 日志id
         * */
        protected String traceId;

        protected AsynCallable(Callable<R> callable, String traceId) {
            this.callable = callable;
            this.traceId = traceId;
        }

        @Deprecated
        protected AsynCallable(Callable<R> callable,String key, String traceId) {
            this.callable = callable;
            this.traceId = traceId;
            this.key = key;
        }

        public abstract void doCall() throws Exception;

        @Override
        public void run() {
            try {
                //线程变量透传
                MDC.put(TRACE_ID,traceId);
                this.doCall();
            } catch (Exception e){
                throw new RuntimeException("运行异常："+e.getMessage());
            }finally {
                //线程变量清理
                MDC.remove(TRACE_ID);
            }
        }
    }

}
