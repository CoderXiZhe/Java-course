package com.bjpowernode.controller;

import com.bjpowernode.model.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/28 17:56
 */

@Controller
@RequestMapping("/tpl")
public class MyController {

    @GetMapping("/test")
    public String test(HttpServletRequest request) {
        request.setAttribute("data", "使用thymeleaf模板");
        return "hello";
    }

    //标准变量表达式
    @GetMapping("/exp1")
    public String exp1(HttpServletRequest request) {
        User u = new User();
        u.setAge(22);
        u.setId(1001);
        u.setName("张三");
        request.setAttribute("user", u);
        request.setAttribute("site", "http://www.thymeleaf.org");
        return "exp1";
    }

    //选择变量表达式
    @GetMapping("/exp2")
    public String exp2(HttpServletRequest request) {
        User u = new User();
        u.setAge(23);
        u.setId(1002);
        u.setName("李四");
        request.setAttribute("user", u);
        request.setAttribute("site", "http://www.thymeleaf.org");
        return "exp2";
    }


    //链接表达式
    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("id", 1001);
        model.addAttribute("age", 22);
        model.addAttribute("name", "张三");
        return "link";
    }

    //
    @GetMapping("/query1")
    @ResponseBody
    public String query1(Integer id) {
        return "链接到相对地址,没有参数id=" + id;
    }

    @GetMapping("/query2")
    @ResponseBody
    public String query2(String name, Integer age, Model model) {

        return "链接到相对地址,有参数name=" + name + ",age=" + age;
    }

    //html-property
    @GetMapping("/property")
    public String property(Model model) {
        model.addAttribute("methodAttr", "post");
        model.addAttribute("name", "李四");
        return "html-property";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String name) {
        return "登录成功,name=" + name;
    }

    @GetMapping("/each")
    public String each(Model model){
        List<User> uList = new ArrayList();
        uList.add(new User(1001,"张三",22));
        uList.add(new User(1002,"李四",21));
        uList.add(new User(1003,"王五",22));
        model.addAttribute("userList",uList);
        return "eachList";
    }

    @GetMapping("/each2")
    public String each2(Model model){
        User user[] = new User[3];
        user[0] = new User(1001,"李红",21);
        user[1] = new User(1002,"王敏",20);
        user[2] = new User(1003,"张超",19);
        model.addAttribute("userArray",user);
        return "eachArray";
    }

    @GetMapping("/each3")
    public String each3(Model model){
        Map<String,User> uMap = new HashMap();
        uMap.put("user1",new User(11001,"李红",21));
        uMap.put("user2",new User(11002,"王旺",21));
        uMap.put("user3",new User(11003,"赵柳",22));
        model.addAttribute("userMap",uMap);
        return "eachMap";
    }

    @GetMapping("/each4")
    public String each4(Model model){
        List<Map<String,User>> lm = new ArrayList<>();
        Map<String,User> uMap = new HashMap();
        uMap.put("user1",new User(11001,"李红",21));
        uMap.put("user2",new User(11002,"王旺",21));
        uMap.put("user3",new User(11003,"赵柳",22));
        lm.add(uMap);
        model.addAttribute("listMap",lm);
        return "eachMap";
    }

    @GetMapping("/ifunless")
    public String ifunless(Model model){
        model.addAttribute("name","张三");
        model.addAttribute("age",20);
        model.addAttribute("isLogin",true);
        model.addAttribute("sex","m");
        model.addAttribute("address","");
        model.addAttribute("wife",null);
        return "ifunless";
    }
    @GetMapping("/switch")
    public String switchTest(Model model){
        model.addAttribute("sex","m");
        return "switch";
    }

    @GetMapping("/inline")
    public String inline(Model model){
        model.addAttribute("name","张三");
        model.addAttribute("sex","男");
        return "inline";
    }

    @GetMapping("/ysf")
    public String ysf(Model model){
        model.addAttribute("name","张三");
        model.addAttribute("sex","男");
        model.addAttribute("age",20);
        model.addAttribute("isLogin",true);
        model.addAttribute("wife",null);
        return "ysf";
    }

    @GetMapping("/baseObject")
    public String baseObject(Model model, HttpServletRequest request, HttpSession session){
        model.addAttribute("name","张三");
        request.setAttribute("requestData","request作用域数据");
        request.getSession().setAttribute("sessionData","session作用域数据");

        session.setAttribute("loginAct","张三");//等价
        return "baseObject";
    }

    @GetMapping("/utilObject")
    public String utilObject(Model model){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("c");
        list.add("d");
        model.addAttribute("mydate",new Date());
        model.addAttribute("mynum",567);
        model.addAttribute("mystr","bjpowernode");
        model.addAttribute("mylist",list);
        model.addAttribute("user",null);
        return "utilObject";
    }
}
