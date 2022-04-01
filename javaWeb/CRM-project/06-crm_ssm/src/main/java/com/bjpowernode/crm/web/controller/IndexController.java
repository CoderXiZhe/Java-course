package com.bjpowernode.crm.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /*
           规定用"/" 代替"//localhost:8080/crm_ssm"
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
