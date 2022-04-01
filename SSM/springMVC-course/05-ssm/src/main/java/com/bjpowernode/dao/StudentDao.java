package com.bjpowernode.dao;


import com.bjpowernode.domain.Student;

import java.util.List;

public interface StudentDao {
    int addStudent(Student student);

    List<Student> findStudent();
}
