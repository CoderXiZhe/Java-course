package com.bjpowernode.dao.impl;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class StudentDaoImpl implements StudentDao {
    @Override
    public Student getById(String id) {
        SqlSession session = SqlSessionUtil.getSqlSession();
        Student student = session.selectOne("test.getById",id);
        return student;
    }

    @Override
    public void save(Student s) {
        SqlSession session = SqlSessionUtil.getSqlSession();
        session.insert("test.save",s);
        //这里不能提交事务 要在service提交事务
    }
}
