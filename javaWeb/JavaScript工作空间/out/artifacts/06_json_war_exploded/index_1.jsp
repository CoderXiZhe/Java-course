<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/9
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function f1() {
            var deptObj = ${requestScope.key};
            // window.alert(typeof deptObj);
            document.getElementById("deptNo").value = deptObj.deptNo;
            document.getElementById("dname").value = deptObj.dname;
            document.getElementById("loc").value = deptObj.loc;
        }
    </script>
</head>
<body onload="f1()">
        <center>
            <table border="2">
                <tr>
                    <td>部门编号</td>
                    <td><input type="text" id="deptNo"></td>
                </tr>

                <tr>
                    <td>部门名称</td>
                    <td><input type="text" id="dname"></td>
                </tr>

                <tr>
                    <td>部门位置</td>
                    <td><input type="text" id="loc"></td>
                </tr>


            </table>

        </center>
</body>
</html>
