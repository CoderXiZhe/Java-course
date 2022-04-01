<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/1
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
        <center>
            <%
                String value = (String)request.getAttribute("info");
            %>
            <font style="color: #ff0000;font-size: 40px">
                <%=value%>

        </center>
</body>
</html>
