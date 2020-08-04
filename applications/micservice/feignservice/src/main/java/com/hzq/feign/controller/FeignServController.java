package com.hzq.feign.controller;

import com.alibaba.fastjson.JSONObject;
import com.hzq.common.aop.ResultResponse;
import com.hzq.common.utils.UserUtils;
import com.hzq.feign.service.UserService;
import com.hzq.feignservice.FeignServInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * feign服务提供
 * 远程配置
 * 参数透传
 * mybatis测试
 * @author Huangzq
 * @title: FeignServController
 * @projectName applications
 * @date 2019/11/20 15:59
 */
@RestController
@Api(tags = "feign服务端测试接口")
@RefreshScope
@Slf4j
public class FeignServController implements FeignServInterface {
    @Value("${test.val}")
    private String val;

    @Autowired
    private UserService userService;

    @Override
    @ApiOperation(value = "feign-service接口")
    public ResultResponse<String> servTest(@PathVariable("para") String para) {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        log.info("threadLocal : "+ UserUtils.getUser()+" 线程："+Thread.currentThread().getId()+" 请求头参数："+request.getHeader("user"));
        return ResultResponse.success("服务端调用成功："+val);
    }

    @Override
    @ApiOperation(value = "feign-service数据库测试接口")
    public ResultResponse<String> feignSevDb(@PathVariable("id") Integer id) {
        return ResultResponse.success(JSONObject.toJSONString(userService.getById(id)));
    }
}
