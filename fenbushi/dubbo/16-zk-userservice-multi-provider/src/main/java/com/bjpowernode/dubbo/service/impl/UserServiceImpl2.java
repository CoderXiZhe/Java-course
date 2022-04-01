package com.bjpowernode.dubbo.service.impl;

import com.bjpowernode.dubbo.domain.User;
import com.bjpowernode.dubbo.service.UserService;

public class UserServiceImpl2 implements UserService {
    public User queryUserById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setName("张三" + "-2");
        return user;
    }
}
