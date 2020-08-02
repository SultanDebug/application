package com.hzq.feign.mapper;


import com.hzq.feign.entity.User;

/**
 * 用户
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-28
 */
public interface UserMapper {
    User getUser(Integer id);
    Integer updateUser(Integer id, String name);
}
