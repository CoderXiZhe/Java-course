package com.bjpowernode.service.impl;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public boolean addStudent(Student student) {
        boolean flag = true;
        int count = studentDao.addStudent(student);
        if(count!=1){
            flag = false;
        }
        return flag;
    }

    @Override
    public List<Student> findStudent() {

        List<Student> sList = studentDao.findStudent();
        return sList;
    }
}
