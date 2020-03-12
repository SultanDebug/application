package com.hzq.feign.service;

import com.hzq.feign.entity.User;
import com.hzq.feign.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.Unsafe;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-03
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Transactional()
    public User getById(Integer id){
        User user = userMapper.getUser(id);

        return user;
    }

}
