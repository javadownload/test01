<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book App</title>

<link rel="stylesheet" href="./css/button.css">
<link rel="stylesheet" href="./css/table.css">
<link rel="stylesheet" href="./css/main.css">
    
    
    
</head>
<body>
<%@ include file="common/header.jsp" %>

<div id="output">
<section>

<table class="tableb">
<tr>
<th>북번호</th><th>Title</th><th>Author</th><th>Price</th>
</tr>

<c:forEach var="book" begin="${(page-1)*5}" 
           end="${(page-1)*5+4 }" items="${list}">

<tr>
<td>${book.bookno}</td><td>${book.title}</td>
<td>${book.publisher}</td><td>${book.price}</td>
</tr>
</c:forEach>

</table>

<c:forEach var="i" begin="${start}" end="${end}">
    <a href="list.do?page=${i}">|${i}|</a>
</c:forEach>



</section>
</div>
<%@ include file="common/footer.jsp" %>


</body>
</html>