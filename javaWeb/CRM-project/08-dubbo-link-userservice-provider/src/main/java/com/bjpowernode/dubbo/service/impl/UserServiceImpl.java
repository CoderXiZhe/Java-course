package com.bjpowernode.dubbo.service.impl;

import com.bjpowernode.dubbo.domain.User;
import com.bjpowernode.dubbo.service.UserService;

public class UserServiceImpl implements UserService {
    @Override
    public User queryUserById(Integer id) {
        User user = new User();
        user.setId(1001);
        user.setAge(23);
        user.setName("张三");
        return user;
    }
}
