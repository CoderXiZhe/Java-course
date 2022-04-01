package com.bjpowernode.test;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.util.SqlSessionUtil;
import com.bjpowernode.vo.StudentAndClassroomVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Test2 {

/*
    动态sql
 */
    public static void main(String[] args) {
        StudentDao studentDao = SqlSessionUtil.getSqlSession().getMapper(StudentDao.class);

//        //1.动态sql where+if
//        Student student = new Student();
//        //student.setName("y");
//        student.setAddress("a");
//        List<Student> sList = studentDao.select1(student);
//        for(Student s: sList) {
//            System.out.println(s);
//        }

          //2.动态sql foreach标签
//        //查询编号为1,2,3,4学员的信息
//        String str[]={"1","2","3","4"};
//        List<Student> sList = studentDao.select2(str);
//        for(Student s: sList) {
//            System.out.println(s);
//        }

          //3.sql片段 代替复杂sql
//        Student student  = new Student();
//        student = studentDao.select3("4");
//        System.out.println(student);

          //4.多表联查
//        List<Map<String,Object>> mapList = studentDao.select4();
//        for(Map<String,Object> map:mapList) {
//            Set<String> set =  map.keySet();
//            for(String key: set) {
//                System.out.println("key=" + key);
//                System.out.println("value=" + map.get(key));
//            }
//            System.out.println("-------------------------");
//        }

          //5.多表联查 查询出学生和班级的所有信息 加VO
        /*
                在实际开发中 如果需要为前端展现的数据 使用一个domain类型不足以表现出来
                这些数据 这是我们可以考虑：map和VO
                例如：
                    查询出学生和班级的所有信息
                    得到的结果 使用学生的domain和班级的都不能够封装这些结果
                    所以我们用map去保存这些信息
                    也可以用vo
                    vo指的是创建出来的一个类 类中的属性完全由我们自己去定义
                  vo
                    student
                    classroom
         */
//        List<StudentAndClassroomVo> voList = studentDao.select5();
//        for(StudentAndClassroomVo vo: voList) {
//            System.out.println(vo);
//        }

         //6.多表联查 查询出带有字母y的学生和班级信息
        Student student = new Student();
        student.setName("y");
        List<StudentAndClassroomVo> voList = studentDao.select6(student);
        for(StudentAndClassroomVo vo: voList) {
            System.out.println(vo);
        }
    }

    /*
            实际开发中 如果需要为前端提供多组值 那么我们应该使用map还是vo呢
            如果前端的需求重复率不高 那么我们临时选用map就行了
            如果前端对于该需求的重复率太高 我们可以创建一个vo类来使用 非常方便
     */
}
