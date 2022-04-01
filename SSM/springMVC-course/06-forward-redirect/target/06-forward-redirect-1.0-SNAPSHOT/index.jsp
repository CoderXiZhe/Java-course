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
            <p>当出来方法返回ModelAndview实现forward</p>
            <form action="doForward.do" method="post">
                姓名:<input type="text" name="name">
                年龄:<input type="text" name="age">
                <input type="submit" value="提交参数">
            </form><br>

            <p>当出来方法返回ModelAndview实现redirect</p>
            <form action="doRedirect.do" method="post">
                姓名:<input type="text" name="name">
                年龄:<input type="text" name="age">
                <input type="submit" value="提交参数">
            </form><br>



        </center>

</body>
</html>
