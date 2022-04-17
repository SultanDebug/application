package com.hzq.directservice.controller;

import com.hzq.common.aop.ResultResponse;
import com.hzq.directservice.service.TestService;
import com.hzq.rediscore.lockaop.RedisLock;
import com.hzq.rediscore.service.RedisService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Huangzq
 * @Date: 2022/3/9 9:32
 */
@RestController
//@Api(tags = "无配置中心启动")
//@Slf4j
//@Controller
//@ResponseBody
public class TestController {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private TestService testService;

    @ApiOperation(value = "redis锁测试")
    @GetMapping("/direct/redis")
    @RedisLock(lockKey = "lockKey", expire = 60000)
    public ResultResponse<String> redis(String val) throws InterruptedException {
        redisService.set("testKey", val);
        TimeUnit.SECONDS.sleep(1L);
        return ResultResponse.success((String) redisService.get("testKey"));
    }

    @ApiOperation(value = "RPC模型测试")
    @GetMapping("/direct/rpcModule")
    public ResultResponse<String> rpcModule() throws InterruptedException, NoSuchFieldException, IllegalAccessException {

        /*RpcProxy proxy = new RpcProxy("192.168.2.2","9999");
        Object o = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{RpcProxyDemoInterface.class}, proxy);
        Field rpcProxyDemoInterface = this.getClass().getDeclaredField("rpcProxyDemoInterface");
        rpcProxyDemoInterface.setAccessible(true);
        rpcProxyDemoInterface.set(this,o);*/
        /*
        * 在controller层直接注入reference实例，由于从spring容器里拿到的congtroller实例是代理实例
        * 而在调用的时候实例不是这个代理实例（暂不知原因），相当于注入无效
        * 转到service层注入，没有aop影响，注入成功
        * */
        return ResultResponse.success(testService.rpcModule());
    }
}
