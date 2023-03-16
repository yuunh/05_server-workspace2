<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
<%
	Board b = (Board)request.getAttribute("b");
	ArrayList<Attachment> list = (ArrayList<Attachment>)request.getAttribute("list");
%> 
--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.outer {
	background-color: #C7A9CC;
	color: white;
	width: 1000px;
	height: 800px;
	margin: auto;
	margin-top: 50px;
}

.detail-area {
	border: 1px solid white;
	text-align: center;
}
</style>
</head>
<body>

	<%-- <%@ include file="../common/menubar.jsp" %> --%>
	<jsp:include page="../common/menubar.jsp"/>

	<div class="outer">
		<br>
		<h2 align="center">사진게시판 상세보기</h2>
		<br>

		<table class="detail-area" align="center" border="1">
			<!-- (tr>td*4)*5 -->
			<tr>
				<td width="70">제목</td>
				<td colspan="3" width="600">${ b.boardTitle }</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${ b.boardWriter }</td>
				<td>작성일</td>
				<td>${ b.createDate }</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3">
					<p style="height: 50px;">${ b.boardContent }</p>
				</td>
			</tr>
			<tr>
				<td>대표사진</td>
				<td colspan="3">
					<div>
						<%-- 
						<c:forEach var="i" items="${ list }" begin="0" end="0">
						<img
							src="${ i.filePath }${ i.changeName }" alt="" width="500" height="300">
						</c:forEach> 
						--%>
						<img src="${ list[0].filePath }${ list[0].changeName }" alt="" width="500" height="300">
					</div>
				</td>
			</tr>
			<tr>
				<td>상세사진</td>
				<td colspan="3">
					<div>
					
						<c:forEach var="i" items="${ list }"  begin="1">
						<%-- <% for (int i = 1; i < list.size(); i++) { %> --%>
						
						<img
							src="${ i.filePath }${ i.changeName }" alt="" width="200" height="150">
							
						<%-- <% } %> --%>
						</c:forEach>
					</div>
				</td>
			</tr>
		</table>

		<br>

		<div align="center">
			<a href="list.th" class="btn btn-sm btn-secondary">목록가기</a>
		</div>
	</div>
</body>
</html>