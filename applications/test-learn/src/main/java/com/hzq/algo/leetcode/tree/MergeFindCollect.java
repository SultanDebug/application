
package com.hzq.algo.leetcode.tree;

import java.util.*;

/**
 * 并查集
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/1/20 10:44
 */
public class MergeFindCollect {

    public class MergeFindSimple{
        public int [] parent;
        public int [] rank;
        MergeFindSimple(int size){
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int merge(int x,int y){
            int i = find(x);
            parent[find(y)] = i;
            return i;
        }

        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }

    public class MergeFindUp{
        public int [] parent;
        public int [] rank;
        MergeFindUp(int size){
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int merge(int x,int y){
            int i = find(x);
            int j = find(y);
            if(rank[i]>rank[j]){
                parent[j] = i;
                return i;
            }else if(rank[i]==rank[j]){
                parent[j] = i;
                rank[i]+=1;
                return i;
            }else {
                parent[i] = j;
                return j;
            }
        }

        public int find(int x){
            if(parent[x] != x){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

    }



    /**
     * 721. 账户合并
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/1/21 16:09
    */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
//        MergeFindSimple mergeFindSimple = new MergeFindSimple(accounts.size());
        MergeFindUp mergeFindSimple = new MergeFindUp(accounts.size());

        //init
        for (int i = 0; i < accounts.size(); i++) {
            mergeFindSimple.parent[i] = i;
            mergeFindSimple.rank[i] = 1;
        }
        Map<String , Integer> map = new HashMap<>();
        for (int i =0;i<accounts.size();i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String s = accounts.get(i).get(j);
                if(map.get(s)==null){
                    map.put(s,i);
                }else{
                    int merge = mergeFindSimple.merge(i, map.get(s));
                }
            }
        }

        Map<Integer,List<String>> res = new HashMap<>();
        for (Map.Entry<String, Integer> stringIntegerEntry : map.entrySet()) {
            Integer id = mergeFindSimple.find(stringIntegerEntry.getValue());
            List<String> orDefault = res.getOrDefault(id, new ArrayList<>());
            orDefault.add(stringIntegerEntry.getKey());
            res.put(id,orDefault);
        }
        List<List<String>> result = new ArrayList<>();
        res.entrySet().forEach(o->{
            String name = accounts.get(o.getKey()).get(0);
            List<String> tmp = new ArrayList<>();
            tmp.add(name);
            Collections.sort(o.getValue());
            tmp.addAll(o.getValue());
            result.add(tmp);
        });

        return result;

    }

    public List<String> sortString(List<String> strings) {
        String[] arr = new String[strings.size()];
        String[] source = strings.toArray(arr);
        String str1 = "";
        String str2 = "";
        String temp = "";
        int length = 0;
        for (int i = 0; i < source.length; i++) {
            for (int m = 0; m < source.length - 1; m++) {
                str1 = source[m];
                str2 = source[m + 1];
                length = str1.length() > str2.length() ? str2.length() : str1.length();
                for (int j = 0; j < length; j++) {
                    if (str1.charAt(j) == str2.charAt(j)) {
                        continue;
                    } else if (str1.charAt(j) < str2.charAt(j)) {
                        break;
                    } else {
                        temp = str1;
                        source[m] = str2;
                        source[m + 1] = temp;
                    }
                }
            }

        }

        return Arrays.asList(source);
    }




    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("David", "David0@m.co", "David1@m.co"));
        accounts.add(Arrays.asList("David", "David3@m.co", "David4@m.co"));
        accounts.add(Arrays.asList("David", "David4@m.co", "David5@m.co"));
        accounts.add(Arrays.asList("David", "David2@m.co", "David3@m.co"));
        accounts.add(Arrays.asList("David", "David1@m.co", "David2@m.co"));

        /*List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John","johnnybravo@mail.com"));*/

        MergeFindCollect mergeFindCollect = new MergeFindCollect();
        System.out.println(mergeFindCollect.accountsMerge(accounts));
    }
}
