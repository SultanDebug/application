package com.hzq.netty.gupao.proxy.dynamicproxy.jdkproxy;

import com.hzq.netty.gupao.proxy.dynamicproxy.Person;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
public class Girl implements Person {
    @Override
    public void findLove() {
        System.out.println("要求很高！");
    }
}
