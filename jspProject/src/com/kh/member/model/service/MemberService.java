package com.kh.member.model.service;

import java.sql.Connection;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	
	public Member loginMember(String userId, String userPwd) {
		
		// connection 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// dao 호출
		Member m = new MemberDao().loginMember(conn, userId, userPwd);
		
		JDBCTemplate.close(conn);
		
		return m;
	}
}
