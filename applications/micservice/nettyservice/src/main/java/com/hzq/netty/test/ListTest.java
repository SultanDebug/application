package com.hzq.netty.test;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-08
 */
public class ListTest {
    public static void test(String[] args) {
        /*List<String> list = new ArrayList<>(1);

        list.add("hzq");

        list.add("sultan");*/

        int i = 10;

        int k = 0;

        for (int j = 0 ; j<5 ; j++){

            k = i>>1;
            i = i + k;
            System.out.println(Integer.toBinaryString(i));

            System.out.println(j+" time :"+i);
        }

        /*Map<String,String> map = new HashMap<>(1);
        map.put("aaa","hzq");
        map.put("bbb","sss");

        System.out.println(map);*/


    }
}
