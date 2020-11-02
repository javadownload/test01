<%@page import="vo.BookVO"%>
<%@page import="java.util.List"%>
<%@page import="service.BookServiceImpl"%>
<%@page import="service.BookService"%>
<%@page import="dao.BookDAO_Mariadb"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/table.css">

<title>Insert title here</title>
</head>
<body>
<h1>Book List</h1>

<table class="tableb">
<tr><th>bookno</th> <th>title</th><th>publisher</th><th>price</th></tr>

<%
  BookDAO_Mariadb db = new BookDAO_Mariadb();
  BookService service = new BookServiceImpl(db);
  List<BookVO> list = service.bookList();
  
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