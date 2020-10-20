package com.hzq.democlient.controller;

import com.hzq.common.aop.LogAop;
import com.hzq.common.aop.ResultResponse;
import com.hzq.common.utils.ApplicationContextUtils;
import com.hzq.common.utils.UserUtils;
import com.hzq.feignservice.FeignServInterface;
import com.hzq.rediscore.lockaop.RedisLock;
import com.hzq.rediscore.service.RedisService;
import com.hzq.starter.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * feign客户端和中间件测试接口
 * @author Huangzq
 * @title: DemoClientController
 * @projectName applications
 * @date 2019/8/6 15:59
 */
@RestController
@Api(tags = "feign客户端和中间件测试接口")
@Slf4j
public class DemoClientController {
    @Value("${test.val}")
    private String val;

    @Autowired(required = false)
    private PersonService personService;

    @Autowired
    private RedisService redisService;

    /*@Autowired
    private DemoServiceInterface demoServiceInterface;*/

    @Autowired
    private FeignServInterface feignServInterface;

    @ApiOperation(value = "client端测试")
    @GetMapping("/client/democlient/test/{name}")
    public ResultResponse<String> clientTest(@PathVariable("name") String name){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        log.info("thread local val : "+UserUtils.getUser()+" 线程："+Thread.currentThread().getId()+" 请求头参数："+request.getHeader("user"));

//        UserUtils.removeUser();

        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();

        LogAop logAop = applicationContext.getBean(LogAop.class);
        log.info("aop实例：{}",logAop.toString());
        String service = "";
        try {
            String str = feignServInterface.servTest(name).getData();
            service += str;
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResultResponse.success("clienttest para :"+name +". demoservice para : "+ service + ",当前配置文件数据：" + val);
    }

    @ApiOperation(value = "client端starter测试")
    @GetMapping("/client/democlient/starterTest")
    public ResultResponse<String> starterTest(){
        personService.sayHello();
        return ResultResponse.success("测试成功");
    }

    @ApiOperation(value = "client端redis锁测试")
    @GetMapping("/client/democlient/redis")
    @RedisLock(lockKey = "lockKey",expire = 60000)
    public ResultResponse<String> redis(String val) throws InterruptedException {
        redisService.set("testKey",val);
        TimeUnit.SECONDS.sleep(1L);
        return ResultResponse.success((String) redisService.get("testKey"));
    }

}
