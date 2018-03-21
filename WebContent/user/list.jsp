<%@page import="java.util.List"%>
<%@page import="bigdata.project.dto.Client"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../common/menu.jsp" %>
회원리스트 - list.jsp
<%
	List<Client> list=(List<Client>) request.getAttribute("list");
%>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>가입일</th>
	</tr>
		<% for(int i =0;i<list.size(); i++) {%>
			<tr>
				<th><%= list.get(i).getId() %>
				<th><%= list.get(i).getName() %>
				<th><%= list.get(i).getRegDate() %>
			</tr>
	<%	}%>
</table>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>이름</th>
		<th>가입일</th>
	</tr>	
<c:forEach items="${list}" var="user">
	<tr>
		<td><a href="detail.project?id=${user.id}">${user.id}</a></td>
		<td> ${user.name}</td>
		<td> ${user.regDate}</td>
	</tr>
</c:forEach>
</table>
</body>
</html>