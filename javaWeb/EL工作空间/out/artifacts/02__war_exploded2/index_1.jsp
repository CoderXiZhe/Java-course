<%@ page import="com.bjpowernode.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/3
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    Student student = (Student)request.getAttribute("key");
%>
学生id：<%=student.getSid()%><br>
学生姓名:<%=student.getSname()%>
<hr>
学生id:${requestScope.key.sid} <br>
学生姓名:${requestScope.key.sname}
