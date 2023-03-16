package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.board.model.vo.Reply;
import com.kh.common.model.vo.PageInfo;

import static com.kh.common.JDBCTemplate.*;

public class BoardService {

	/**
	 * 일반게시판 조회
	 * @return
	 */
	public int selectListCount() {
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn);
		
		// 그냥 조회만 하기 때문에 트랜젝션 처리(커밋 롤백) 필요 없음
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 일반게시판 목록 조회
	 * @param pi
	 * @return
	 */
	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 일반게시판 목록 조회
	 * @return
	 */
	public ArrayList<Board> selectList2() {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList2(conn);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 카테고리 목록 조회
	 * @return
	 */
	public ArrayList<Category> selectCategoryList() {
		Connection conn = getConnection();
		
		ArrayList<Category> list = new BoardDao().selectCategoryList(conn);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 게시판 등록
	 * @param b
	 * @param at
	 * @return
	 */
	public int insertBoard(Board b, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new BoardDao().insertBoard(conn, b);
		int result2 = 1; // 0으로 하면 첨부파일 없을 때 분기문 처리 복잡하기 때문에 1로 넣어줌 
		// result2는 첨부파일 있을 때 성공하면 1, 실패하면 0 / 첨부파일 없을 때 둘 다 1
		
		if (at != null) { // 첨부파일 있음
			result2 = new BoardDao().insertAttachment(conn, at);
		}
		
		if (result1 > 0 && result2 > 0) { // 둘 다 성공했을 때
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2; // 1 * 1 = 1
	}
	
	/**
	 * 조회수 증가
	 * @param boardNo
	 * @return
	 */
	public int increaseCount(int boardNo) {
		Connection conn = getConnection();
		
		int result = new BoardDao().increaseCount(conn, boardNo);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	/**
	 * 게시글 조회
	 * @param boardNo
	 * @return
	 */
	public Board selectBoard(int boardNo) {
		Connection conn = getConnection();
		
		Board b = new BoardDao().selectBoard(conn, boardNo);
		
		close(conn);
		
		return b;
	}
	
	/**
	 * 첨부파일 조회
	 * @param boardNo
	 * @return
	 */
	public Attachment selectAttachment(int boardNo) {
		Connection conn = getConnection();
		
		Attachment at = new BoardDao().selectAttachment(conn, boardNo);
		
		close(conn);
		
		return at;
	}
	
	/**
	 * 첨부파일 리스트 조회
	 * @param boardNo
	 * @return
	 */
	public ArrayList<Attachment> selectAttachmentList(int boardNo) {
		Connection conn = getConnection();
		
		ArrayList<Attachment> list = new BoardDao().selectAttachmentList(conn, boardNo);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 게시글 수정
	 * @param b
	 * @param at
	 * @return
	 */
	public int updateBoard(Board b, Attachment at) {
		Connection conn = getConnection();
		
		int result1 = new BoardDao().updateBoard(conn, b);
		
		int result2 = 1;
		
		if (at != null) { // 새로운 첨부파일이 있었을 경우
			
			if (at.getFileNo() != 0) { // 기존에 첨부파일이 있었을 경우
				result2 = new BoardDao().updateAttachment(conn, at);
			} else { // 기존에 첨부파일이 없는 경우 new!
				result2 = new BoardDao().insertNewAttachment(conn, at);
			}
		}
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		return result1 * result2;
	}
	
	public int insertThumbnailBoard(Board b, ArrayList<Attachment> list) {
		Connection conn = getConnection();
		
		int result1 = new BoardDao().insertThBoard(conn, b);
		int result2 = new BoardDao().insertAttachmentList(conn, list);
		
		if (result1 > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}
	
	public ArrayList<Board> selectThumbnailList() {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectThumbnailList(conn);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Reply> selectReplyList(int boardNo) {
		Connection conn = getConnection();
		
		ArrayList<Reply> list = new BoardDao().selectReplyList(conn, boardNo);
		
		close(conn);
		
		return list;
	}
	
	public int insertReply(Reply r) {
		Connection conn = getConnection();
		
		int result = new BoardDao().insertReply(conn, r);
		
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
