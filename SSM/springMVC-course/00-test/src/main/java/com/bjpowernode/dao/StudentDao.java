package com.bjpowernode.dao;


import com.bjpowernode.domain.Student;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface StudentDao {
    int add(Student student);

    List<Student> findAll();
}
