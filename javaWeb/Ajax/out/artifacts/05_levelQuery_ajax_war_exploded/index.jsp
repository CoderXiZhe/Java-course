<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/14
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>级联查询</title>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
      function loadAjax() {
        $.ajax({
          url: "one",
          //data: "",
          dataType: "json",
          success: function (resp) {
            $("#s1").empty();
            //resp是json对象
            $.each(resp, function (i, e) {
              //alert(e.name)
              $("#s1").append("<option id='" + e.id + "'>" + e.name + "</option>")
            })
          }
        })
      }
      $(function() {
        loadAjax();
          $("#s1").click(function() {
            if($("#s1>option").length == 1){
              loadAjax();
            }

          })
          //这里给select绑定change事件  每次s1选中的值变化 s2都会进行刷新一下
          $("#s1").change(function(){
            var dom = $("#s1>option:selected")[0]
            var pid = dom.id
              $.ajax({
                url: "two",
                data: {
                  "pid": pid
                },
                dataType: "json",
                success: function (resp) {
                  $("#s2").empty();//在每次循环添加option标签之前 先把原来的给删除掉
                  $.each(resp, function (i, e) {
                    //alert(e.name)
                    $("#s2").append("<option>" + e.name + "</option>")
                  })
                }
              })
          })

      })

    </script>
  </head>
  <body>
  <center>
    <input type="button" id="b1" value="加载数据"><br>
    选择省份:<select id="s1">
    <option id="o1">请选择</option>
  </select >
    <br><br>

    选择城市:<select id="s2">
    <option id="o2">请选择</option>
  </select>

  </center>

  </body>
</html>
