<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/3
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--   http://localhost:8080/myWeb/index_2.jsp?pageNo=1&pageNo=2&pageNo=3     -->

第一个部门编号：${paramValues.pageNo[0]}<br>
第二个部门编号：${paramValues.pageNo[1]}<br>
第三个部门编号：${paramValues.pageNo[2]}