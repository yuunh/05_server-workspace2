<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();

	String alertMsg = (String) session.getAttribute("alertMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% if (alertMsg != null) { %>
	<script>
		alert("<%= alertMsg %>");
	</script>
		<% session.removeAttribute("alertMsg"); %>
	<% } %>
	
	<form action="<%= contextPath %>/insert.wa" method="post">
		<table>
			<tr>
				<th>생수명</th>
				<td><input type="text" name="brand" required></td>
			</tr>
			<tr>
				<th>가격</th>
				<td><input type="text" name="price" required></td>
			</tr>
			<tr>
				<th colspan="2">
					<button type="submit">입력</button>
					<button type="button" onclick="updatePage();">수정</button>
				</th>
			</tr>
		</table>
	</form>
	
	<script>
		function updatePage() {
			location.href = "<%= contextPath %>/updateForm.wa";
		}
	</script>
	
</body>
</html>