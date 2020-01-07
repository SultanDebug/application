package com.hzq.democlient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @ClassName OcrReqDTO
 * @Description
 * @Author zhengzhouyang
 * @Date 2019/9/16 17:00
 * @Version 1.0
 */
@ApiModel(value = "SocketReqDTO",description = "socket请求参数")
@Data
@ToString
public class SocketReqDTO implements Serializable {
    private static final long serialVersionUID = 5060041435197104001L;

    @ApiModelProperty(value = "用户")
    private String cid;

    @ApiModelProperty(value = "服务")
    private String server;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "连接状态")
    private Boolean status;



}
