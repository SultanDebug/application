package com.hzq.flow;

import com.xiaoleilu.hutool.json.JSONUtil;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Huangzq
 * @description
 * @date 2022/11/10 17:31
 */
@Service
@Slf4j
public class ChainService {
    @Resource
    private FlowExecutor flowExecutor;

    public void chain1(){
        LiteflowResponse chain1 = flowExecutor.execute2Resp("chain1");
        log.info("执行结果{}", JSONUtil.toJsonStr(chain1));
    }
}
