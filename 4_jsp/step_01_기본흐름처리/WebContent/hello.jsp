<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hello.jsp</title>
</head>
<body>
<h2>hello.jsp page 입니다.</h2>

<%!   //선언부    %>

<%
    //자바코드
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=utf-8");
    
    String name = request.getParameter("name");
%>
     <h3> 이름은 : <%= name %> </h3>
     <h3> 성 은 : <%= name.charAt(0) %> </h3>
    
<%    
    String addr = request.getParameter("addr");
%>
     <h3> 주소는 :  <%= addr %> </h3>



</body>
</html>