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
</head>
<body>
<%@ include file="../common/header2.jsp" %>
    <!-- Services Section -->
    <section id="services">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">주차장 검색</h2>
                   	 	<form action="../parkingsearch.project" method="get">
                   			 <input type="text" name="parkingSearch" placeholder="주소를 입력하세요 " id="parkingSearch">
                    		 <input type="submit" value ="검색" class = "btn-primary">
                    	</form>
                </div>
            </div>      
        </div>
    </section>



<%@ include file="../common/footer.jsp" %>

</body>

</html>
