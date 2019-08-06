package com.hzq.demoservice.controller;

import com.hzq.demoservice.DemoServiceInterface;
import com.hzq.demoservice.config.RedisCon;
import com.hzq.demoservice.service.rabbitmq.MsgProducer;
import com.hzq.demoservice.ssdb.core.SSDB;
import com.hzq.demoservice.util.ProduceThreadPool;
import com.hzq.demoservice.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huangzq
 * @date 2019-04-12
 */
@RestController
@Api(tags = "feign和中间件测试接口")
@Slf4j
public class DemoController implements DemoServiceInterface {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisCon redisCon;

    @Autowired
    private MsgProducer msgProducer;

    @Autowired
    private CuratorFramework curatorFramework;

    @Autowired
    private ProduceThreadPool produceThreadPool;

    @Value("${test.val}")
    private String val;

    @ApiOperation(value = "service端")
    @GetMapping("/demo/{name}")
    public String getTest(@PathVariable("name") String name){
        System.out.println("----------测试---------");
        return "hello "+name+",there is :"+val;
    }

    @ApiOperation(value = "redis 性能测试")
    @GetMapping("/redis")
    public String redis(@RequestParam String str){

        System.out.println(redisCon);

        redisUtil.set("test",str);

        return "花朵花朵"+","+redisUtil.get("test");
    }

    @ApiOperation("ssdb 性能测试")
    @GetMapping("/ssdb")
    public String ssdb(@RequestParam String str) throws Exception {

        SSDB ssdb = new SSDB("192.168.1.40",8888);

        ssdb.set("test","testVal");

        return "hello:"+str+","+ssdb.get("test");
    }

    @ApiOperation("mq多线程生产者开关")
    @GetMapping("/mq")
    public String mq(@RequestParam(required = false) Long thredId){

        if(thredId != null){
            Thread thread = ProduceThreadPool.pool.get(thredId);
            thread.interrupt();
        }else{

            /*ExecutorService executorService = ProduceThreadPool.executorService;

            Runnable runnable = () -> {
                for (int i = 0;i<Integer.MAX_VALUE;i++){
                    msgProducer.sendMsg("发送第 "+i+" 条消息");
                    log.info("发送{}次消息",i);
                    if(Thread.currentThread().isInterrupted()){
                        log.info(Thread.currentThread().getId()+" 已经停止");
                        break;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                        log.info("测试");
                        break;
                    }
                }
            };

            executorService.execute(runnable);*/



            Thread thread = new Thread(() -> {
                for (int i = 0;i<Integer.MAX_VALUE;i++){
                    msgProducer.sendMsg("发送第 "+i+" 条消息");

                    if(Thread.currentThread().isInterrupted()){
                        log.info(Thread.currentThread().getId()+" 已经停止");
                        break;
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                        log.info("测试");
                        break;
                    }
                }

            });
            thread.start();
            ProduceThreadPool.pool.put(thread.getId(),thread);
            return thread.getId()+"";
        }

        return thredId+"中断成功";
    }

    @ApiOperation("zookeeper测试")
    @GetMapping("/zk")
    public String zkTest(){

        return "success";
    }

}
