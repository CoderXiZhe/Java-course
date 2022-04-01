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
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">

        $(function(){
            $("#Btn").click(function () {
                $.ajax({
                    url:"returnStudentJsonArray.do",
                    data:{
                        name:"张三",
                        age:33
                    },
                    type:"get",
                    //dataType:"text",
                    dataType:"json",
                    success:function (data) {
                        $.each(data,function (i,e) {
                            alert(e.name + "-" + e.age)
                        })

                    }
                })
            })

        })
    </script>
</head>
<body>

        <center>
            <p>String返回值</p>
            <form action="returnString-view.do" method="post">
                姓名:<input type="text" name="name">
                年龄:<input type="text" name="age">
                <input type="submit" value="提交参数">
            </form><br>

            <button id="Btn" >发起ajax</button>


        </center>

</body>
</html>
