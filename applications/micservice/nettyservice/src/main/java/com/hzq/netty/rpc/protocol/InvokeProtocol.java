package com.hzq.netty.rpc.protocol;

import lombok.Data;

import java.io.Serializable;

@Data
public class InvokeProtocol implements Serializable {

    private String className;

    private String method;

    private Class<?> [] paras;

    private Object[] values;

}
