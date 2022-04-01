<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/3
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
          String age = (String) request.getAttribute("age");
          if(Integer.valueOf(age ) >= 18) {
 %>
            欢迎光临<br>
<%
          }else {
              %>
            拒绝入内<br>
<%
          }

%>

EL表达式输出：${age>=18?"欢迎光临":"拒绝入内"}