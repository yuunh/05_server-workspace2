<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>JSTL Functions Library</h1>
	<p>el 구문 안에서 쓰임</p>
	
	<c:set var="str" value="How are you?"/>
	
	str : ${ str } <br>
	
	문자열의 길이 : ${ str.length() } <br>
	문자열의 길이 : ${ fn:length(str) } <br> <!-- arrayList도 제시 가능함 => 리스트의 사이즈 -->
	
	모두 대문자로 출력 : ${ fn:toUpperCase(str) } <br>
	모두 소문자로 출력 : ${ fn:toLowerCase(str) } <br>
	
	are의 시작 인덱스 : ${ fn:indexOf(str, "are") } <br>
	
	are => were 변경 : ${ fn:replace(str, "are", "were") } <br> <!-- 원본은 안 바뀜 -->
	
	<c:if test="${ fn:contains(str, 'are') }">
		포함되어 있음
	</c:if>
	
</body>
</html>