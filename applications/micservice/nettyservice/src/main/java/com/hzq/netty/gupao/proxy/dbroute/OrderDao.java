package com.hzq.netty.gupao.proxy.dbroute;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
public class OrderDao {
    public int insert(Order order){
        System.out.println("插入一个订单");
        return 1;
    }
}
