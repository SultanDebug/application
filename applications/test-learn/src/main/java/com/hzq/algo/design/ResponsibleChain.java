package com.hzq.algo.design;

import com.google.common.collect.Lists;
import com.hzq.algo.design.respchain.Chain1;
import com.hzq.algo.design.respchain.Chain2;
import com.hzq.algo.design.respchain.Chain3;
import com.hzq.algo.design.respchain.RespChainAbstract;

/**
 * @author Huangzq
 * @description
 * @date 2022/10/15 16:05
 */
public class ResponsibleChain {
    public static void main(String[] args) {
        RespChainAbstract<Integer,Integer> r1 = new Chain1();
        RespChainAbstract<Integer,Integer> r2 = new Chain2();
        RespChainAbstract<Integer,Integer> r3 = new Chain3();

        r1.next(r2);
        r2.next(r3);

        r1.doCall(1, Lists.newArrayList());
    }
}
