package com.bjpowernode.dubbo.service.impl;

import com.bjpowernode.dubbo.domain.User;
import com.bjpowernode.dubbo.service.UserService;

public class UserServiceImpl implements UserService {
    public User queryUserById(Integer id) {
        User user = new User();
        user.setName("李昊");
        user.setId(id);
        return user;
    }
}
