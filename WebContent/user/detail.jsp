<%@page import="bigdata.project.dto.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../common/menu.jsp"%>
<% Client user =(Client) request.getAttribute("user"); 
   out.print(user.getId());
%>
<table>
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>가입일</th>
		<th>비밀번호</th>
	</tr>
	<tr>
		<td><% out.print(user.getId());%></td>
		<td><% out.print(user.getName());%></td>
		<td><% out.print(user.getRegDate());%></td>
		<td><% out.print(user.getPwd());%></td>
	</tr>

</table>
</body>
</html>