package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/27 13:29
 */

@Controller
public class JspController {

    @RequestMapping("/main")
    public String main(Model model){
        model.addAttribute("data","SpringBoot打包为war文件");
        return "index";
    }
}
