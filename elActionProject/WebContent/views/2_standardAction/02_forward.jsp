<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- url로는 http://localhost:8003/ea/views/2_standardAction/02_forward.jsp 찍힘 -->
	<h1>여기는 02_forward.jsp 입니다.</h1>
	
	<jsp:forward page="footer.jsp"></jsp:forward>
	<!-- 잘 사용하지 않지만 if else 분기쳐서 나태내는 페이지 포워딩할 때 주로 쓰임 -->
	
</body>
</html>