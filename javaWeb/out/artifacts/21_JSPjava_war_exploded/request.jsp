
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--
        JSP文件内置对象:request
                   类型: HttpServletRequest
                   作用:在JSP文件运行时读取请求包信息
        与Servlet在请求转发过程中实现数据共享
        浏览器:http://localhost:8080/myWeb/request.jsp?userName=allen&password=123
-->
<%
    //在JSP文件执行时，借助于内置request对象读取请求包参数信息
    String userName = request.getParameter("userName");
    String password =request.getParameter("password");
%>

来访用户姓名:<%=userName%><br/>
来访用户密码:<%=password%>
