package com.hzq.algo.design.respchain;

import java.util.List;

/**
 * @author Huangzq
 * @description
 * @date 2022/10/15 16:06
 */
public abstract class RespChainAbstract<R,Q> {
    /*
     * 下一个节点
     * */
    private RespChainAbstract<R,Q> next;

    public void next(RespChainAbstract<R,Q> next) {
        this.next = next;
    }

    /**
     * Description:
     * 节点转移
     *
     * @author Huangzq
     * @date 2022/10/18 11:53
     */
    public void doCall(Q req, List<R> results) {
        //先执行再验证或者先验证再执行
        R session = this.doHandle(req);
        results.add(session);
        if (this.check(session)) {
            next.doCall(req, results);
        }
    }

    /**
     * Description:
     * 业务判断，节点是否终止 true继续  false 终止
     *
     * @author Huangzq
     * @date 2022/10/18 11:54
     */
    abstract boolean check(R result);

    /**
     * Description:
     * 业务逻辑
     *
     * @author Huangzq
     * @date 2022/10/18 14:02
     */
    abstract R doHandle(Q req);
}
