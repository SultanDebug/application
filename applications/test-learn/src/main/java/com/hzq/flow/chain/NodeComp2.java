package com.hzq.flow.chain;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Huangzq
 * @description
 * @date 2022/11/10 17:32
 */
@Component("node2")
@Slf4j
public class NodeComp2 extends NodeComponent {
    @Override
    public void process() throws Exception {
        log.info("节点2执行");
    }
}
