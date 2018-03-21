<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="bigdata.project.dto.Bookmark"%>
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
<%@ include file="../common/header.jsp" %>
<style>
@import
url("./css/table.css")
</style>
<script src = "./js/jquery-3.2.1.min.js"></script>
<script>
$(document).ready(function() {
   
   // 즐겨찾기 삭제 -------------------------------------------------------
   $("#table").on("click", "#delete", function(){
     console.log($(this).parent().parent().find("td:first-child").text());
    var tr= $(this);
     $.ajax({
        url: "./bookmarkdelete.project",
        data : {"primaryNum" : $(this).parent().parent().find("td:first-child").text()
        },
        success : function(data){
           console.log("success");
           $(tr).parent().parent().remove();
           },
           error: function(){
              console.log("error");
           }
        
     });
  });
});
</script>
<body>
	<!-- Services Section -->
	<section id="services">
	<div id="demo">
<%
   List<Bookmark> list = (List<Bookmark>)request.getAttribute("list");
%>
<%-- ${ requestScope.list } 또는 ${ list } 이렇게 간단하게 표현할 수 있음 --%>
<table id="table">
   <thead>
   	  <tr>
     	 <th>주차장번호</th>
     	 <th>주차장이름</th>
     	 <th>주차장유형</th>
     	 <th>기본요금</th>
     	 <th>즐겨찾기 삭제</th>
   	  </tr>
   </thead>
   <tbody>
   <% for(int i =0;i <list.size();i++){ %><!-- for(User user:list) -->
   <tr>
      <td><a href="parkingdetail.project?parkingSearch=<%=list.get(i).getPrimaryNum()%>"><%=list.get(i).getPrimaryNum()%></a></td>
      <td>   
      <%= list.get(i).getParkingName()%>
      </td>
      <td>   
      <%= list.get(i).getParkingType()%>
      </td>
      <td>   
      <%= list.get(i).getParkingBasicFee()%>
      </td>
      <td><input type = "button" name = "delete" id = "delete" value = "삭제"></td>

   </tr>
   <% } %> 
   </tbody>     
</table>
</div>
	</section>

	<%@ include file="../common/footer.jsp" %>

</body>

</html>