package com.hzq.netty.gupao.proxy.dbroute.dbproxy;

import com.hzq.netty.gupao.proxy.dbroute.Order;
import com.hzq.netty.gupao.proxy.dbroute.OrderService;
import com.hzq.netty.gupao.proxy.dbroute.db.DynamicDatasourceEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
public class OrderProxy implements OrderService {

    private OrderService orderService;

    public OrderProxy(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public int insertOrder(Order order) {

        long time = order.getCreateTime();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");

        Integer year = Integer.parseInt(simpleDateFormat.format(new Date(time)));

        DynamicDatasourceEntity.setLocal(year);

        this.orderService.insertOrder(order);

        DynamicDatasourceEntity.reset();

        return 0;
    }
}
