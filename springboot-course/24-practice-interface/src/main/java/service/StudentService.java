package service;

import entity.Student;

public interface StudentService {
    int addStudent(Student student);
    Student queryStudent(Integer id);
}
