package com.bjpowernode.service.impl;

import com.bjpowernode.dao.StudentDao;
import entity.Student;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import service.StudentService;

import javax.annotation.Resource;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/30 12:50
 */
/*这里是StudentSerivce 不是实现类 不然会报错The annotated type must be an interface!*/
@DubboService(interfaceClass = StudentService.class,version = "1.0")
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public int addStudent(Student student) {
        //先查看手机号是否注册过
        String phone = student.getPhone();
        int count = studentDao.queryStudentByPhone(phone);
        if(count == 1) {
            //手机号已经存在
            return 2;
        }
        //没有注册手机号
        return studentDao.addStudent(student);
    }

    @Override
    public Student queryStudent(Integer id) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Student.class));
        if( id != null){
            Student student = (Student) redisTemplate.opsForValue().get("Student-"+id);
            if (student == null) {
                student = studentDao.queryStudent(id);
                if(student !=null) {
                    redisTemplate.opsForValue().set("Student-"+id,student);
                }else{
                    //数据库查不到 在redis添加一个此id的值 防治缓存穿透
                    redisTemplate.opsForValue().set("Student-"+id,Student.defaultStudent());
                }
            }
            return student;
        }else{
            return null;
        }
    }
}
