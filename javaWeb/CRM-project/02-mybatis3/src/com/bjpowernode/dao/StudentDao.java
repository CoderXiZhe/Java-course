package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;
import com.bjpowernode.vo.StudentAndClassroomVo;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    public Student getById(String id);

    public void save(Student s);

    List<Student> getAll();

    List<Student> select1(Student student);

    List<Student> select2(String[] str);

    Student select3(String s);

    List<Map<String, Object>> select4();

    List<StudentAndClassroomVo> select5();

    List<StudentAndClassroomVo> select6(Student student);
}
