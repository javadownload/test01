<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="./css/my.css">
<link rel="stylesheet" href="./css/main.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js">
</script>
<script src="https://maps.google.com/maps/api/js?sensor=false"></script>
<style type="text/css">
  #map {
     width: 100%;
     height: 400px;
     background-color: gray;
  }

</style>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="common/header.jsp" %>
<div id="output">
<section>
<h2> My Location </h2>
<div id="map"></div>   


</section>
</div>
<%@include file="common/footer.jsp" %>
<script type="text/javascript">
navigator.geolocation.getCurrentPosition(initmap);
function initmap(location) {
	var cen = { 
			lat :location.coords.latitude,
			lng :location.coords.longitude
	};
	console.log(location.coords.latitude);
	console.log(location.coords.longitude);
	var map = new google.maps.Map(document.getElementById('map'),{
		zoom:14,
		center:cen
	});
	var marker = new google.maps.Marker({
		position:cen,
		map:map
	});
}
</script>
</body>
</html>