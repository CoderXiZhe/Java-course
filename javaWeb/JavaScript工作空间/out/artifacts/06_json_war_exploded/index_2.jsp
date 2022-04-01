<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/9
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        function f1() {
            var jsonArray = ${requestScope.key};
            for(var i =0;i<jsonArray.length;i++) {
                var jsonObj = jsonArray[i];
                var trDom = document.createElement("tr");//<tr></tr>
                var tdDom_1 = document.createElement("td");//<td></td>
                var tdDom_2 = document.createElement("td");
                var tdDom_3 = document.createElement("td");
                tdDom_1.innerHTML =jsonObj.deptNo;
                tdDom_2.innerHTML = jsonObj.dname;
                tdDom_3.innerHTML = jsonObj.loc;
                trDom.appendChild(tdDom_1);
                trDom.appendChild(tdDom_2);
                trDom.appendChild(tdDom_3);
                document.getElementById("one").appendChild(trDom);
            }
        }

    </script>
</head>
<body onload="f1()">
<center>
    <table border="2" id="one">
        <tr>
            <td>部门编号</td>
            <td>部门名称</td>
            <td>部门位置</td>
        </tr>


    </table>

</center>
</body>
</html>
