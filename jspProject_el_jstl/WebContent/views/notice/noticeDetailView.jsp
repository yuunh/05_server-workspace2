<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
	Notice n = (Notice)request.getAttribute("notice");
	// 글번호, 제목, 내용, 작성자 아이디, 작성일
%> --%>
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
</style>
</head>
<body>

    <%-- <%@ include file = "../common/menubar.jsp" %> --%>
	<jsp:include page="../common/menubar.jsp"/>

    <div class="outer" align="center">
        <br>
        <h2 align="center">공지사항 상세보기</h2>
        <br>

         <table id="detail-area" border="1">
            <!-- (tr>th+td+td+td)*3 -->
            <tr>
                <th width="70">제목</th>
                <td colspan="3" width="430">${ notice.noticeTitle }</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${ notice.noticeWriter }</td>
                <td>작성일</td>
                <td>${ notice.createDate }</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 150px;">${ notice.noticeContent }</p>
                </td>
            </tr>
         </table>
         <br><br>

         <div>
            <a href="list.no" class="btn btn-sm btn-secondary">목록가기</a>

			<!-- 현재 로그인한 사용자가 해당 글을 쓴 본인일 경우 -->
			
			<c:if test="${ not empty loginUser and notice.noticeWriter eq loginUser.userId }">
			<%-- <% if (loginUser != null && n.getNoticeWriter().equals(loginUser.getUserId())) { %> --%>
			
				<a href="updateForm.no?num=${ notice.noticeNo }" class="btn btn-sm btn-warning">수정하기</a> 
				<a href="delete.no?num=${ notice.noticeNo }" class="btn btn-sm btn-danger">삭제하기</a>
			
			<%-- <% } %> --%>
			</c:if>
			
		</div>
    </div>

</body>
</html>