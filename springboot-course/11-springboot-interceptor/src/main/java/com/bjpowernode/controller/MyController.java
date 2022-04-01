package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 18:58
 */
@Controller
@RequestMapping("/user")
public class MyController {

    @RequestMapping("/account")
    @ResponseBody
    public String toAccount(){
        return "user的account";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String toLogin(){
        return "user的login";
    }
}
