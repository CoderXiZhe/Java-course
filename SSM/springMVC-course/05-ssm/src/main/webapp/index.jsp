<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>Title</title>
</head>
<body>
<center >
    <a href="addStudent.jsp">学生注册</a><br><br>
    <a href="findStudent.jsp">学生查询</a>

</center>
</body>
</html>
