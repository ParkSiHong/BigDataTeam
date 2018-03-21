<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
@import url("../css/login.css")

/* @import url("../css/agency.css")  */
/* body{background-color : black}  */

</style>
<script src="../js/jquery-3.2.1.min.js"></script>
<script>
   $(function(){
      
      $("#idcheck").click(function(){
         $.ajax({
            url : "../login.project" ,
            data : {"id" : $("#id").val(),
               "pwd" : $("#pwd").val()},
            dataType:"text",
            success : function(data){
               console.log(data);
               if(data == null) {
                  $("#id").val("");
                  $("#id").focus();
                  $("#pwd").val("");
                  $("#pwd").focus("");
                  alert("아이디 혹은 비밀번호가 틀렸습니다.");  
               }
               else {
                  location.href="../index.jsp";
               }
               
            },
            error : function(data){
            	console.log(data);
            }
            
         });
         
      });
   });
</script>
</head>
<body>
<div class="login-page">
<div class="form">
	<form class="login-form" method="post" action="../login.kdata">
		<input type="text" name="id" id="id" placeholder="아이디"/>
		<input type="password" name="password" id="pwd" placeholder="비밀번호"/>
		<input type="button" value="login" id="idcheck">
		<%@ include file="../common/menu.jsp" %>


</form>
</div>
</div>
</body>
</html>