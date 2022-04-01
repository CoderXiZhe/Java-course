package com.bjpowernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/25 18:11
 */

@RestController   //返回的都是数据
public class MyRestController {

    //查询id是1001的学生
    @GetMapping("/student/{stuId}")
    public String queryStudent(@PathVariable(value = "stuId") Integer id){
        return "查询学生id=" + id;
    }

    @PostMapping("/student/{name}/{age}")
    public String addStudent(@PathVariable("name") String name,
                             @PathVariable("age") Integer age){
        return "添加学生" + "name=" + name + ",age=" + age;
    }

    /**
     *
     * @param id
     * @param age
     * @return
     * 当路径名和形参名一样  @PathVariable中的value可省略
     */
    @PutMapping("/student/{id}/{age}")
    public String modifyStudent(@PathVariable Integer id,
                             @PathVariable Integer age){
        return "修改学生" + "id=" + id + ",age=" + age;
    }

    @DeleteMapping("/student/{id}")
    public String deleteStudent(@PathVariable Integer id){
        return "删除学生" + "id=" + id ;
    }

    @PutMapping("/student/test")
    public String test(){
        return "test测试";
    }
}
