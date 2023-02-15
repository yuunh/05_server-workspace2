package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
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
}
