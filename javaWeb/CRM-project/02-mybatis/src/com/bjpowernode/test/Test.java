package com.bjpowernode.test;


import com.bjpowernode.domain.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*
                SqlSessionFactoryBuilder:SqlSessionFactory的建造者
                SqlSessionFactory：为我们创建SqlSession对象
         */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //都是用session来完成增删改查 处理事务
        SqlSession session = sqlSessionFactory.openSession();
        /*
            需求：根据id查询单条
                调用selectOne方法
             参数1：根据命名空间.sqlId的形式找到我们需要的sql语句
             参数2:我们要为sql语句传递的参数

         */
//        Student student = session.selectOne("test.getById","2");
//        System.out.println(student);
//        session.close();


        // 查询所有记录
//        List<Student> sList = session.selectList("test.getAll");
//        for(Student s :sList) {
//            System.out.println(s);
//        }
//        session.close();


            /*
                MyBatis默认情况下手动提交事务
             */
        //添加操作
//        Student s = new Student("4",24,"cxk");
//        int count = session.insert("test.save",s);
//        session.commit();
//        session.close();


        //修改操作
//        Student s = new Student(4,26,"cxk");
//        int count = session.update("test.update",s);
//        session.commit();
//        session.close();

        //删除操作
        session.delete("test.delete",4);
        session.commit();
        session.close();
    }
}
