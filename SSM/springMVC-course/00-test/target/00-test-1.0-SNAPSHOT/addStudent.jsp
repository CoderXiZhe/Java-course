<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body>
<center>
    <form action="student/add.do">
        <table border="1">
            <tr>
                <td>学生姓名:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>学生年龄:</td>
                <td><input type="text" name="age"></td>
            </tr>
            <tr>
                <td><input type="submit" value="注册"></td>
            </tr>
        </table>

    </form>
</center>
</body>
</html>
