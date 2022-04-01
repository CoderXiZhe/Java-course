<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/11
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
            $(function () {
                $("#b1").click(function () {
                    var pid = $("#pid").val();
                    $.get("one",{pid:pid},function(resp){
                        $("#pname").val(resp.name)
                        $("#jiancheng").val(resp.jiancheng)
                        $("#shenghui").val(resp.shenghui)
                    },"json")
                })
            })
















        // function search() {
        //    //1.创建
        //    var xmlHttp =  new XMLHttpRequest;
        //    //2.绑定事件
        //    xmlHttp.onreadystatechange = function() {
        //         if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        //             //alert(xmlHttp.responseText);
        //             var jsonStr = xmlHttp.responseText;
        //             var jsonObj = JSON.parse(jsonStr);
        //             document.getElementById("pname").value = jsonObj.name;
        //             document.getElementById("jiancheng").value = jsonObj.jiancheng;
        //             document.getElementById("shenghui").value = jsonObj.shenghui;
        //         }
        //    }
        //    //3.初始化
        //     var pid = document.getElementById("pid").value;
        //     var param = "one?pid=" + pid;
        //     xmlHttp.open("get",param,true);
        //
        //    //4.发送
        //     xmlHttp.send();
        // }

    </script>
</head>
<body>
        <center>
            省份编号:   <input type="text" name="pid" id="pid"> <br>
            省份名称:   <input type="text" name="pname" id="pname" ><br>
            省份简称:   <input type="text" name="jiancheng" id="jiancheng" ><br>
            省份省会:   <input type="text" name="shenghui" id="shenghui" ><br>

            <input type="button" value="搜索" id="b1"><br><br>
        </center>
</body>
</html>
