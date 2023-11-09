package com.hzq.common.load;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Huangzq
 * @description
 * @date 2023/11/7 17:07
 */
public abstract class AbsBean {
    @Autowired
    private ABean aBean;
}
