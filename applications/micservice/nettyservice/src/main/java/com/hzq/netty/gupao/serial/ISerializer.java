package com.hzq.netty.gupao.serial;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-22
 */
public interface ISerializer {
    <T> byte[] serialObj(T obj);

    <T> T deSerialObj(byte[] bytes);
}
