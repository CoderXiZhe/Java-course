package com.bjpowernode.controller;

import com.bjpowernode.vo.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/23 16:55
 */

@Controller
public class MyController {

    @Value("${server.port}")
    private String sort;
    @Value("${server.servlet.context-path}")
    private String path;
    @Value("${school.name}")
    private String schoolName;
    @Value("${school.address}")
    private String address;

    @Resource
    private School school;

    @ResponseBody
    @RequestMapping("/doSome")
    public String doSome(){
        return "sort="+sort + " path=" +path + " school" +schoolName + "address=" + address;
    }

    @ResponseBody
    @RequestMapping("/doSome1")
    public String doSome1(){
        return school.toString();
    }
}
