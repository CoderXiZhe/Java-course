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
            <p>提交参数给controller</p>
            <form action="receive.do" method="post">
                姓名:<input type="text" name="name">
                年龄:<input type="text" name="age">
                <input type="submit" value="提交参数">
            </form><br>
            <p>请求参数名和处理器参数名不一致</p>
            <form action="receive2.do" method="post">
                姓名:<input type="text" name="rname">
                年龄:<input type="text" name="rage">
                <input type="submit" value="提交参数">
            </form><br>
            <p>使用java对象接收请求参数</p>
            <form action="receive3.do" method="post">
                姓名:<input type="text" name="name">
                年龄:<input type="text" name="age">
                <input type="submit" value="提交参数">
            </form><br>


        </center>

</body>
</html>
