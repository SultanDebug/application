package com.hzq.netty.gupao.proxy.dbroute.db;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
public class DynamicDatasourceEntity {
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();

    private DynamicDatasourceEntity(){}

    public static String getLocal(){
        return LOCAL.get();
    }

    public static void reset(){
        LOCAL.set(null);
    }

    public static void setLocal(String local){
        LOCAL.set(local);
    }

    public static void setLocal(Integer year){
        LOCAL.set("DB_"+year);
    }

}
