package com.hzq.zkservice.controller;

import com.hzq.zkcore.service.ZkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huangzq
 * @title: TestZkController
 * @projectName applications
 * @date 2019/12/13 17:03
 */
@RestController
@Api(tags = "zk测试")
@Slf4j
public class TestZkController {
    @Autowired
    private ZkService<String> zkService;

    @ApiOperation("zk测试")
    @GetMapping("/zk")
    public String testZk(@RequestParam("msg") String msg){
        try {
            String str = zkService.set("/hzq","/first",msg);
            log.info("插入结果："+str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}
