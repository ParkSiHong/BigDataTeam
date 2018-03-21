<%@page import="bigdata.project.dto.ParkingLot"%>
<%@page import="bigdata.project.dto.Bookmark"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Insert title here</title>
</head>
<script type="text/javascript"
   src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0bd44f8e722c29ebe8a167485a894e67"></script>

<%@ include file="../common/header.jsp" %>
<style>
@import
url("./css/sidebar.css")
</style>

<body>
<!-- Services Section -->
<section id="services">
<div id="content">
<%
   List<ParkingLot> recommendList = (List<ParkingLot>)request.getAttribute("recommendList");
%>
<form action="./bookmarkenroll.project">
<table id="table">
   <thead>
        <tr>
         <th>주차장번호</th>
         <th>주차장이름</th>
         <th>주차장유형</th>
         <th>기본요금</th>
        </tr>
   </thead>
   <tbody>
   <% for(int i =0;i <recommendList.size();i++){ %><!-- for(User user:list) -->
   <tr>
      <td><a href="parkingdetail.project?parkingSearch=<%=recommendList.get(i).getPrimaryNum()%>"><%=recommendList.get(i).getPrimaryNum()%></a></td>
      <td>   
      <%= recommendList.get(i).getParkingName()%>
      </td>
      <td>   
      <%= recommendList.get(i).getParkingType()%>
      </td>
      <td>   
      <%= recommendList.get(i).getParkingBasicFee()%>
      </td>
      <td><input type="checkbox" name="check" id="check"
                     value="<%=recommendList.get(i).getPrimaryNum()%>"></td>
      </tr>
      <%
       }
      %>
</tbody>
</table>
<input type="submit" id="search" value="즐겨찾기 추가">
</form>
</div>

<div id= "sidebar">

<%
   List<Bookmark> list = (List<Bookmark>)request.getAttribute("list");
%>

   <table id="table1"><!--  id="table" -->
      <thead>
        <tr>
            <th colspan ="2" style = "border : 1px solid ; background-color : #79BD8F; color : white; border-collapse: collapse;">맞춤형 주차장 추천 서비스</th>
        </tr>
           <tr>
            <th>번호</th>
            <th>주차장이름</th>
           </tr>
      </thead>
      <tbody>
      <% for(int i =0;i <list.size();i++){ %><!-- for(User user:list) -->
      <tr>
         <td><a href="parkingdetail.project?parkingSearch=<%=list.get(i).getPrimaryNum()%>"><%=list.get(i).getPrimaryNum()%></a></td>
         <td>   
         <%= list.get(i).getParkingName()%>
         </td>
      </tr>
      <% } %> 
      </tbody>     
   </table>
</div>
<div id="map" style="position : relative; margin : auto; width: 80vmin; height: 80vmin; max-width:850px; "></div>

<script>

var LatitudeCenter = 0.0;
var LongtitudeCenter = 0.0;
var count = 0;

<%for(int i = 0; i<recommendList.size(); i++){%>

   if((<%= recommendList.get(i).getLatitude()%> != 0) || (<%= recommendList.get(i).getLongtitude()%> != 0)) {
      LatitudeCenter += <%= recommendList.get(i).getLatitude()%>
      LongtitudeCenter += <%= recommendList.get(i).getLongtitude()%>
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
<%for(int i = 0; i<recommendList.size(); i++){%>
   positions.push({
      content: '<div style="padding:10px; padding-bottom:30px"><h3><%= recommendList.get(i).getParkingName() %></h3><br><p><%= recommendList.get(i).getOld_address() %><br></p><a href="http://map.daum.net/link/map/<%= recommendList.get(i).getParkingName() %>,<%= recommendList.get(i).getLatitude() %>, <%= recommendList.get(i).getLongtitude() %>" style="color:#468966 ; font-weight : 500 ; margin-right : 30px" target="_blank"> 큰 지도보기  </a> <a href="http://map.daum.net/link/to/<%= recommendList.get(i).getParkingName() %>,<%= recommendList.get(i).getLatitude() %>, <%= recommendList.get(i).getLongtitude() %>" style="color:#468966 ;font-weight : 500" target="_blank">  길찾기</a></div>',
       latlng: new daum.maps.LatLng(<%= recommendList.get(i).getLatitude()%>, <%= recommendList.get(i).getLongtitude()%>)

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
    
    
    var iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
    
 // 마커에 표시할 인포윈도우를 생성합니다 
    var infowindow = new daum.maps.InfoWindow({
        content: positions[i].content, // 인포윈도우에 표시할 내용
        removable : iwRemoveable
    });

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
    daum.maps.event.addListener(marker, 'click', makeClick(map, positions[i].latlng));
    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
}

//마커에 클릭이벤트를 등록합니다
function makeClick(map, marker) {
      // 마커 위에 인포윈도우를 표시합니다
      return function(){
      infowindow.open(map, marker);
      }
};

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
</body>
</html>