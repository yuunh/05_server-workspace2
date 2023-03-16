<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
<%
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
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

	<%-- <%@ include file = "../common/menubar.jsp" %> --%>
	<jsp:include page="../common/menubar.jsp"/>

    <!-- .outer>br+h2+br -->
    <div class="outer">
        <br>
        <h2 align="center">일반게시판</h2>
        <br>

        <!-- 로그인한 회원만 보여지는 div -->
        
        <c:if test="${ loginUser != null }">
        <%-- <% if (loginUser != null) { %> --%>
        
        <div align="right" style="width: 860px;">
            <a href="enrollForm.bo" class="btn btn-sm btn-secondary">글작성</a>
            <br><br>
        </div>
        
		<%-- <% } %> --%>
		</c:if>
		
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
            
            	<c:choose>
            		<c:when test="${ empty list }">
	            	<%-- <% if (list.isEmpty()) { %> --%>
	            	
	                <!-- case1. 게시글이 없을 경우 -->
	                <tr>
	                    <td colspan="6">조회된 게시글이 없습니다.</td>
	                </tr>
	                
	                </c:when>
	                <c:otherwise>
	                <%-- <% } else { %> --%>
	                
	                <!-- case2. 게시글이 있을 경우 -->
	                
	                	<c:forEach var="b" items="${ list }">
	                	<%-- <% for (Board b : list) { %> --%>
	                	
	                <tr>
	                    <td>${ b.boardNo }</td>
	                    <td>${ b.categoryNo }</td>
	                    <td>${ b.boardTitle }</td>
	                    <td>${ b.boardWriter }</td>
	                    <td>${ b.count }</td>
	                    <td>${ b.createDate }</td>
	                </tr>
	                
	                </c:forEach>
                	<%-- <% } %>
                <% } %> --%>
                	</c:otherwise>
                </c:choose>
                
            </tbody>
        </table>

		<script>
			$(function() {
				$(".list-area>tbody>tr").click(function() {
					location.href='detail.bo?bno=' + $(this).children().eq(0).text();
				})
			})
		</script>
		
        <br><br>

        <div class="paging-area" align="center">
        
        	<c:if test="${ pi.currentPage ne 1 }">
        	<%-- <% if (currentPage != 1) { %> --%>
        	
            	<button onclick="location.href = 'list.bo?cpage=${ pi.currentPage - 1 }'">&lt;</button>
            
            <%-- <% } %> --%>
            </c:if>
            
            <c:forEach var="p" begin="${pi.startPage }" end="${ pi.endPage }">
            <%-- <% for (int p = startPage; p <= endPage; p++) { %> --%>
            
            	<c:choose>
            		<c:when test="${ pi eq currentPage }">
	            	<%-- <% if (p == currentPage) { %> --%>
	            	
	            		<button style="background: #A28AA6" disabled>${ p }</button>
	            	
	            	</c:when>
	            	<c:otherwise>
	            	<%-- <% } else { %> --%>
	            		
	            		<button onclick="location.href = 'list.bo?cpage=${ p }'">${ p }</button>
            		
            		</c:otherwise>
            	<%-- <% } %>
            <% } %> --%>
            	</c:choose>
            </c:forEach>
            
            <c:if test="${ currentPage ne maxPage }">
            <%-- <% if (currentPage != maxPage) { %> --%>
            
            <button onclick="location.href = 'list.bo?cpage=${ currentPage + 1 }'">&gt;</button>
       		
       		<%-- <% } %> --%>
       		</c:if>
        </div>
    </div>
</body>
</html>