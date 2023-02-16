package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 일반 방식이 아닌 multipart/form-data로 전송하는 경우 request로부터 값 뽑기 불가!!
		// String boardTitle = request.getParameter("title"); => 이런 방식이 안됨!!
		
		// enctype이 multipart/form-data로 잘 전송되었을 경우 전반적인 내용들이 수행되도록
		if (ServletFileUpload.isMultipartContent(request)) {
			// System.out.println("잘되남 요청하걸아");
			
			// 파일 업로드를 위한 라이브러리 : cos.jar(com.oreilly.servlet의 약자)
			//						  http://www.servlets.com 접속해서 다운로드
			
			// 1. 전달되는 파일을 처리할 작업내용 (전달되는 파일의 용량 제한, 전달된 파일을 저장시킬 폴더 경로)
			// 1_1) 전달되는 파일의 용량 제한 (int maxSize => byte 단위) => 10Mbyte로 제한
			
			/*
			 * byte => kbyte => mbyte => gbyte => tbyte
			 * 1byte == 1024byte
			 * 1mbyte == 1024kbyte == 1024 * 1024 byte
			 * 10mbyte == 10 * 1024 * 1024 byte
			 */
			
			int maxSize = 10 * 1024 * 1024;
			
			// 1_2) 전달된 파일을 저장시킬 폴더의 경로 알아내기 (String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			// System.out.println(savePath); // 경로 확인용
			
			// 2. 전달된 파일의 파일명 수정 및 서버에 업로드(폴더에 저장) 작업
			
			/*
			 * >> HttpServletRequest request(이거 사용X) => MultipartRequest multiRequest 변환
			 * 
			 * 위 구문 한 줄 실행만으로 넘어온 첨부파일이 해당 폴더에 무조건 업로드 됨
			 * 
			 * 단, 업로드 시 파일명 수정해주는게 일반적임! 그래서 파일명 수정시켜주는 객체까지 제시해야 됨
			 * 왜? => 같은 파일명이 존재할 경우 덮어씌워질 수 있고, 파일명에 한글/특문/띄어쓰기가 포함되어 있을 경우 서버에 따라 문제 발생할 수 있음
			 * 
			 * 기본적으로 파일명이 안 겹치도록 수정 작업해주는 객체 있음
			 * => DefaultFileRenamePolicy 객체 (cos.jar에서 제공하는 객체)
			 * => 내부적으로 해당 클래스에 rename() 메소드 실행되면서 파일명 수정된 후 업로드
			 * 
			 * 	  rename(원본파일) {
			 * 		기존에 동일한 파일명이 존재할 경우
			 * 		파일명 뒤에 카운팅된 숫자를 붙여줌 (존재하지 않으면 그냥 넘어감)
			 * 		ex) aaa.jpf, aaa1.jag, aaa2.jpg
			 * 			하늘사진.jpg, 하늘사진1.jpg, 하늘사진2.jpg
			 * 
			 * 		return 수정파일
			 * 	  }
			 * 
			 * 나만의 방식대로 절대 안 겹치도록 rename 할 수 있게 나만의 FileRenamePolicy 클래스 (rename 메소드) 재정의
			 * com.kh.common.MyFileRenamePolicy 클래스 만들기
			 */ 
			
			MultipartRequest multipartRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			// 여기까지 하고 실행
			
			// 3. DB에 기록할 데이터를 뽑아서 vo에 담기
			// 	  > 카테고리 번호, 제목, 내용, 작성자 회원번호 뽑아서 Board insert
			//	  > 넘어온 첨부파일이 있다면 원본명, 수정명, 저장폴더 경로 => Attachment insert
			
			String category = multipartRequest.getParameter("category");
			String boardTitle = multipartRequest.getParameter("title");
			String boardContent = multipartRequest.getParameter("content");
			String boardWriter = multipartRequest.getParameter("userNo");
			
			Board b = new Board();
			b.setCategoryNo(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			b.setBoardWriter(boardWriter);
			
			Attachment at = null; // 첨에는 null로 초기화, 넘어온 첨부파일이 있다면 생성
			// multipartRequest.getOriginalFileName("키값"); : 넘어온 첨부파일이 있었을 경우 "원본명" | 없었을 경우 null
			if (multipartRequest.getOriginalFileName("upfile") != null) { // 첨부파일이 있다
				at = new Attachment();
				at.setOriginName(multipartRequest.getOriginalFileName("upfile"));
				at.setChangeName(multipartRequest.getFilesystemName("upfile"));
				at.setFilePath("resources/board_upfiles/"); // 경로 마지막 / 꼭 붙여주기 그래야 upfiles 안에 저장됨
			}
			
			// 4. 서비스 요청 (요청처리)
			int result = new BoardService().insertBoard(b, at);
			
			// 5. 응답뷰 지정
			if (result > 0) {
				// 성공 => /jsp/list.bo?cpage=1 url 재요청 => 목록페이지
				response.sendRedirect(request.getContextPath() + "/list.bo?cpage=1");
			} else {
				// 실패 => 에러문구 보여주는 에러페이지
				request.setAttribute("errorMsg", "일반게시판 글작성 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
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
