package com.hzq.algo.design.respchain;

/**
 * @author Huangzq
 * @description
 * @date 2022/10/17 11:43
 */
public class Chain2 extends RespChainAbstract<Integer,Integer>{
    @Override
    boolean check(Integer result) {
        return result > 5;
    }

    @Override
    Integer doHandle(Integer req) {
        System.out.println("chain2");
        return 4;
    }
}
