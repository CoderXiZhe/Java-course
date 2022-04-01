package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 15:49
 */

@Controller
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String test(){
        return  "你好,Springboot";
    }

}
