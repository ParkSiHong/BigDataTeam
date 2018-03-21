<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
   @import url("../css/login.css")
</style>
<script src="../js/jquery-3.2.1.min.js"></script>
<script>
$(function(){
    //alert("경고");
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    
    $("#idcheck").click( function(){
       
       $.ajax({
          url : "../idcheck.project",
          data : {"id": $("#id").val()},/* input태그에 있는 값을 얻어올때 .val() */
          /* ../idcheck.kdata?id=$("#id").val()와 동일 */
          /* 받을 때도 동일하게 request.getParameter로 */
          dataType : "text",
          success : function(data){
             //console.log("success");
             //console.log(data);
             if(data==1){
                $("#msg").html("중복").css("color","red");
             }
             else
                $("#msg").html("사용가능").css("color","green");
          },
          error : function(){
             console.log("error");
          }
       });
       
    } );
 })

</script>
</head>
<body>

<div class="login-page">
   <div class="form">
   <form class="register-form" action="../register.project" method="post">
        <input type="text" name="id" id="id" placeholder="아이디"/>
         <input type="button" value="중복확인" id="idcheck" style="background-color:#B8AE9C; color:#595241">
         <span id="msg"></span>
         <input type="password" name="pwd" placeholder="비밀번호"/>
         <input type="text" name="name" placeholder="이름"/>
         <input type="text" name="homeaddress" placeholder="집주소"/>
         <input type="text" name="jobaddress" placeholder="회사주소"/>
         <button type="submit" value="회원가입">회원가입</button>

   </form>
   
   
   </div>
</div>   


</body>
</html>