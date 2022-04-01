package com.bjpowernode.service.impl;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.model.Student;
import com.bjpowernode.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/25 11:45
 */

@Service
public class StudentServiceImpl  implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Override
    public Student queryStudentById(Integer id) {
        return studentDao.selectStudentById(id);
    }
}
