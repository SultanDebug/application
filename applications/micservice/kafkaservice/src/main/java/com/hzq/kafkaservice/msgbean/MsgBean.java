package com.hzq.kafkaservice.msgbean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息实体
 * @author Huangzq
 * @title: MsgBean
 * @projectName zhengtyun-saas
 * @date 2019/8/29 16:50
 */
@Data
public class MsgBean implements Serializable {
    private static final long serialVersionUID = 468232647721621191L;

    private Long id;

    private String msg;

    private Date sendTime;

}
