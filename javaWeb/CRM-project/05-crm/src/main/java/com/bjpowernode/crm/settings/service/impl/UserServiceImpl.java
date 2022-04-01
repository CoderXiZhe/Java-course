package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.exception.LoginException;
import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.utils.DateTimeUtil;
import com.bjpowernode.crm.utils.SqlSessionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    public User login(String loginAct,String loginPwd,String ip) throws LoginException {
        Map<String,String> map = new HashMap<String, String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User user = userDao.login(map);
        if(user == null) {
            //验证账号密码是否错误
            throw new LoginException("账号密码错误");
        }
        //执行到这里 表示账号密码正确 此时user不空
        String expireTime = user.getExpireTime();//失效时间
        String currentTime = DateTimeUtil.getSysTime();//当前时间
        String lockState = user.getLockState();//锁定状态
        String allowIps = user.getAllowIps();//获取授权ip

        if(expireTime.compareTo(currentTime)<0) {
            //验证账号是否失效
            throw new LoginException("账号已失效,请联系管理员");
        }

        if("0".equals(lockState)){
            throw new LoginException("账号已被锁定,请联系管理员");
        }

        if(!allowIps.contains(ip)){
            throw new LoginException("ip受限,请联系管理员");
        }


        return user;
    }

    public List<User> getUserList() {
        List<User> uList = userDao.getUserList();

        return uList;
    }
}
