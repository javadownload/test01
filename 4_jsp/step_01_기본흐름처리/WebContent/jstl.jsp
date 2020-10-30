<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% int a = Integer.parseInt(request.getParameter("a")); %>
<%if ( a == 99 ){ %>
<%= a %>
<%} %>
<hr>
<c:if test="${param.a == 99}">
  <c:out value="${param.a}"/>
</c:if> 
${param.a}<br>

<%for (int i=0; i<10; i++ ){ %>
<%= i %>
<%} %>
<br>


<% 
   ArrayList<String> list = new ArrayList<String>();  
   list.add("0A");
   list.add("1A");
   list.add("2A");
   list.add("3A");
   list.add("4A");
   list.add("5A");
   list.add("6A");
   list.add("7A");
   list.add("8A");
   list.add("9A");
   list.add("10A");
   list.add("11A");
   list.add("12A");
   list.add("13A");
   list.add("14A");
   list.add("15A");
   list.add("16A");
   list.add("17A");
   list.add("18A");

   request.setAttribute("list", list);
   request.setAttribute("page", 2);
%>
<c:forEach var="data" begin="${page*5 }" end="${page*5+5 }" items="${list}">
   ${data} , 
</c:forEach>
<br>
<c:set var="num" value="${20}" />
<c:set var="today" value="<%= new java.util.Date() %>" />
<c:set var="num2">
<B>안녕하세요</B>
</c:set>

<c:out value="${today}"/>
<br>오늘은 :  ${today} 
<br>num : ${num}
<br>html 태그로 구성된 num2 : ${num2}


<c:remove var="today" scope="page" />
<p>
삭제한 후의 today = ${today} <br>
<h3> map test</h3>
<c:set var="map" value="<%= new java.util.HashMap() %>" />
<c:set target="${map}" property="name" value="홍길동" />
<c:set target="${map}" property="email" value="master@naver.com" />
<c:set target="${map}" property="id" value="master" />
<c:forEach var="i" items="${map}">
    ${i.key} = ${i.value}<br>
</c:forEach>
<h3> 예외 처리 </h3>
<c:catch var="ex">
	id 파라미터의 값 = <%= request.getParameter("id") %><br>
	<%  if (request.getParameter("id").equals("master")) {  %>
			${param.id}은 test 입니다.
	<%  }   %>
</c:catch>
<p>
<c:if test="${ex != null}">
	예외가 발생하였습니다:<br>
	${ex}
</c:if>
<h3> url test ..</h3>

<c:url var="url1" value="footer.jsp" />
<A HREF=${url1}>footer.jsp</A> 비교 <A HREF="footer.jsp">footer.jsp</A>
<br>
<c:import url="footer.jsp" var="end" />
end : ${end}

</body>
</html>









