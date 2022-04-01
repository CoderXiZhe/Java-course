<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/3
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
        Integer sid = (Integer)application.getAttribute("sid");
        String name =(String)session.getAttribute("sname");
        String home = (String) request.getAttribute("home");
%>
学员Id:<%=sid%><br>
学员姓名:<%=name%><br>
学员地址：<%=home%>
<hr>
学员Id:${applicationScope.sid}<br>
学员姓名:${sessionScope.sname}<br>
学员地址：${requestScope.home}