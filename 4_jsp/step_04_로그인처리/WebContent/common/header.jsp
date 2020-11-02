<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	    <header>
	       <hgroup>
	           <h1>Page Title</h1>
	           <h2>Page Title</h2>
	       </hgroup>
	    </header>
	    
	
	<nav>
	    <ul>
	       <li> <a href="index.jsp"> Home </a> </li>
	      
	       <li class="dropbox"> <a href="#"> 메 뉴 </a> 
	           <span class="dropmenu">
		           <span> <a href="img1.html" class="btn"> 이미지보기 </a> </span>
			       <span> <a href="table1.html" class="btn"> 데이터보기 </a> </span>
			       <span> <a href="chart1.jsp" class="btn"> chart 1 </a> </span>
			       <span> <a href="#" class="btn"> link 2  </a> </span>
			       <span> <a href="#" class="btn"> link 3 </a> </span>
	           </span>
	       </li>
	       
	       <li> <a href="#"> About </a> </li>
	       <li> <a href="#"> Contact </a> </li>
	       
	       <c:if test="${empty login}">
			   <li> <a href="login.jsp"> LogIn </a></li><br>
		   </c:if>
			
			<c:if test="${!empty login }">
			   <li> <a href="logout.do"> LogOut </a></li><br>
			</c:if>


	    </ul>
	</nav>
	