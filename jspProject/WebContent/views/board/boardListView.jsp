<%@page import="com.kh.board.model.vo.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.kh.common.model.vo.PageInfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
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
        height: 500px;
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
	<%@ include file = "../common/menubar.jsp" %>

    <!-- .outer>br+h2+br -->
    <div class="outer">
        <br>
        <h2 align="center">일반게시판</h2>
        <br>

        <!-- 로그인한 회원만 보여지는 div -->
        <div align="right" style="width: 860px;">
            <button>글작성</button>
            <br><br>
        </div>

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

        <br><br>

        <div class="paging-area" align="center">
        
        	아직 페이징바는 미완성 상태 => 내일 할 거임
            <button>&lt;</button>
            <button>1</button>
            <button>2</button>
            <button>3</button>
            <button>4</button>
            <button>5</button>
            <button>6</button>
            <button>7</button>
            <button>8</button>
            <button>9</button>
            <button>10</button>
            <button>&gt;</button>
        </div>
    </div>
</body>
</html>