package com.hzq.feign.service;

import com.hzq.feign.entity.User;
import com.hzq.feign.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-03
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getById(Integer id){
        User user = userMapper.getUser(id);

        return user;
    }
}
