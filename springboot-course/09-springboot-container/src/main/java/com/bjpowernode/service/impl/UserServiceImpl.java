package com.bjpowernode.service.impl;

import com.bjpowernode.service.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 18:05
 */

@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        return "执行了业务方法,你好"+name;
    }
}
