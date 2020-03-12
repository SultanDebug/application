package com.hzq.feign.test;

import sun.misc.Unsafe;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-07
 */
public class UnsafeTest implements Serializable {
    private static final long serialVersionUID = 7267444210144747328L;

    private static final Unsafe unsafe  =  Unsafe.getUnsafe();
    private static final long valueOffset;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset
                    (UnsafeTest.class.getDeclaredField("val"));
        } catch (Exception ex) { throw new Error(ex); }
    }

    private volatile int val;

    public static void main(String[] args) {

        UnsafeTest u = new UnsafeTest();
        int a = u.getAndSet(2);
        System.out.println();
    }

    public int getAndSet(int a){
        return unsafe.getAndSetInt(this,valueOffset,a);
    }
}
