package com.hzq.netty.gupao.proxy.dbroute;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    public OrderServiceImpl() {
        this.orderDao = new OrderDao();
    }

    @Override
    public int insertOrder(Order order) {
        return orderDao.insert(order);
    }
}
