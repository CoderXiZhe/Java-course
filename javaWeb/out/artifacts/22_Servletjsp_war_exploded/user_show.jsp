<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    //从请求作用域对象中得到OneServlet添加进去的集合
    List<Student> list = (List)request.getAttribute("key");
%>
<!--将处理结果写入到响应体中-->

<table border="2" align="center">
    <tr>
        <td>学生姓名</td>
        <td>学生年龄</td>
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