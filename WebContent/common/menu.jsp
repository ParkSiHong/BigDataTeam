<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>

a{
	text-decoration : none;
	color : #fed136;
}

ul{
	align : center;
	list-style : none;
	text-align: center;
}
h5{
	color:#586967;
}
</style>
</head>

<body>
<div id = "loginContainer">
<%-- <a href="<%= request.getContextPath() %>/user/login.jsp">로그인</a>
<br> --%>
<a href="<%= request.getContextPath() %>/logout.project">로그아웃</a>
<br>
<h5>아직회원이 아니신가요?</h5>
<a href="<%= request.getContextPath() %>/user/register.jsp">회원가입</a>
</div>
</body>

</html>