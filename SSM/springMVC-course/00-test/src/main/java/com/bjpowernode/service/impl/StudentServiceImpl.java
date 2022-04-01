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
    public boolean add(Student student) {
        int count = studentDao.add(student);
        if(count!=1){
            return false;
        }
        return true;
    }

    @Override
    public List<Student> findAll() {
        List<Student> sList = studentDao.findAll();
        return sList;
    }
}
