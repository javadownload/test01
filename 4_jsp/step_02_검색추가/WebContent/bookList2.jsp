<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
<link rel="stylesheet" href="./css/table.css">

</head>
<body>
<h1>Book List</h1>

<table class="tableb">
<tr><th>bookno</th> <th>title</th><th>publisher</th><th>price</th></tr>

<c:forEach  var="data"  items="${bookList}">
   <tr>
       <td>${data.bookno}  </td>
       <td><a href="viewBook.do?bookno=${data.bookno}"> ${data.title} </a>  </td>
       <td>${data.publisher}  </td>
       <td>${data.price}  </td>
   </tr>
</c:forEach>

</table>


</body>
</html>





