package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;

import java.util.List;

public interface StudentDao {


    List<Student> getAll();

    int save(Student student);
}
