package com.hzq.common.load;

import com.hzq.common.utils.SpringContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Huangzq
 * @description
 * @date 2023/11/7 17:07
 */
@Component("CBean")
public class CBean implements InitializingBean {
    @Resource(name = "aBean")
    private ABean aBean;

    private EBean eBean;

    public String test(String para) {
        System.out.println(aBean);
        System.out.println(eBean);
        return null;
    }

    public List<String> test1(String para) {
        return null;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        eBean = SpringContext.getBean("eBean");
    }
}
