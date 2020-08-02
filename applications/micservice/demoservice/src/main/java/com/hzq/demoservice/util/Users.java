package com.hzq.demoservice.util;

import java.io.Serializable;

/**
 * 测试bean
 * @author Huangzq
 * @title: Users
 * @projectName applications
 * @date 2019/7/10 10:03
 */
public class Users implements Serializable {
    private static final long serialVersionUID = 6166135073988990752L;
    private String userName;
    private Integer id;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
