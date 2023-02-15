<%@page import="com.kh.board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="resources/css/paginate.css">
<link rel="stylesheet" href="resources/css/ligne.css">
<script type="text/javascript" src="resources/js/paginate.js"></script>

<style>
* {
	font-family: sans-serif;
}

.outer {
	background-color: #C7A9CC;
	color: white;
	width: 1000px;
	height: 550px;
	margin: auto;
	margin-top: 50px;
}

.list-area {
	border: 1px solid white;
	text-align: center;
}
</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp"%>

	<div class="outer">
		<br>
		<h2 align="center">특별게시판</h2>
		<br>

		<div align="right" style="width: 860px;">
			<label for="searchBox">검색</label> 
			<input type="search" id="searchBox">
			<br>
			<br>
		</div>

		<table align="center" class="myTable table hover">
			<thead>
				<tr>
					<th width="70">글번호</th>
					<th width="80">카테고리</th>
					<th width="300">제목</th>
					<th width="90">작성자</th>
					<th width="60">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				<% if (list.isEmpty()) { %>
				<!-- case1. 게시글이 없을 경우 -->
				<tr>
					<td colspan="6">조회된 게시글이 없습니다.</td>
				</tr>
				<% } else { %>
				<!-- case2. 게시글이 있을 경우 -->
				<% for (Board b : list) { %>
				<tr>
					<td><%= b.getBoardNo() %></td>
					<td><%= b.getCategoryNo() %></td>
					<td><%= b.getBoardTitle() %></td>
					<td><%= b.getBoardWriter() %></td>
					<td><%= b.getCount() %></td>
					<td><%= b.getCreateDate() %></td>
				</tr>
				<% } %>
				<% } %>
			</tbody>
		</table>
	</div>
	<script>

    let options = {
        numberPerPage:5, //Cantidad de datos por pagina
        goBar:true, //Barra donde puedes digitar el numero de la pagina al que quiere ir
        pageCounter:true, //Contador de paginas, en cual estas, de cuantas paginas
    };

    let filterOptions = {
        el:'#searchBox' //Caja de texto para filtrar, puede ser una clase o un ID
    };

    paginate.init('.myTable',options,filterOptions);
</script>
</body>
</html>