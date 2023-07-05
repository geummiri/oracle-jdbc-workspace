package com.kh.controller;

import java.sql.SQLException;

import com.kh.model.dao.MemberDAO;
import com.kh.model.vo.Member;

public class MemberController {

	private MemberDAO dao = new MemberDAO();

	public boolean joinMembership(Member m)  {
		
		
		// id가 없다면 회원가입 후 true 반환
		try {
			if(dao.getMember(m.getId()) == null) {
				dao.registerMember(m);
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 없다면 false 값 반환
		return false;
		}
	
	
	public String login(String id, String password)  {
		
		// 로그인 성공하면 이름 반환
		Member m = new Member(); //멤버변수 만들어주깅
		
		m.setId(id);
		m.setPassword(password);
		
		try {
			Member result = dao.login(m); //login 메서드에 있는게 Member변수여서 Member를 만들어줘야함
			if(result != null) {
				result.getName(); //이름 반환하기
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
//		Member mem = new Member();
//		
//		mem.setId(id);
//		mem.setPassword(password);
//		
//		try {
//			if(dao.login(mem)!=null) {
//				return dao.login(mem).getName();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
			// 실패하면 null 반환
			return null;
	}
	
	public boolean changePassword(String id, String oldPw, String newPw) {

		// 로그인 했을 때 null이 아닌 경우
		Member m = new Member(); //로그인 Member객체 만들어주기
		m.setId(id);
		m.setPassword(oldPw);
		
		try {
			if(dao.login(m) != null) {
				m.setPassword(newPw);
				dao.updatePassword(m); 
				return true; // 비밀번호 변경 후 true 반환, 아니라면 false 반환
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
//		Member mem = new Member();
//
//		mem.setId(id);
//		mem.setPassword(oldPw);
//
//		if(login(id, oldPw) != null) {
//			try {
//				dao.updatePassword(new Member(mem.getId(), newPw, mem.getName()));
//				return true;
//				}
//			catch (SQLException e) {
//			e.printStackTrace();
//				}
//			}
//		}
		return false;
	}
	
	// 이름 변경!
	public void changeName(String id, String name) {
		
		Member m = new Member();
		m.setId(id);
		m.setName(name);
		
		try {
			dao.updateName(m);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		Member m = new Member();
//		
//		m.setId(id);
//		m.setName(name);
//		
//			try {
//				dao.updateName(new Member(m.getId(), m.getPassword(), name));
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
	}
