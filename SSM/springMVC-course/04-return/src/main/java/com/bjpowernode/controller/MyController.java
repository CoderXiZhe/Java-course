package com.bjpowernode.controller;


import com.bjpowernode.vo.Student;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @RequestMapping :
 *      value:所有请求地址的公共部分 叫做模块名称
 *      位置：放在类的上面
 *
 */

@Controller
public class MyController {


    /**
     *  处理器方法返回string--表示逻辑视图名称，需要配置视图解析器
     */
    @RequestMapping(value = "/returnString-view.do")
    public String doSome(HttpServletRequest request,String name,Integer age){

        //可以手动添加数据到request域
        request.setAttribute("myName",name);
        request.setAttribute("myAge",age);
        // show :逻辑视图名称,项目中配置了视图解析器
        // 框架对视图执行forward转发操作
        return "show";
    }

    @RequestMapping(value="/returnVoid-ajax.do")
    public void doSome2(HttpServletResponse response,String name,Integer age) throws IOException {

        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        String json = "";
        if(student != null) {
            ObjectMapper om  = new ObjectMapper();
            json = om.writeValueAsString(student);
        }
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }

    /**
     *处理器方法返回一个student，通过框架转为json ,响应ajax请求
     * @ResponseBody:
     * 作用:把处理器方法返回对象转为json后，通过HttpservletResponse输出给浏览器。
     * 位置:方法的定义上面。和其它注解没有顺序的关系
     *
     * 1. 框架会把返回student类型，调用框架的中ArrayList<HttpMessageConverter>中每个类的canwrite()方法
     *  检查那个HttpMessageConverter接口的实现类能处理Student类型的数据--Mappingackson2HttpMessageconverter
     *
     * 2. 框架会调用实现类的write( ) ,MappingJackson2HttpMessageConverter的write()方法
     * 把李四同学的student对象转为json ，调用jackson 的objectMapper实现转为json
     *
     * 3.框架会调用@ResponseBody把2的结果数据输出到浏览器，ajax请求处理完成
     *
     */
//    @RequestMapping(value="/returnStudentJson.do")
//    @ResponseBody
//    public Student doStudentJsonObject(String name,Integer age){
//        Student student = new Student();
//        student.setName("李四同学");
//        student.setAge(20);
//        return student;//会被框架转为json
//    }
    @RequestMapping(value="/returnStudentJson.do")
    @ResponseBody
    public Student doStudentJsonObject(Student student){
        return student;//会被框架转为json
    }


    /**
     * 处理器返回List<Student>
     */

    @RequestMapping(value = "/returnStudentJsonArray.do")
    @ResponseBody
    public List<Student> doStudentJsonObjectArray(String name, Integer age){

        List<Student> sList = new ArrayList<>();
        Student student = new Student();
        student.setName("李四");
        student.setAge(55);
        sList.add(student);

        student = new Student();
        student.setName(name);
        student.setAge(age);
        sList.add(student);
        return sList;
    }


    /**
     *  处理器方法返回的是string , string表示数据的，不是视图。
     * 区分返回值string是数据，还是视图，看有没有@ResponseBody注解
     * 如果有@ResponseBody注解，返回string就是数据，反之就是视图
     *
     * 中文乱码： iso-8859
     * 解决方案: 给RequestMapping增加一个属性 produces，使用这个属性指定新的contentType.
     */
    @RequestMapping(value = "/returnStringData.do",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String doStringData(String name,Integer age) {

        return "helloworld 哈哈哈";
    }


    @ResponseBody
    @RequestMapping(value ="/returnMapData.do")
    public Map<String,Object> doMapData(String name,Integer age){
        List<Student> sList = new ArrayList<>();
        Student student = new Student();
        student.setName("张三");
        student.setAge(33);
        sList.add(student);

        student = new Student();
        student.setAge(44);
        student.setName("李四");
        sList.add(student);

        Boolean success = true;

        Map<String,Object> map = new HashMap<>();
        map.put("sList",sList);
        map.put("success",success);

        return map;
    }

    @RequestMapping(value="/returnFlagData.do")
    @ResponseBody
    public boolean doBooleanData(){
        return true;
    }
}
