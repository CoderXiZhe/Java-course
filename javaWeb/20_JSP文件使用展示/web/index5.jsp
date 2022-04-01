<%@ page import="com.bjpowernode.bean.Student" %>
<%@ page import="java.awt.dnd.DragGestureEvent" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/9/30
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

    Student student1 = new Student("zhangsan",20);
    Student student2 = new Student("lisi",30);
    Student student3 = new Student("wangwu",40);
    List<Student> list = new ArrayList<>();
    list.add(student1);
    list.add(student2);
    list.add(student3);

%>
<table border="2" align="center"  >
    <tr>
        <td>学生姓名</td>
        <td>学生编号</td>
    </tr>
    <%
        for(Student s: list) {

    %>
    <tr>
        <td><%=s.getName()%></td>
        <td><%=s.getAge()%></td>
    </tr>
    <%
        }
    %>

</table>