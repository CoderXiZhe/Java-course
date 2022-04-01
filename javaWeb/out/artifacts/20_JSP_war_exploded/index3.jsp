<%@ page import="com.bjpowernode.bean.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>



<%
    Student student = new Student("mike",10);
%>
学生姓名:<%=student.getName()%></br>
学生年龄:<%=student.getAge()%>