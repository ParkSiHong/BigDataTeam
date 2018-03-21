<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@page import="bigdata.project.dto.ParkingLot"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Agency - Start Bootstrap Theme</title>
<script type="text/javascript"
   src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0bd44f8e722c29ebe8a167485a894e67"></script>
<%@ include file="../common/header.jsp"%>

<!-- Services Section -->
<section id="services">
<div id="demo">
   <%
      List<ParkingLot> list = (List<ParkingLot>) request.getAttribute("list");
   %>
   
</div>





<div id="map" style="width:100%;height:700px;"></div>

<script>

var LatitudeCenter = 0.0;
var LongtitudeCenter = 0.0;
var count = 0;

<%for(int i = 0; i<list.size(); i++){%>

   if((<%= list.get(i).getLatitude()%> != 0) || (<%= list.get(i).getLongtitude()%> != 0)) {
      LatitudeCenter += <%= list.get(i).getLatitude()%>
      LongtitudeCenter += <%= list.get(i).getLongtitude()%>
      count++;
   }
<%}%>

LatitudeCenter = LatitudeCenter / count;
LongtitudeCenter = LongtitudeCenter / count;

var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
mapOption = { 
   center: new daum.maps.LatLng(LatitudeCenter, LongtitudeCenter), // 지도의 중심좌표
    level: 7 // 지도의 확대 레벨
};

var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
 
// 마커를 표시할 위치와 title 객체 배열입니다 

var positions = [];
<%for(int i = 0; i<list.size(); i++){%>
   positions.push({
      content: '<div><%= list.get(i).getParkingName()%></div>',
      latlng: new daum.maps.LatLng(<%= list.get(i).getLatitude()%>, <%= list.get(i).getLongtitude()%>)

      });
<%}%>   


// 마커 이미지의 이미지 주소입니다
var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"; 
    
for (var i = 0; i < positions.length; i ++) {
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new daum.maps.Size(24, 35); 
    
    // 마커 이미지를 생성합니다    
    var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize); 
    
    // 마커를 생성합니다
    var marker = new daum.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        //title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        //image : markerImage // 마커 이미지 
    });
    
 // 마커에 표시할 인포윈도우를 생성합니다 
    var infowindow = new daum.maps.InfoWindow({
        content: positions[i].content // 인포윈도우에 표시할 내용
    });

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
}

// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
function makeOverListener(map, marker, infowindow) {
    return function() {
        infowindow.open(map, marker);
    };
}
   
// 인포윈도우를 닫는 클로저를 만드는 함수입니다    
function makeOutListener(infowindow) {
    return function() {
        infowindow.close();
    };
}


</script>
</section>
<%@ include file="../common/footer.jsp"%>
</body>

</html>