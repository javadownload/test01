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

<h2>Book</h2>	
	<form action="insertbook.do" method="post">
		제목 : <input type="text" id="title" name="title"><br/>
		출판사 : <input type="text" name="publisher"></br>
		가격 : <input type="number" name="price"></br>
		<input type="submit" value="전송">
		<input type="reset" value="초기화">
	</form>


</section>
</div>
<%@ include file="common/footer.jsp" %>


</body>
</html>