package com.hzq;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.junit.Test;

import java.util.List;

/**
 * @author Huangzq
 * @description
 * @date 2024/1/15 15:27
 */
public class FuncTest {
    @Test
    public void sort() {
        List<Term> terms = HanLP.segment("查尔高地Floor Crown House");
        System.out.println(terms);
    }
}
