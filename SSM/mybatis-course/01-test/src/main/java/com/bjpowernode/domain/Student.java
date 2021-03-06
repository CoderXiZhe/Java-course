package com.bjpowernode.domain;

public class Student {
        private Integer id;//不是int

        private String name;

        private String email;

        private Integer age;

        public Integer getId() {
                return id;
        }

        @Override
        public String toString() {
                return "Student{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        ", email='" + email + '\'' +
                        ", age=" + age +
                        '}';
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public Integer getAge() {
                return age;
        }

        public void setAge(Integer age) {
                this.age = age;
        }
}
