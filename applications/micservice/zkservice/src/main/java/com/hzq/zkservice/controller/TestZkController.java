package com.hzq.zkservice.controller;

import com.hzq.zkcore.service.ZkService;
import com.hzq.zkservice.service.ZkOptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * zk测试
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

    @Autowired
    private ZkOptService zkOptService;

    @ApiOperation("zk测试")
    @GetMapping("/zk")
    public String testZk(@RequestParam("msg") String msg){
        try {
            String str = zkService.set("/hzq","/",msg);
            log.info("插入结果："+str);

            String str1 = zkService.get("/hzq/0000000002");
            log.info("获取结果："+str1);

            List<String> result = zkService.getAllNode("/hzq");

            for (String s : result) {
                log.info("获取{}结果：{}",s,zkService.get("/hzq/"+s));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @ApiOperation("zk新增")
    @GetMapping("/zkadd")
    public String zkadd(@RequestParam("path") String path ,@RequestParam("msg") String msg){
        return zkOptService.zkadd(path,msg);
    }

    @ApiOperation("zk删除")
    @GetMapping("/zkDel")
    public String zkDel(@RequestParam("path") String path){
        return zkOptService.zkDel(path);
    }

    @ApiOperation("zk更新")
    @GetMapping("/zkUpdate")
    public String zkUpdate(@RequestParam("path") String path ,@RequestParam("msg") String msg){
        return zkOptService.zkUpdate(path,msg);
    }

    @ApiOperation("zk查询")
    @GetMapping("/zkQuery")
    public String zkQuery(@RequestParam("path") String path){
        return zkOptService.zkQuery(path);
    }

    @ApiOperation("zk监视测试")
    @GetMapping("/zkWatch")
    public String zkWatch(@RequestParam("path") String path){
        return zkOptService.zkWatch(path);
    }
}
