package com.hzq.algo.design.respchain;

/**
 * @author Huangzq
 * @description
 * @date 2022/10/17 11:43
 */
public class Chain1 extends RespChainAbstract<Integer,Integer>{
    @Override
    boolean check(Integer result) {
        return result > 1;
    }

    @Override
    Integer doHandle(Integer req) {
        System.out.println("chain1");
        return 2;
    }
}
