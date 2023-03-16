package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// --------------- 페이징 처리 ---------------
		// 짱짱 어려움 주의 !! 원리를 파악하자 => 공식 외우기 (내가 코드 짤 일 없음 구글링하면 다 나옴 이해를 하기 위한..)
		
		int listCount;		// 현재 총 게시글 개수
		int currentPage;	// 현재 페이지 (즉, 사용자가 요청한 페이지)
		int pageLimit;		// 페이지 하단에 보여질 페이징바의 페이지 최대 개수 (몇개 단위씩 배치) 1 2 3 4 ....
		int boardLimit;		// 한 페이지 내에 보여질 게시글 최대 개수 (몇개 단위씩)
		
		// 몇 번 페이지(currentPage)를 요청했냐에 따라서 시작 숫자, 끝 숫자 달라짐
		// 4번 선택시 1~10 | 12번 선택시 11~20
		
		// 위의 4개의 변수를 가지고 아래 3개의 값을 구해낼거임		
		int maxPage;		// 가장 마지막 페이지 (총 페이지 수)
		int startPage;		// 페이징바의 시작 수 (현재 페이지 페이징바의 시작 수)
		int endPage;		// 페이징바의 끝 수 (현재 페이지 페이징바의 마지막 수)
		
		// * listCount : 총 게시글 개수
		listCount = new BoardService().selectListCount();
		
		// * curretPage : 현재 페이지 (즉, 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		// * pageLimit : 페이징바의 페이지 최대 개수 (단위)
		pageLimit = 10; // 10개씩 보여줄 거임
		
		// * boardLimit : 게시글 최대 개수 (단위)
		boardLimit = 10; // 10개
		
		/*
		 *  * maxPage : 제일 마지막 페이지 수 (총 페이지 수)
		 *  
		 *  listCount, boardLimit의 영향을 받음
		 *  
		 *  ** 더블로 바꾸고 나누고 올림 처리
		 *  listCount를 더블형으로 바꿔준다
		 *  listCount를 boardLimit로 나누고 올림 처리해주면 그게 maxPage가 됨
		 *  
		 *  ex) 게시글이 10개 단위씩 보여진다는 가정하에
		 *  listCount	boardLimit			maxPage
		 *   100.0			10 => 10.0			10
		 *   101.0			10 => 10.1			11	
		 *   105.0			10 => 10.5			11	
		 *   110.0			10 => 11.0			11
		 *  
		 *  ex) 게시글이 5개 단위씩 보여진다는 가정하에
		 *  listCount	boardLimit			maxPage
		 *   10.0			5 => 2.0			2
		 *   11.0			5 => 2.1			3
		 *   14.0			5 => 2.4			3
		 *   
		 *  ** maxPage = 올림처리(총게시글 개수(실수형) / BoardLimit)
		 */
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		/*
		 *  * startPage : 페이징바의 시작수
		 *  
		 *  pageLimit, currentPage의 영향을 받음
		 *  
		 *  ex) 페이징바의 목록이 10개 단위씩이라는 가정하에 (pageLimit = 10)
		 *  	startPage : 1, 11, 21, 31, ....
		 *  				=> n * pageLimit + 1
		 *  
		 *  currentPage	startPage
		 *  	1			1		=> 0 * pageLimit + 1	=> n = 0
		 *  	5			1		=> 0 * pageLimit + 1	=> n = 0
		 *  	10			1		=> 0 * pageLimit + 1	=> n = 0
		 *  	11			11		=> 1 * pageLimit + 1	=> n = 1
		 *  	15			11		=> 1 * pageLimit + 1	=> n = 1
		 *  	20			11		=> 1 * pageLimit + 1	=> n = 1
		 *  
		 *  	1~10	=> n = 0
		 *  	11~20	=> n = 1
		 *  	21~30	=> n = 2
		 *  
		 *  currentPage - 1 / pageLimit => n
		 *  	0~9
		 *  	10~19
		 *  	20~29
		 *  
		 *  ** StartPage = 					n			 * pageLimit + 1
		 *  			   (currentPage - 1) / pageLimit * pageLimit + 1
		 */
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		/*
		 *  * endPage : 페이징바의 끝 수
		 *  
		 *  startPage, pageLimit의 영향을 받음 (단, maxPage에도 영향을 받긴함)
		 *  
		 *  ex) pageLimit이 10이라는 가정하에 (1 2 3 4 5 6 7 8 9 10)
		 *  startPage : 1	=> endPage : 10
		 *  startPage : 11	=> endPage : 20
		 *  startPage : 21	=> endPage : 30
		 */
		
		endPage = startPage + pageLimit - 1;
		
		// startPage가 11이어서 endPage 20으로 됨 (근데 maxPage가 고작 13 밖에 안되면?)
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이징 처리 자주 사용할거기 때문에 객체로 만들어둘거임
		// com.kh.common.model.vo.PageInfo
		// * 페이징바를 만들 때 필요한 객체
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		// * 현재 요청한 페이지(currentPage)에 보여질 게시글 리스트 boardLimit 수만큼 조회
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
