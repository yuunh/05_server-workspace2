package com.kh.member.model.service;

import java.sql.Connection;

import static com.kh.common.JDBCTemplate.*;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		
		// connection 객체 생성
		Connection conn = getConnection();
		
		// dao 호출
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		close(conn);
		
		return m;
	}
	
	public int insertMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDao().insertMember(conn, m);
		
		// 트랜젝션 처리
		if (result > 0) {
			// 성공
			commit(conn);
		} else {
			// 실패
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public Member updateMember(Member m) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updateMember(conn, m);
		Member updateMem = null;
		
		if (result > 0 ) { // 성공
			commit(conn);
			
			// 갱신된 회원 객체 다시 조회
			updateMem = new MemberDao().selectMember(conn, m.getUserId());
			
		} else { // 실패
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem;
	}
	
	public Member updatePwd(String userId, String userPwd, String updatepwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().updatePwd(conn, userId, userPwd, updatepwd);
		Member updateMem = null;
		
		if (result > 0) { // 성공
			commit(conn);
			
			// 갱신된 회원 객체 다시 조회해오기
			updateMem = new MemberDao().selectMember(conn, userId);
		} else { // 실패
			rollback(conn);
		}
		
		close(conn);
		
		return updateMem;
	}
	
	public int deleteMem(String userId, String userPwd) {
		Connection conn = getConnection();
		
		int result = new MemberDao().deleteMem(conn, userId, userPwd);
		
		close(conn);
		
		return result;
		
	}
	
	public int idCheck(String checkId) {
		Connection conn = getConnection();
		
		int count = new MemberDao().idCheck(conn, checkId);
		
		close(conn);
		
		return count;
	}
}
