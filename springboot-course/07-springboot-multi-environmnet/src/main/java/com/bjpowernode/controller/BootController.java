package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 16:21
 */

@Controller
public class BootController {

    @RequestMapping("/doSome")
    @ResponseBody
    public String doSome(){
        return "springboot多环境配置";
    }
}
