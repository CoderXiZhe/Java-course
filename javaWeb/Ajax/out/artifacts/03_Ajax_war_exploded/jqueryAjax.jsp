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
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">

      $(function() {
        $("#b1").on("click", function () {
          var pid = $("#pid").val();
          $.ajax({
            url: "one",
            data: {
              "pid": pid
            },
            dataType: "json",
            success: function (resp) {
              //resp是json对象
              $("#pname").val(resp.name)
              $("#jiancheng").val(resp.jiancheng)
              $("#shenghui").val(resp.shenghui)
            }

          })
        })

      })
    </script>
  </head>
  <body >
    <center>
            <table boredr="2">
                <tr>
                  <td>省份编号:</td>
                  <td><input type="text" id="pid" name="pid">
                    <input type="button" id="b1" value="查询" >
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
