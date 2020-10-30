<%@page import="vo.BookVO"%>
<%@page import="java.util.List"%>
<%@page import="dao.BookDAO_JDBC"%>
<%@page import="dao.BookDAO"%>
<%@page import="service.BookServiceImpl"%>
<%@page import="service.BookService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<%
  // BookDAO dao = new BookDAO_JDBC();
  // BookService service = new BookServiceImpl(dao);
   
  // List<BookVO> list = service.bookList();
   List<BookVO> list = (List<BookVO>)request.getAttribute("bookList");
   for(BookVO data :list){
%>
   <tr>
       <td><%= data.getBookno() %>  </td>
       <td><%= data.getTitle() %>   </td>
       <td><%= data.getPublisher() %>  </td>
       <td><%= data.getPrice() %>  </td>
   </tr>
<%} %>

</table>


</body>
</html>





