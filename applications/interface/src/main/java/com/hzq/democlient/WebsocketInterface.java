package com.hzq.democlient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Huangzq
 * @title: ImageTransferInterface
 * @projectName zhengtyun-saas
 * @date 2019/7/26 18:48
 */
@FeignClient(contextId = "websocketservice",value = "socket-service")
@Api(tags = "websocket推送(Huangzq)")
public interface WebsocketInterface {
    String MAPPING = "/ws";

    @PostMapping(value = MAPPING + "/sender")
    @ApiOperation(value = "消息发送")
    String sender(@RequestBody SocketReqDTO socketReqDTO );
}
