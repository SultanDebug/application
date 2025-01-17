package com.hzq.kafkaservice.controller;

import com.hzq.common.aop.ResultResponse;
import com.hzq.kafkaservice.service.ProviderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发布
 * @author Huangzq
 * @title: KafkaController
 * @projectName zhengtyun-saas
 * @date 2019/8/31 15:13
 */
@RestController
@Slf4j
@Api(description = "(Huang)kafka消息服务")
public class KafkaController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/kafka/provider")
    @ApiOperation(value = "消息生产")
    public ResultResponse<String> confirmOrder(@RequestParam("para") String para){

        providerService.sendMsg(para);

        return ResultResponse.success("success");
    }

}
