<%@page contentType="text/html; charset=utf-8" isELIgnored="false"%>
 
 
<html>
<head>
<title>Book 관리</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
			f.action="deleteBook.do";
			f.submit();
		}
	}
</script>
</head>
<body >
<table width=780 border=0 cellpadding=0 cellspacing=0>
	<tr>
	  <td width="20"></td>
	  <td>
  <!--contents-->
	  <table width=590 border=0 cellpadding=0 cellspacing=0>
		  <tr>
			<td bgcolor="f4f4f4" height="22">&nbsp;&nbsp;<b>Book 상세 보기</b></td>
		  </tr>
	  </table>  
	  <br>
	  
	  <!-- view Form  -->
	  <form name="f" method="post" action="">
	  <input type="hidden" name="bookno" value="${book.bookno}">
	  <table border="0" cellpadding="0" cellspacing="1" width="610" bgcolor="BBBBBB">
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">도서제목</td>
			<td width=490 bgcolor="ffffff" style="padding-left:10">
				 ${book.title}
			</td>
		  </tr>
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">출판사</td>
			<td width=490 bgcolor="ffffff" style="padding-left:10">
				 				 ${book.publisher}
			</td>
		  </tr>
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">가격</td>
			<td width=490 bgcolor="ffffff" style="padding-left:10">
				 				 ${book.price}
			</td>
		  </tr>		
		  <tr>
			<td width=120 align=center bgcolor="E6ECDE" height="22">이미지</td>
			<td width=490 bgcolor="ffffff" style="padding-left:10">
				 			<img alt="" src="./img/spring1.png" width="200" height="200">	 
			</td>
		  </tr>		
		  
	  </table>
	  </form>
	  <table width=610 border=0 cellpadding=0 cellspacing=0>
		  <tr>
			<td align=center>
			<input type="button" value="수정" onClick="bookModify()"> &nbsp;
			<input type="button" value="삭제" onClick="bookRemove()"> &nbsp;
			<input type="button" value="목록" onClick="bookList()"> 
			</td>
		  </tr>
	  </table>
	  </td>
	</tr>
</table>  

</body>
</html>