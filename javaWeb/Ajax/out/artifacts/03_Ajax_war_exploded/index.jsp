<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/10
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript">
      function search() {
          //1.创建
        var xmlHttp = new XMLHttpRequest();

          //2.绑定事件
        xmlHttp.onreadystatechange = function() {
          if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
               //window.alert(xmlHttp.responseText);
              //将string转换为json对象
               var jsonObj =JSON.parse(xmlHttp.responseText);
               //更新dom对象
               document.getElementById("pname").value = jsonObj.name;
               document.getElementById("jiancheng").value = jsonObj.jiancheng;
               document.getElementById("shenghui").value = jsonObj.shenghui;
          }
        }
        //3.初始化
        var pid = document.getElementById("pid").value;
        var param = "pid=" + pid;
        xmlHttp.open("get","one?" + param,true);

        //4.发送
        xmlHttp.send();
      }
    </script>
  </head>
  <body >
    <center>
            <table boredr="2">
                <tr>
                  <td>省份编号:</td>
                  <td><input type="text" id="pid" name="pid">
                    <input type="button" value="查询" onclick="search()">
                  </td>
                </tr>
              <tr>
                <td>省份名称:</td>
                <td><input type="text" id="pname" name="pname">
                </td>
              </tr>
              <tr>
                <td>省份简称:</td>
                <td><input type="text" id="jiancheng" name="jiancheng">
                </td>
              </tr>
              <tr>
                <td>省会:</td>
                <td><input type="text" id="shenghui" name="shenghui">
                </td>
              </tr>

            </table>

    </center>
  </body>
</html>
