<%@ page import="java.util.List" %>
<%@ page import="com.bjpowernode.entity.Question" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/10/2
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <%
        List<Question> list = (List) request.getAttribute("key");
    %>
    <table border="2">
        <tr>
            <td align="center">题目编号</td>
            <td align="center">题目信息</td>
            <td align="center">A</td>
            <td align="center">B</td>
            <td align="center">C</td>
            <td align="center">D</td>
            <td align="center">正确答案</td>
            <td align="center" colspan="2">操作</td>
        </tr>
        <%

//            for(Question question : list) {
//                Integer questionId = question.getQuestionId();
//                String title = question.getTitle();
//                String optionA = question.getOptionA();
//                String optionB = question.getOptionB();
//                String optionC = question.getOptionC();
//                String optionD = question.getOptionD();
//                String answer = question.getAnswer();
            for(int i = 0;i<list.size();i++) {
                Question question = list.get(i);
        %>
        <tr>
            <td align="center"><%=question.getQuestionId()%></td>
            <td align="center"><%=question.getTitle()%></td>
            <td align="center"><%=question.getOptionA()%></td>
            <td align="center"><%=question.getOptionB()%></td>
            <td align="center"><%=question.getOptionC()%></td>
            <td align="center"><%=question.getOptionD()%></td>
            <td align="center"><%=question.getAnswer()%></td>
            <td align="center"><a href="/myWeb/question/delete?questionId=<%=question.getQuestionId()%>">删除试题</a></td>
            <td align="center"><a href="/myWeb/question/findById?questionId=<%=question.getQuestionId()%>">详细信息</a></td>
        </tr>
        <%

            }
        %>
    </table>
</center>
</body>
</html>
