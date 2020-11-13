<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<title>My WebApplication ...</title>
<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">

<script >
	function bookList() {
		f.action="bookList.do"
		f.submit();
	}
	function bookModify() {
		f.action="bookmodify.do";
		f.submit();
	}
	function bookRemove() {
		if ( confirm("정말 삭제하시겠습니까?") ) {
			f.action="bookDelete.do";
			f.submit();
		}
	}
</script>


</head>
<body>

<%@ include file="common/header.jsp" %>
<section>
<h2> ...... </h2>



<!-- view Form  -->
	  <form name="f" method="post" action="">
	  <input type="hidden" name="bookno" value="${book.bookno}">
	  <table>
		  <tr>
			<td >도서제목</td>
			<td > ${book.title}	</td>
		  </tr>
		  <tr>
			<td >출판사</td>
			<td >${book.publisher}</td>
		  </tr>
		  <tr>
			<td >가격</td>
			<td >${book.price}</td>
		  </tr>		
		  <tr>
			<td >이미지</td>
			<td ><img alt="" src="./img/book1.png" width="200" height="200"></td>
		  </tr>		
		  
	  </table>
	  </form>
	  


     <table>
		  <tr>
			<td align=center>
			<input type="button" value="수정" onClick="bookModify()"> &nbsp;
			<input type="button" value="삭제" onClick="bookRemove()"> &nbsp;
			<input type="button" value="목록" onClick="bookList()"> 
			</td>
		  </tr>
	 </table>
	 

</section>
<%@ include file="common/footer.jsp" %>
</body>
</html>