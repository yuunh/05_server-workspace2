<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String year = new SimpleDateFormat("yyyy").format(new Date());
	%>
	
	Copyright © 1998-<%= year %> KH Information Educational Institute All Right Reserved
	<br>
	test : ${ param.test } <br>
</body>
</html>