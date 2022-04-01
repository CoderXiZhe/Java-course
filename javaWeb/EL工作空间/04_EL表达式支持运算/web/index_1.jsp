<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/3
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String num1 = (String)request.getAttribute("key1");
    Integer num2 = (Integer)request.getAttribute("key2");
    int num3 = Integer.valueOf(num1) + num2;

%>
传统计算结果:<%=num3%><br>
简化版:${key1+key2}