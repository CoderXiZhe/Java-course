package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.settings.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User queryByLoginActAndPwo(Map<String,Object> map);
    List<User> QueryAllUser();
}
