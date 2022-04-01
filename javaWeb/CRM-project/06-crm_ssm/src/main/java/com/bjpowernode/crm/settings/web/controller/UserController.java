package com.bjpowernode.crm.settings.web.controller;


import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }


    @RequestMapping("/settings/qx/user/login.do")  //具体指向对应的返回页面的路径
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response,
                        String loginAct, String loginPwd, String isRemPwd){
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userService.queryByLoginActAndPwo(map);
        ReturnObject returnObject = new ReturnObject();
        returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);//封装 使用常量
        if(user==null){
            //用户名和密码错误
            returnObject.setMessage("用户名或密码错误");
        }else{
            //工具类进行封装
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String nowStr =sdf.format(new Date());

            String nowStr =DateUtils.formatDateTime(new Date());
            if(nowStr.compareTo(user.getExpireTime()) >0){
                //登录失败,账号已过期
                returnObject.setMessage("登录失败,账号已过期");
            }else if("0".equals(user.getLockState())){
                //登录失败,账号被锁定
                returnObject.setMessage("登录失败,账号被锁定");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){
                //登录失败,ip地址受限制
                returnObject.setMessage("登录失败,ip地址受限制");
            }else{
                //登录成功
                request.getSession().setAttribute(Contants.SESSION_USER,user);
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);

                //保存密码 写cookie
                if("true".equals(isRemPwd)){
                    Cookie c1 = new Cookie("loginAct",user.getLoginAct());
                    c1.setMaxAge(10*24*60*60);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd",user.getLoginPwd());
                    c2.setMaxAge(10*24*60*60);
                    response.addCookie(c2);
                }else{
                    //删除没有过期的cookie
                    Cookie c1 = new Cookie("loginAct","1");
                    c1.setMaxAge(0);
                    response.addCookie(c1);
                    Cookie c2 = new Cookie("loginPwd","1");
                    c2.setMaxAge(0);
                    response.addCookie(c2);
                }

            }
        }
        return returnObject;
    }

    @RequestMapping("/settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpServletRequest request){
        //清空cookie
        Cookie c1 = new Cookie("loginAct","1");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2 = new Cookie("loginPwd","1");
        c2.setMaxAge(0);
        response.addCookie(c2);
        //销毁session
        request.getSession().invalidate();
        //跳转到首页
        return "redirect:/";
    }

}
