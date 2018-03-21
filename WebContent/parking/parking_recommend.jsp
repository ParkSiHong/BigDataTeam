<%@page import="bigdata.project.dto.ParkingLot"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript"
   src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0bd44f8e722c29ebe8a167485a894e67&libraries=services"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<body>
<div id="loading"><img id="loading-image" src="../img/loading.gif" alt="Loading..." /></div>
</body>
<style>
#loading {
 width: 100%;  
 height: 100%;  
 top: 0px;
 left: 0px;
 position: fixed;  
 display: block;  
 opacity: 0.7;  
 background-color: #fff;  
 z-index: 99;  
 text-align: center; } 
  
#loading-image {  
 position: absolute;  
 top: 50%;  
 left: 50%; 
 z-index: 100; }
</style>
<script type="text/javascript">
$(window).load(function() {    
     $('#loading').hide();  
    });
</script>
<script>
var geocoder = new daum.maps.services.Geocoder();
var Latitude=0.0;
var Longtitude=0.0;
console.log('<%= session.getAttribute("id")%>');

// 주소로 좌표를 검색합니다
   geocoder.addressSearch('<%= session.getAttribute("homeaddress")%>', function(result, status) {

       // 정상적으로 검색이 완료됐으면 
        if (status === daum.maps.services.Status.OK) {
         
           var coords = new daum.maps.LatLng(result[0].y, result[0].x);
           Latitude = Number(result[0].y);
           Longtitude = Number(result[0].x);
        }
        console.log(Latitude);
        console.log(Longtitude);
        location.href = "<%= request.getContextPath()%>/parkingrecommend.project?lat="+Latitude+"&lng="+Longtitude;
   });     

   
</script>
</html>