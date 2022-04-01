package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 20:32
 */

@Controller
public class MyController {

    @RequestMapping("/user/account")
    @ResponseBody
    public String userAccount(){
        return "user/account";
    }

    @RequestMapping("/query")
    @ResponseBody
    public String userQuery(){
        return "query";
    }
}
