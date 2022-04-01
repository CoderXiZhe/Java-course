
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <center>
            退出系统 从session中删除数据
            <%
                    session.removeAttribute("name");
            %>
        </center>
</body>
</html>
