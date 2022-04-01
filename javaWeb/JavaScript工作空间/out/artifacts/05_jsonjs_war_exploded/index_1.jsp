<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/7
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        var stuObj = ${requestScope.key};
        window.alert("id=" + stuObj.id + "  name=" + stuObj.name);

    </script>
</head>
<body>

</body>
</html>
