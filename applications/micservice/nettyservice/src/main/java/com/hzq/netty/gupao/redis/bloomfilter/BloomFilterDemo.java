package com.hzq.netty.gupao.redis.bloomfilter;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.text.NumberFormat;
import java.util.*;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-04
 */
public class BloomFilterDemo {

    private static final int insertions = 1000000;

    public static void main(String[] args) {
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions);

        Set<String> sets = new HashSet<>(insertions);

        List<String> lists = new ArrayList<>(insertions);

        for (int i = 0 ; i < insertions ; i++){
            String s = UUID.randomUUID().toString();
            bloomFilter.put(s);
            sets.add(s);
            lists.add(s);

        }

        int right = 0;
        int wrong = 0;

        for (int j = 0 ; j < 10000 ; j++){
            String data = (j % 100 == 0 ? lists.get(j/100) : UUID.randomUUID().toString());

            if(bloomFilter.mightContain(data)){
                if(sets.contains(data)){
                    right++;
                    continue;
                }

                wrong++;
            }
        }

        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        float r = (float) (9900 - wrong) / 9900;
        float w = (float) wrong/9900;

        System.out.println("数据大小：sets："+sets.size()+",list:"+lists.size());
        System.out.println("100w数据判断100存在数据结果："+right);
        System.out.println("100w数据判断9900不存在数据，误认为存在："+wrong+
                ",命中率："+numberFormat.format(r)+"，错误率："+numberFormat.format(w));

    }
}
