package com.bjpowernode.service.impl;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.service.StudentService;
import com.bjpowernode.util.SqlSessionUtil;

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = SqlSessionUtil.getSession().getMapper(StudentDao.class);
    @Override
    public Student getById(String id) {
        Student s = null;
        s = studentDao.getById(id);
        return s;
    }

    @Override
    public void save(Student student) {
        studentDao.save(student);
    }
}
