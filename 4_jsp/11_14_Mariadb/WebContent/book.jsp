<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
  
</head>
<body>
<%@ include file="common/header.jsp" %>
<div id="output">

<section>
<h2> Book 등록</h2>

<form action="addBook.do" method="post">
    제목: <input type="text"  name="title"> <br/>
    출판사: <input type="text"  name="publisher"> <br/>
    가격: <input type="number"  name="price"> <br/>
    
  <input type="submit" value="등록"> 
  <input type="reset">
</form>
</section>
</div>
<%@ include file="common/footer.jsp" %>

</body>
</html>