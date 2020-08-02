package com.hzq.common.utils;

/**
 * token参数透传
 * @author Huangzq
 * @title: UserUtils
 * @projectName applications
 * @date 2019/11/22 10:08
 */
public class UserUtils {
    private static final ThreadLocal<String> user = new InheritableThreadLocal();

    public static String getUser() {
        return user.get();
    }

    public static void setUser(String user) {
        UserUtils.user.set(user);
    }

    public static void removeUser() {
        UserUtils.user.remove();
    }
}
