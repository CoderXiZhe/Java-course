package com.bjpowernode.service;


import com.bjpowernode.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface StudentService {

    boolean add(Student student);

    List<Student> findAll();
}
