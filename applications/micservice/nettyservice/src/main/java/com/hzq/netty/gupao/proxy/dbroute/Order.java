package com.hzq.netty.gupao.proxy.dbroute;

import lombok.Data;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
@Data
public class Order {
    private Object orderInfo;
    private Long createTime;
    private String id;

}
