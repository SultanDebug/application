package com.hzq.algo.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Huangzq
 * @description
 * @date 2023/2/23 11:21
 */
public class RomanInt {
    public static void main(String[] args) {
        System.out.println(romanToInt("CMXCIX"));
    }
    public static int romanToInt(String s) {
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        char[] chars = s.toCharArray();
        char pre = ' ';
        int res = 0;
        for (char aChar : chars) {
            Integer preVal = map.get(pre);
            if(preVal!=null && preVal<map.get(aChar)){
                res = res-preVal;
                res += (map.get(aChar)-preVal);
            }else{
                res += map.get(aChar);
            }
            pre = aChar;
        }
        return res;
    }
}
