<%@page import="com.kh.water.model.vo.Water"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();

	Water w = (Water)session.getAttribute("w");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String brand = w.getBrand();
		int price = w.getPrice();
	%>
	
	<form action="<%=contextPath%>/update.wa" method="post">
		<input type="hidden" name="waterNo" value="<%= w.getWaterNo() %>">
		<table>
			<tr>
				<th>생수명</th>
				<td><input type="text" name="brand" value="<%= brand %>"></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="price" value="<%= price %>"></td>
			</tr>
			<tr>
				<th colspan="2">
					<button type="submit">확인</button>
				</th>
			</tr>
		</table>
	</form>

</body>
</html>