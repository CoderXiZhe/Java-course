package com.bjpowernode.test;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.utils.SqlSessionUtil;

import java.util.List;

public class test {
    public static void main(String[] args) {

        StudentDao studentDao = SqlSessionUtil.getSqlSession().getMapper(StudentDao.class);
        List<Student> sList = studentDao.select();

        for(Student student:sList){
            System.out.println(student);
        }

    }
}
