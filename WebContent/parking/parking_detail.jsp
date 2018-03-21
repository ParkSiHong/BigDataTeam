<%@page import="bigdata.project.dto.ParkingLot"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<style>

</style>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="text/html; charset=UTF-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<meta name="description" content="">
<meta name="author" content="">

<script type="text/javascript"
   src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0bd44f8e722c29ebe8a167485a894e67&libraries=services"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<%@ include file="../common/header.jsp"%>
<style>
@import
url("./css/table2.css")
</style>
<!-- Services Section -->
<body>
<section style="padding-bottom:40px"id="services" >
<div id="demo">
   <% ParkingLot parking = (ParkingLot)request.getAttribute("parkingSearch");%>

   <table id="table">
      <thead>
         <tr>
            <th>주차장번호</th>
            <th>주차장이름</th>
            <th>주차장구분</th>
            <th>구획수</th>
            <th>운영일</th>
            <th>전화번호</th>
            <th>위치</th>
            <th>주차기본요금</th>
            <th>주차기본시간</th>
            <th>추가단위요금</th>
            <th>추가단위시간</th>
         </tr>
      </thead>
      <tbody>
         <tr>
            <td><%= parking.getPrimaryNum() %></td>
            <td><%= parking.getParkingName() %></td>
            <td><%= parking.getParkingType() %></td>
            <td><%= parking.getSectionDount() %></td>
            <td><%= parking.getRunDay() %></td>
            <td><%= parking.getTel() %></td>
            <td><%= parking.getOld_address() %></td>
            <td><%= parking.getParkingBasicFee() %></td>
            <td><%= parking.getParkingBasicTime() %></td>
            <td><%= parking.getAddUnitFee() %></td>
            <td><%= parking.getAddUnitTime() %></td>
         </tr>
      </tbody>
   </table>
</div>
</section>


      <div style="position :relative; background-color:#fae001 ; width:80vmin; margin:auto">
         <a id="navi" href="#" onclick="navi();">
              <img src="./img/navi.png"  style="width : 10vmin ; margin-left:35vmin;z-index: 3"/>
            </a>
      </div>
       <div id="map" style="position : relative; margin : auto; width: 80vmin; height: 80vmin; max-width:850px; "></div>
      <div style="position :relative; background-color:#fae001 ; width:80vmin; margin:auto;margin-bottom : 30px">
         <a id="navi" href="#" onclick="navi();">
              <img src="./img/navi.png"  style="width : 10vmin ; margin-left:35vmin;z-index: 3"/>
            </a>
      </div>
       

   <script>      
   

   
   
   var Latitude=0.0;
   var Longtitude=0.0;
   
   Latitude = <%= parking.getLatitude()%>;
   Longtitude= <%= parking.getLongtitude()%>;
   
   if(<%=parking.getLatitude()%> == 0 || <%=parking.getLongtitude()%>== 0){
      //주소-좌표 변환 객체를 생성합니다
      var geocoder = new daum.maps.services.Geocoder();
   // 주소로 좌표를 검색합니다
      geocoder.addressSearch('<%= parking.getOld_address()%>', function(result, status) {

          // 정상적으로 검색이 완료됐으면 
           if (status === daum.maps.services.Status.OK) {
            
              var coords = new daum.maps.LatLng(result[0].y, result[0].x);
              Latitude = Number(result[0].y);
              Longtitude = Number(result[0].x);
              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new daum.maps.Marker({
                  map: map,
                  position: coords
              });

              console.log(Latitude);
              console.log(Longtitude);
              console.log(coords);
              console.log(coords.hb);
              
              var iwContent = '<div style="padding:10px; padding-bottom:30px"><h3><%= parking.getParkingName() %></h3><br><p><%= parking.getOld_address() %><br></p><a href="http://map.daum.net/link/map/<%= parking.getParkingName() %>, Latitude, Longtitude" style="color:#468966 ; font-weight : 500 ; margin-right : 30px" target="_blank"> 큰 지도보기  </a> <a href="http://map.daum.net/link/to/<%= parking.getParkingName() %>, Latitude, Longtitude" style="color:#468966 ;font-weight : 500" target="_blank">  길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
              iwPosition = new daum.maps.LatLng(Latitude,Longtitude); //인포윈도우 표시 위치입니다

              // 인포윈도우를 생성합니다
              var infowindow = new daum.maps.InfoWindow({
                 position : iwPosition,
                 content : iwContent
              });

              // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
              infowindow.open(map, marker);
              
              // 인포윈도우로 장소에 대한 설명을 표시합니다
              //var infowindow = new daum.maps.InfoWindow({
              //    content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
              //});
              //infowindow.open(map, marker);

              // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
              map.setCenter(coords);
              
          }
      });
   }
   var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
       mapOption = { 
           center: new daum.maps.LatLng(Latitude, Longtitude), // 지도의 중심좌표
           level: 3 // 지도의 확대 레벨
       };

   var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

   // 마커가 표시될 위치입니다 
   var markerPosition  = new daum.maps.LatLng(<%= parking.getLatitude()%>, <%= parking.getLongtitude()%>);

   // 마커를 생성합니다
   var marker = new daum.maps.Marker({
       position: markerPosition
   });

   // 마커가 지도 위에 표시되도록 설정합니다
   marker.setMap(map);

   // 아래 코드는 지도 위의 마커를 제거하는 코드입니다
   // marker.setMap(null); 
   
   var iwContent = '<div style="padding:10px; padding-bottom:30px"><h3><%= parking.getParkingName() %></h3><br><p><%= parking.getOld_address() %><br></p><a href="http://map.daum.net/link/map/<%= parking.getParkingName() %>,<%= parking.getLatitude() %>, <%= parking.getLongtitude() %>" style="color:#468966 ; font-weight : 500 ; margin-right : 30px" target="_blank"> 큰 지도보기  </a> <a href="http://map.daum.net/link/to/<%= parking.getParkingName() %>,<%= parking.getLatitude() %>, <%= parking.getLongtitude() %>" style="color:#468966 ;font-weight : 500" target="_blank">  길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
      iwPosition = new daum.maps.LatLng(<%=parking.getLatitude()%>,<%=parking.getLongtitude()%>); //인포윈도우 표시 위치입니다

      // 인포윈도우를 생성합니다
      var infowindow = new daum.maps.InfoWindow({
         position : iwPosition,
         content : iwContent
      });

      // 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
      infowindow.open(map, marker);
   </script>
  
   
   <script type='text/javascript'>
     //<![CDATA[
    // 사용할 앱의 JavaScript 키를 설정해 주세요.
    Kakao.init('0bd44f8e722c29ebe8a167485a894e67');
    // 카카오 로그인 버튼을 생성합니다.
    
     function navi(){
         var xp = "<%= parking.getLatitude()%>";
         xp = Number(xp);
         var yp = "<%= parking.getLongtitude() %>";
         yp = Number(yp);
         
         Kakao.Navi.start({
              name: "<%= parking.getParkingName() %>",
              x: yp,
              y: xp,
              coordType: 'wgs84'
          }); 
      }
    
     //]]>
</script>
<%@ include file="../common/footer.jsp"%>
</body>
</html>