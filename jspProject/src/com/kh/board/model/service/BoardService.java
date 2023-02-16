package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.common.model.vo.PageInfo;

import static com.kh.common.JDBCTemplate.*;

public class BoardService {

	public int selectListCount() {
		Connection conn = getConnection();
		
		int listCount = new BoardDao().selectListCount(conn);
		
		// 그냥 조회만 하기 때문에 트랜젝션 처리(커밋 롤백) 필요 없음
		
		close(conn);
		
		return listCount;
	}
	
	public ArrayList<Board> selectList(PageInfo pi) {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Board> selectList2() {
		Connection conn = getConnection();
		
		ArrayList<Board> list = new BoardDao().selectList2(conn);
		
		close(conn);
		
		return list;
	}
	
	public ArrayList<Category> selectCategoryList() {
		Connection conn = getConnection();
		
		ArrayList<Category> list = new BoardDao().selectCategoryList(conn);
		
		close(conn);
		
		return list;
	}
	
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
		
		return result1 * result2; // 1 * 1 = 1
	}
}
