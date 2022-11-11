package com.hzq.flow.chain;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Huangzq
 * @description
 * @date 2022/11/10 17:32
 */
@Component("node1")
@Slf4j
public class NodeComp1 extends NodeComponent {
    @Override
    public void process() throws Exception {
        log.info("节点1执行");
    }
}
