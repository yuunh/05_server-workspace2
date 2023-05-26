<%@page import="com.kh.board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.model.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
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
        height: 600px;
        margin: auto;
        margin-top: 50px;
    }

    .list-area {
        border: 1px solid white;
        text-align: center;
    }
    
    .list-area>tbody>tr:hover {
	background-color: #A28AA6;
	cursor: pointer;
}
</style>
</head>
<body>
	<%@ include file = "../common/menubar.jsp" %>

    <!-- .outer>br+h2+br -->
    <div class="outer">
        <br>
        <h2 align="center">일반게시판</h2>
        <br>

        <!-- 로그인한 회원만 보여지는 div -->
        <% if (loginUser != null) { %>
        <div align="right" style="width: 860px;">
            <a href="<%= contextPath %>/enrollForm.bo" class="btn btn-sm btn-secondary">글작성</a>
            <br><br>
        </div>
		<% } %>
		
        <table align="center" class="list-area">
            <thead>
                <tr>
                    <th width="70">글번호</th>
                    <th width="80">카테고리</th>
                    <th width="300">제목</th>
                    <th width="100">작성자</th>
                    <th width="50">조회수</th>
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

		<script>
			$(function() {
				$(".list-area>tbody>tr").click(function() {
					location.href='<%= contextPath %>/detail.bo?bno=' + $(this).children().eq(0).text();
				})
			})
		</script> 
		
        <br><br>

        <div class="paging-area" align="center">
        
        	<% if (currentPage != 1) { %>
            	<button onclick="location.href = '<%= contextPath %>/list.bo?cpage=<%= currentPage - 1 %>'">&lt;</button>
            <% } %>
            
            <% for (int p = startPage; p <= endPage; p++) { %>
            	<% if (p == currentPage) { %>
            		<button style="background: #A28AA6" disabled><%= p %></button>
            	<% } else { %>
            		<button onclick="location.href = '<%= contextPath %>/list.bo?cpage=<%= p %>'"><%= p %></button>
            	<% } %>
            <% } %>
            
            <% if (currentPage != maxPage) { %>
            <button onclick="location.href = '<%= contextPath %>/list.bo?cpage=<%= currentPage + 1 %>'">&gt;</button>
       		<% } %>
        </div>
    </div>
</body>
</html>