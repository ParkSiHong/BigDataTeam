<%@page import="java.util.ArrayList"%>
<%@page import="bigdata.project.dto.ChartCount"%>
<%@page import="bigdata.project.dto.ParkingLot"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="../js/jquery-3.2.1.js"></script>
<script type="text/javascript"
    src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript"
    src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">


$(document).ready(function() {
   $("#search").click(function() {
      var list = [];
      list.push(['Element', 'Count', { role: 'style' }]);
      $.ajax({
         type : 'POST',
          url : '../parkingchart.project',
          data : {
            "ChartSearch" : $("#ChartSearch").val()
         },
            dataType : 'json',
            success : function(data) {
               google.charts.load('current', {'packages':['bar']});
            google.charts.setOnLoadCallback(drawStuff);

            function drawStuff() {
              

              
               $.each(data, function(index, item){
                  console.log(index + item.cityname + item.count);
               //console.log(item.cityname);
               //console.log(item.count);
               color = ['fill-color: #b87333','fill-color: blue','color: white','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800',
                  'color: #468966','color: #FFF0A5','color: #FFB03B','color: #B64926','color: #8E2800'
                  ]
               list.push([item.cityname, item.count, color[index]]) 
               console.log(color[index])
               
               });
               
               console.log(list);
               
               var countdata = new google.visualization.arrayToDataTable(list);
               
               var options = {
                      legend: { position: 'none' },
                      chart: {
                        title: '구글 API를 이용한 공공표준주차장 데이터 시각화',
                        subtitle: 'SMART PARKING' },
                        bars: 'horizontal',
                      axes: {
                        x: {
                          0: { side: 'bottom', label: '주차장 수'} // Top x-axis.
                        }
                      },
                      bar: { groupWidth: "50%" }
                    };

                    var chart = new google.charts.Bar(document.getElementById('top_x_div'));
                    // Convert the Classic options to Material options.
                    chart.draw(countdata, google.charts.Bar.convertOptions(options));
                  };
               

          },
          error : function(xhr, type) {
              alert('server error occoured')
          }
      });
      
      
   });
   
});

    
   
</script>
<style>
#selectcontain{
position : relative;
top : 150px;
left : 25vw
}

#top_x_div{
   position : relative;
   top : 200px;

}

rect{
   max-width : 90vw;
}
</style>




</head>
<body>
<%@ include file="../common/header2.jsp" %>
   <script language="javascript">
      function SelectValue(idvalue) {
         var obj_id = document.getElementById('ChartSearch');
         obj_id.value = idvalue;
      }
   </script>
   <!-- <form action="../parkingchart.project" method="get"> -->
   <div id = "selectcontain">
      <input type="text" name= "ChartSearch" id="ChartSearch">
      <select name="selectbox" onChange="SelectValue(this.value)">
         <option selectd>선택</option>
         <option value="전국주차장">전국주차장</option>
         <option value="서울특별시">서울특별시</option>
         <option value="부산광역시">부산광역시</option>
         <option value="대구광역시">대구광역시</option>
         <option value="인천광역시">인천광역시</option>
         <option value="대전광역시">대전광역시</option>
         <option value="광주광역시">광주광역시</option>
         <option value="울산광역시">울산광역시</option>
         <option value="경기도">경기도</option>
         <option value="강원도">강원도</option>
         <option value="충청북도">충청북도</option>
         <option value="충청남도">충청남도</option>
         <option value="경상북도">경상북도</option>
         <option value="경상남도">경상남도</option>
         <option value="전라북도">전라북도</option>
         <option value="전라남도">전라남도</option>
         <option value="제주도">제주도</option>
      </select>
      <select name="selectbox2" onChange="SelectValue(this.value)">
         <option selectd>선택</option>
         <option value="대전광역시">전체</option>
         <option value="대전광역시 동구">동구</option>
         <option value="대전광역시 중구">중구</option>
         <option value="대전광역시 서구">서구</option>
         <option value="대전광역시 대덕구">대덕구</option>
         <option value="대전광역시 유성구">유성구</option>
      </select>
      <input type="button" value ="검색" id = "search" class = "btn-primary">
      <br />
      <!-- </form> -->
</div>
    <div id="top_x_div" style="width: 800px; height: 600px;"></div>
    <%@ include file="../common/footer.jsp" %>
</body>
</html>