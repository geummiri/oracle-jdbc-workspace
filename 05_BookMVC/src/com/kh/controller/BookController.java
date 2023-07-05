package com.kh.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.kh.model.dao.BookDAO;
import com.kh.model.vo.Book;
import com.kh.model.vo.Member;
import com.kh.model.vo.Rent;

public class BookController {

	private BookDAO dao = new BookDAO();
	private Member member = new Member();

	public ArrayList<Book> printBookAll() {
		
		return null;
	}
	//insert dao에서 하기
	public boolean registerBook(Book book) throws SQLException {
	//dao에서 int로 받아옴	
//		try {
//			int result = dao.registerBook(book);
//			
//			if(result != 0) {
//				return true;
//			};
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	
	if(dao.registerBook(book) > 0) {
		return true;
	 }	else {
	
		return false;
	}
	}
	//delete dao에서 하기  번호로 값 찾아오기
	public boolean sellBook(int no) { 
		
		return false;
	}
	
	public boolean registerMember(Member member) {
		
		return false;
	}
	
	public ArrayList<Rent> printRentBook() {
		
		return null;
	}
	
	public Member login(String id, String password) {
		
		return null;
	}
	//회원탈퇴지만 update로 사용하기 n인 경우만 로그인 되고 y면 안댕
	//y로 update해서 로그인 못하게
	public boolean deleteMember() {
		
		return false;
	}
	//번호로 값 찾아오기
	public boolean rentBook(int no) {
		
		return false;
	}
	//이거 어려우니까 안할거임 
	//본인이 빌린 책만 조회되게끔
	public boolean deleteRent(int no) {
		
		return false;
	}
	
	
}
