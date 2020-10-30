<%@page import="vo.BookVO"%>
<%@page import="java.util.List"%>
<%@page import="dao.BookDAO_JDBC"%>
<%@page import="dao.BookDAO"%>
<%@page import="service.BookServiceImpl"%>
<%@page import="service.BookService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
<link rel="stylesheet" href="./css/table.css">

<script type="text/javascript">
   function f1() {
	   return confirm("삭제 할거니?");
   }
</script>

</head>
<body>
<h1>Book List</h1>

<br>
<form action="${pageContext.request.contextPath}/search.do" method="post">
    <table>
        <tr align="right">
           <td>
              <select name="condition">
                  <option value="title">도서제목</option>
                  <option value="publisher">출판사</option>
              </select>
              <input type="text" name="keyword">
              <input type="submit" value="검색">
           </td>
        </tr>
    </table>
</form>
<hr>

<form action="deleteBook.do" method="get">
<table class="tableb">
	<tr><th>bookno</th> <th>title</th><th>publisher</th><th>price</th> 
	              <th><input type="submit" value="삭제" onclick="return f1()"> </th></tr>
	
	<c:forEach  var="data"  items="${bookList}">
	   <tr>
	       <td>${data.bookno}  </td>
	       <td><a href="viewBook.do?bookno=${data.bookno}"> ${data.title} </a>  </td>
	       <td>${data.publisher}  </td>
	       <td>${data.price}  </td>
	       <td> <input type="checkbox" name="bookno" value="${data.bookno}"> </td>
	       
	   </tr>
	</c:forEach>
	
	</table>
</form>

</body>
</html>





