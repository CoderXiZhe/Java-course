package com.bjpowernode.test;


import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import com.bjpowernode.service.impl.StudentServiceImpl;
import com.bjpowernode.util.ServiceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        StudentService ss  = (StudentService) ServiceFactory.getService(new StudentServiceImpl());
//        Student s = new Student("4",30,"zs");
//        ss.save(s);

//        Student  s = ss.getById("2");
//        System.out.println(s);

        //查询所有记录
        List<Student> sList = ss.getAll();
        for(Student s:sList) {
            System.out.println(s);
        }
    }

    /*
            为sql传值的几种方式

            domain 方式 (查询姓名为wyf 年龄为23岁的学员信息) -> domain 和 map都可
            map 方式(查询姓名为wyf 班级为一年一班) ->map方式
     */

        /*
                like模糊查询  :  '%' #{value} '%'
                      sql中空格作为拼接字符串
         */

        /*
                需求:根据姓名分组 查询出每一个姓名对应的数量
                叫wyf的多少人
                叫lh的多少人

                select
                name,count(*)
                from tbl_student
                group by name

                对于以上查询结果 使用domain不能作为封装查询结果
                因为domain有name属性 但是没有count属性
                使用map返回一定可以查询到结果
         */

        /*
                15.resultType 当数据库表字段名称和domain类属性名称不一致时候 方式1：起别名
                如 ： fullname as name
         */
}

