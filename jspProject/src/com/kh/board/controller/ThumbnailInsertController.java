package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ThumbnailInsertController
 */
@WebServlet("/insert.th")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		if (ServletFileUpload.isMultipartContent(request)) {
			// 1_1 전송 용량 제한
			int maxSize = 10 * 1024 * 1024;
			
			// 1_2. 저장시킬 폴더의 물리적인 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/thumbnail_upfiles/");
			
			// 2. 전달된 파일 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			System.out.println(savePath + 1323);
			// 3. db에 기록할 값 뽑기
			// Board insert
			Board b = new Board();
			b.setBoardWriter(multiRequest.getParameter("userNo"));
			b.setBoardTitle(multiRequest.getParameter("title"));
			b.setBoardContent(multiRequest.getParameter("content"));
			
			// Attachment insert => 여러번 insert 할 데이터 뽑기
			ArrayList<Attachment> list = new ArrayList<Attachment>();
			
			for (int i = 1; i <= 4; i++) {
				String key = "file" + i;
				
				if (multiRequest.getOriginalFileName(key) != null) { // 첨부파일이 잘 들어왔는지 확인하는 코드
					// 첨부파일이 존재할 경우
					// Attachment 객체 생성 + 원본명, 수정명, 폴더경로, 파일레벨 담아서 => list 담기
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key)); // 원본명
					at.setChangeName(multiRequest.getFilesystemName(key)); // 수정명
					at.setFilePath("resources/thumbnail_upfiles/"); // 폴더경로
					
					// 파일레벨
					if (i == 1) { // 대표이미지일 경우
						at.setFileLevel(1);
					} else { // 상세이미지일 경우
						at.setFileLevel(2);
					}
					
					list.add(at);
				}
			}
			int result = new BoardService().insertThumbnailBoard(b, list);
			
			if (result > 0) {
				// 성공 => /jsp/list.th url 재요청 => 목록페이지
				request.getSession().setAttribute("alertMsg", "게시글 등록이 되었습니다.");
				response.sendRedirect(request.getContextPath() + "/list.th");
			} else {
				// 에러 문구 보여주는 에러페이지
				request.setAttribute("errorMsg", "사진 게시판 등록 실패");
				request.getRequestDispatcher("views/board/thumbnailListView.jsp").forward(request, response);
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
