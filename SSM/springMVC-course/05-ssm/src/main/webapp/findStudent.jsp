<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">

        $(function () {

            find();
            $("#btn").click(function () {
                find();
            })

        });

        function find(){
            $.ajax({
                url:"student/findStudent.do",
                type:"get",
                dataType:"json",
                success:function (data) {
                    var html=""
                    $.each(data,function (i,e) {
                        html+= '<tr>';
                        html+= '<td>'+e.id+'</td>';
                        html+= '<td>'+e.name+'</td>';
                        html+= '<td>'+e.age+'</td>';
                        html+= '</tr>';
                    })
                    $("#tbody").html(html);
                }
            })
        }
    </script>
</head>
<body>

<center>
    <table border="1">

        <thead>
        <tr>
            <td>编号</td>
            <td>姓名</td>
            <td>年龄</td>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
    <input type="button" id="btn" value="查询">
</center>
</body>
</html>
