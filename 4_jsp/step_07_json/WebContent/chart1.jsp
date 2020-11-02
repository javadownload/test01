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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/series-label.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

  <style type="text/css">
#container {
  min-width: 310px;
  max-width: 800px;
  height: 400px;
  margin: 0 auto;
  
}
</style>

<script type="text/javascript">
let error;
$(function () {
	$('#btn1').click(function () {
		$.ajax({
			url : 'chart_data.jsp',
			dataType : 'json',
			success : function(data){
		         chart(data);
			},
			error:function(e){
				error = e;
				alert("ajax 통신 에러 ");
			}
		});
		
	});
});

function chart(data) {
	Highcharts.chart('container', {

		  title: {
		    text: '우리나라 지역별 온도, 2019'
		  },

		  subtitle: {
		    text: 'Source: thesolarfoundation.com'
		  },

		  yAxis: {
		    title: {
		      text: 'Number of Employees'
		    }
		  },

		  xAxis: {
		    accessibility: {
		      rangeDescription: 'Range: 2010 to 2017'
		    }
		  },

		  legend: {
		    layout: 'vertical',
		    align: 'right',
		    verticalAlign: 'middle'
		  },

		  plotOptions: {
		    series: {
		      label: {
		        connectorAllowed: false
		      },
		      pointStart: 2010
		    }
		  },

		  series: data,

		  responsive: {
		    rules: [{
		      condition: {
		        maxWidth: 500
		      },
		      chartOptions: {
		        legend: {
		          layout: 'horizontal',
		          align: 'center',
		          verticalAlign: 'bottom'
		        }
		      }
		    }]
		  }

		});
}

</script>
  
    
</head>
<body>
<%@ include file="common/header.jsp" %>

<div id="output">
<section>

<h3> HiChart ajax</h3>
<button id="btn1"> 차트보기</button>

<div id="container"></div>


</section>
</div>
<%@ include file="common/footer.jsp" %>


</body>
</html>