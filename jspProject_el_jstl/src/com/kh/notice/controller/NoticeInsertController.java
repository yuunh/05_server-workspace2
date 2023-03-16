package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 공지사항 무조건 한글 있으니까 인코딩부터
		request.setCharacterEncoding("UTF-8");
		
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content");
		
		// 로그인한 회원 정보를 얻어내는 방법
		// 1. input type = "hidden"으로 애초에 요청시 숨겨서 전달하기
		// 2. session에 담겨있는 loginUser 활용하는 방법
		
		HttpSession session = request.getSession();
		int userNo = ((Member)session.getAttribute("loginUser")).getUserNo();
		
		Notice n = new Notice();
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		
		// n.setNoticeWriter(userNo + "");
		n.setNoticeWriter(String.valueOf(userNo));
		
		int result = new NoticeService().insertNotice(n);
		
		if (result > 0) { // 성공 => 목록조회 화면으로 url 재요청
			response.sendRedirect(request.getContextPath() + "/list.no");
		} else { // 실패 => 에러문구(공지사항 등록 실패) 담아서 에러메세지 포워딩(에러페이지)
			request.setAttribute("errorMsg", "공지사항 등록에 실패했습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
