package com.bjpowernode.dao;

import entity.Student;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface StudentDao {
     int addStudent(Student student);
     Student queryStudent(Integer id);
     int queryStudentByPhone(String phone);
}
