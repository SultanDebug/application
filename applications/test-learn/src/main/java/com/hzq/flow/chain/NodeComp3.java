package com.hzq.flow.chain;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Huangzq
 * @description
 * @date 2022/11/10 17:32
 */
@Component("node3")
@Slf4j
public class NodeComp3 extends NodeComponent {
    @Override
    public void process() throws Exception {
        log.info("节点3执行");
    }
}
