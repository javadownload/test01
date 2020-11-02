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


<h1>login form 처리   get 방식</h1>

<form action="login.do" method="post">
   <table border="1">
      <tr>
         <td><label for="id">ID</label></td>
         <td>
             <input type="text" id="id" name="id" value="" 
                  required="required" placeholder="아이디입력" 
                  pattern="\w{6,12}" title="영문자숫자로 6~12 글자사이입력 "/> 
         </td>      
      </tr>
      <tr>
         <td><label for="pw">PW</label></td>
         <td><input type="password" id="pw" name="pw" value="" required="required"/> </td>
      </tr>
       <tr>
         <td colspan="2">
             <input type="submit" value="로그인">
             <input type="reset" value="취소">
             <a href="users.html">회원가입</a>
         </td>
      </tr>
   </table>
</form>

</section>
</div>
<%@ include file="common/footer.jsp" %>


</body>
</html>