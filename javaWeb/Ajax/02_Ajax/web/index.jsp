<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/10
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <script type="text/javascript">

          function doAjax() {
            //1.创建异步对象
            var xmlHttp = new XMLHttpRequest();

            //2.绑定事件
            xmlHttp.onreadystatechange = function() {
              //处理服务端返回的数据
                //alert("readyState属性值:" +　xmlHttp.readyState + "|status:" + xmlHttp.status);
                if(xmlHttp.readyState == 4 && xmlHttp.status ==200) {
                   var data = xmlHttp.responseText;
                   document.getElementById("data").innerText = data;
                }
            }

            //3.初始化请求数据
              var name = document.getElementById("name").value;
              var w = document.getElementById("w").value;
              var h = document.getElementById("h").value;

              var param = "name=" + name + "&w=" + w + "&h=" + h;
              //alert("param=" + param);
              xmlHttp.open("get","one?" + param,true);

            //4.发起请求
            xmlHttp.send();
          }

    </script>
  </head>
  <body>
  <center>
      姓名:<input type="text" name="name" id="name"><br>
      体重(公斤):<input type="text" name="w" id="w"><br>
      身高(米):<input type="text" name="h" id="h"><br>
      <input type="submit" value="提交" onclick="doAjax()"><br><br>

      <div id="data">等待加载数据........</div>
  </center>
  </body>
</html>
