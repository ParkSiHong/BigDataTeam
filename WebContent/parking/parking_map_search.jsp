<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script src="../js/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
    src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
       $("#search").click(function() {
          var LatitudeCenter = 0.0;
            var LongtitudeCenter = 0.0;
            var count = 0;
          var positions = [];
          $.ajax({
             type : 'POST',
              url : '../parkingmapsearch.project',
              data : {
                "parkingMapList" : $("#parkingMapList").val()
             },
                dataType : 'json',
                success : function(data) {
                   $.each(data, function(index, item){
                      
                      if((item.Latitude != 0) || (item.Longtitude != 0)) {
                           LatitudeCenter += item.Latitude
                           LongtitudeCenter += item.Longtitude
                           count++;
                        }
                      
                    positions.push({
                        content: '<div style="padding:10px; padding-bottom:30px"><h3> '+item.ParkingName+' </h3><br><p>'+item.Old_address+' <br></p><a href="http://map.daum.net/link/map/'+item.ParkingName+' ,'+item.Latitude+', '+item.Longtitude+'" style="color:#468966 ; font-weight : 500 ; margin-right : 30px" target="_blank"> 큰 지도보기  </a> <a href="http://map.daum.net/link/to/'+item.ParkingName+' ,'+item.Latitude+', '+item.Longtitude+'" style="color:#468966 ;font-weight : 500" target="_blank">  길찾기</a></div>',
                        latlng: new daum.maps.LatLng( item.Latitude, item.Longtitude),
                        });
                   });

                   LatitudeCenter = LatitudeCenter / count;
                   LongtitudeCenter = LongtitudeCenter / count;

                   var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
                   mapOption = { 
                      center: new daum.maps.LatLng(LatitudeCenter, LongtitudeCenter), // 지도의 중심좌표
                       level: 7 // 지도의 확대 레벨
                   };

                   var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
                    
                   // 마커를 표시할 위치와 title 객체 배열입니다 

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
                           clickable: true
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

                   /* //마커에 클릭이벤트를 등록합니다
                   daum.maps.event.addListener(marker, 'click', function() {
                         // 마커 위에 인포윈도우를 표시합니다
                         infowindow.open(map, marker);  
                   }); */


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
                },
             error : function(xhr, type) {
                  alert('server error occoured')
              }
          });
       });
    });
    </script>
</head>
<body>
<%@ include file="../common/header2.jsp" %>
    <!-- Services Section -->
    <section id="services">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">주차장 검색</h2>
                          
                             <input type="text" name="parkingMapList" placeholder="주소를 입력하세요 " id="parkingMapList">
                           <input type="button" value ="검색" id ="search" class = "btn-primary">
                </div>
            </div>      
        </div>
    </section>
<div id="map" style="width:100%;height:700px;"></div>


<%@ include file="../common/footer.jsp" %>

</body>

</html>