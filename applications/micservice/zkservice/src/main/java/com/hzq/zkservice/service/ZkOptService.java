package com.hzq.zkservice.service;

/**
 * zk基础api
 * @author Huangzq
 * @title: ZkOptService
 * @projectName applications
 * @date 2020/1/19 9:37
 */
public interface ZkOptService {
    String zkadd(String path ,String msg);
    String zkDel(String path);
    String zkUpdate(String path ,String msg);
    String zkQuery(String path);
    String zkWatch(String msg);
}
