<%@page import="com.kh.common.JDBCTemplate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 
		C(INSERT) | R(SELECT) | U(UPDATE) | D(DELETE)
		
		* 회원서비스
		  로그인(R)
		  회원가입(C)
		  마이페이지(R)
		  정보변경(U)
		  회원탈퇴(U)
		  [아이디중복체크](R) => AJAX
		
		* 공지사항 서비스
		  공지사항 리스트 조회(R) - 페이징 처리 / 상세조회(R)
		  공지사항 작성(C) / 공지사항 수정(U) / 공지사항 삭제(U)
		
		* 일반게시판 서비스
		  게시판 리스트 조회(R) / 상세조회(R)
		  게시글 작성(C) / 게시글 수정(U) / 게시글 삭제(U)
		  [댓글리스트 조회(R) / 댓글 작성(C)] => AJAX
		
		* 사진게시판 서비스
		  게시판 리스트 조회(R) - 썸네일 / 상세조회(R)
		  게시글 작성(C) / 첨부파일 업로드(C)
	 -->
	
	<%@ include file = "views/common/menubar.jsp" %>

</body>
</html>