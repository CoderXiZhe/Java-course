
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!--
        session_1.jsp与session_2.jsp为同一个用户/浏览器提供服务
          因此可以使用当前用户在服务端的私人储物柜进行数据共享
-->
<%
    Integer value = (Integer)session.getAttribute("key");
%>
sessoin2.jsp从当前用户session中获取到值为<%=value%>