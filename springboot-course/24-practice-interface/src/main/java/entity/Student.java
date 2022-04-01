package entity;



import java.io.Serializable;

/**
 * @author admin
 * @version 1.0
 * @description: TODO
 * @date 2022/3/30 12:42
 */
public class Student implements Serializable {
    private Integer id;
    private Integer age;
    private String phone;
    private String name;

    public static Student defaultStudent(){
        Student student = new Student();
        student.setId(0);
        student.setAge(0);
        student.setName("-");
        student.setPhone("-");
        return student;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
