package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.model.vo.Person;

/**
 * Servlet implementation class ElServlet
 */
@WebServlet("/el.do")
public class ElServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ElServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * 데이터를 담을 수 있는 JSP 내장객체 종류
		 * 
		 * 1. ServletContext (application Scope)
		 * 	  한 애플리케이션당 단 1개 존재하는 객체
		 * 	  이 영역에 데이터 담으면 애플리케이션 전역에서 사용 가능
		 * 	  공유 범위가 가장 크다 (jsp / servlet / java)
		 * 
		 * 2. HttpSession (session Scope)
		 * 	  한 브라우저당 1개 존재하는 객체
		 * 	  이 영역에 데이터 담으면 jsp / servlet 단에서 사용 가능
		 * 	  공유 범위가 다소 제한적이다
		 * 
		 * 3. HttpServletRequest (request Scope)
		 * 	  요청 때마다 매번 생성되는 객체
		 * 	  이 영역에 데이터 담으면 해당 request 객체를 포워딩 받는 응답 jsp에서만 사용 가능
		 * 	  공유 범위가 응답 jsp뿐!
		 * 
		 * 4. PageContext (page Scope)
		 * 	  jsp 마다 존재하는 객체
		 * 	  공유 범위가 가장 작음 (해당 페이지)
		 * 
		 * 위의 4개의 객체들에
		 * 데이터 담을 때는 .serAttribute("키", 담고자하는 데이터)
		 * 데이터 꺼낼 때는 .getAttribute("키") : 담겨있는 데이터(벨류)
		 * 데이터 삭제할 때는 .removeAttribute("키")
		 * 
		 */
		
		// requestScope에 담기
		request.setAttribute("classRoom", "361강의장");
		request.setAttribute("student", new Person("전재준", 23, "남자"));
		
		// sessionScope에 담기
		HttpSession session = request.getSession();
		session.setAttribute("academy", "KH정보교육원");
		session.setAttribute("teacher", new Person("문동은", 25, "여자"));
		
		// requestScope와 sessionScope에 동일한 키값으로 데이터 담으면 어떻게 될까?
		request.setAttribute("scope", "request");
		session.setAttribute("scope", "session");
		
		// applicationScope에 담기
		ServletContext application = request.getServletContext();
		application.setAttribute("scope", "application");
		
		request.getRequestDispatcher("views/1_EL/01_el.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
