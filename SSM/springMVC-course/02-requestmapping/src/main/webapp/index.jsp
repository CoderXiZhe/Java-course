<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/11/29
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

        <center>
            <p>第一个springmvc项目</p>
            <a href="user/some.do">发起some.do的get</a>
            <form action="user/other.do" method="post">
                <input type="submit" value="post请求other.do">
            </form>
        </center>

</body>
</html>
