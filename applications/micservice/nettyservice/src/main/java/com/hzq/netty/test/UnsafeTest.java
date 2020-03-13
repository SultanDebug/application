package com.hzq.netty.test;

import sun.misc.Unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-07
 */
public class UnsafeTest implements Serializable {
    private static final long serialVersionUID = 7267444210144747328L;

    private static Unsafe unsafe ;
    private static long valueOffset;
    private static UnsafeTest theTest;

    static {
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);



            Field field = UnsafeTest.class.getDeclaredField("val");

            valueOffset = unsafe.objectFieldOffset(field);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private volatile int val;

    public static void test(String[] args) {

//        UnsafeTest u = new UnsafeTest();

        try {
            UnsafeTest o = UnsafeTest.class.newInstance();


            int oldVal = 1,newVal = 3;

            int res = o.cas(oldVal,newVal);

            System.out.println(res);

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

//        int a = u.getAndSet(2);
//        System.out.println(a);



    }

    public int cas(int oldVal , int newVal){
        if(unsafe.compareAndSwapInt(this,valueOffset,oldVal,newVal)){
            return newVal;
        }else{
            return oldVal;
        }
    }

    public int getAndSet(int a){
        return unsafe.getAndSetInt(this,valueOffset,a);
    }
}
