package com.hzq.controller;

import com.hzq.flow.ChainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Huangzq
 * @description
 * @date 2022/11/10 17:19
 */
@RestController
@Slf4j
public class TestController {

    @Resource
    private ChainService chainService;

    @GetMapping("/test")
    public String getTest(){
        chainService.chain1();
        return "success";
    }
}
