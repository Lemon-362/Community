package com.lemon.community.service;

import com.lemon.community.entity.User;
import com.lemon.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO 4.2.2 UserService
 * @Author Lemon
 * @Date 20.6.21 021 15:43:37
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    // 查询一个用户 User selectById(int id);
    public User findUserById(int id){
        return userMapper.selectById(id);
    }
}
