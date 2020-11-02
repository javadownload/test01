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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
<script src="./js/fn.js"></script>    

<script type="text/javascript">

function f1() {
	alert("ajax 통신 ")
	$.ajax({ 
		 url:'bookListajax.do',
		 dataType:'json',
		 success:function(data){
			 display(data);
		 },
		 error:function(error){
			 alert("error "+error.name);
			 alert("error "+error.name+","+error.statusText);
		 }
	 });  
}

function display(data){
	//console.log(typeof(data))
	//$(data).each((i,item)=>{log(item);});
	
	 let tag = "<table class='tablea'>";
	 tag += "<tr><th>bookno</th> <th>title</th><th>publisher</th></tr> ";
	 
	 $(data).each((i,item)=>{
		 tag += "<tr><td>"
	     tag += item.bookno+"</td><td>"+item.title+"</td><td>"+item.publisher
	     tag += "</tr></td>"
	 });
	 
	 tag += "</table>";
	 
	 $('section').html(tag);
}

</script>    
    
</head>
<body>
<%@ include file="common/header.jsp" %>

<div id="output">
<section>
<div>
<h3> link List</h3>


<ul>
<c:if test="${empty login}">
   <li> <a href="login.jsp"> LogIn </a></li><br>
</c:if>

<c:if test="${!empty login }">
   <li> <a href="logout.do"> LogOut </a></li><br>
   <li> <a href="bookList.do"> Book List </a></li><br>
   <li> <a href="book.jsp"> Book 등록t </a></li><br>
</c:if>

</ul>

</div>
</section>
</div>
<%@ include file="common/footer.jsp" %>


</body>
</html>