package com.kh.model.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.AbstractListModel;

import com.kh.model.vo.Book;
import com.kh.model.vo.Member;
import com.kh.model.vo.Rent;

import config.ServerInfo;

public class BookDAO implements BookDAOTemplate{

	
	private Properties p = new Properties();
	
	public BookDAO() {
		try { //드라이버 로딩!!!
			p.load(new FileInputStream("src/config/jdbc.properties"));
			Class.forName(ServerInfo.DRIVER_NAME);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 	
			catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override //디비연결
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		return conn;
	}
	

	@Override //자원반납1
	public void closeAll(PreparedStatement st, Connection conn) throws SQLException {
		st.close();
		conn.close();
	}

	@Override //자원반납2
	public void closeAll(ResultSet rs, PreparedStatement st, Connection conn) throws SQLException {
		closeAll(st, conn);
		rs.close();
	}

	@Override
	
	public ArrayList<Book> printBookAll() throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("printBookAll"));
		ResultSet rs = st.executeQuery();
		
		ArrayList<Book> bookList = new ArrayList<>();

		while(rs.next()) {
			bookList.add(new Book(rs.getInt("bk_no"), rs.getString("bk_title"),rs.getString("bk_author")));
		}
		
		closeAll(rs, st, conn);
		
		return bookList;
	}
	//책 등록하기
	@Override
	public int registerBook(Book book) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("registerBook"));
		
		st.setString(1, book.getBkTitle());
		st.setString(2, book.getBkAuthor());
		
		int result = st.executeUpdate();
		
		closeAll(st, conn);
		
		return result;
	}

	@Override //등록한 책 삭제
	public int sellBook(int no) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("sellBook"));
		
		st.setInt(1, no);
		
		int result = st.executeUpdate();
		
		closeAll(st, conn);
		
		return result;
		
	}

	@Override
	public int registerMember(Member member) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("registerMember"));
		
		st.setString(1, member.getMemberId());
		st.setString(2,  member.getMemberPwd());
		st.setString(3, member.getMemberName());
		
		int result = st.executeUpdate();
		
		closeAll(st, conn);
		
		return result;
	}

	@Override //rs.getString("status").charAt(0);
	public Member login(String id, String password) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("login"));
		
		st.setString(1, id);
		st.setString(2, password);
		
		ResultSet rs = st.executeQuery();
		
		Member m = null;
		while(rs.next()) {
			m = new Member();
			m.setMemberNo(rs.getInt("member_no"));
			m.setMemberId(rs.getString("member_id"));
			m.setMemberPwd(rs.getString("member_pwd"));
			m.setMemberName(rs.getString("member_name"));
			m.setStatus(rs.getString("status").charAt(0));
			m.setEnrollDate(rs.getDate("enroll_date"));

		}
		closeAll(rs, st, conn);
		return m;
	}

	@Override //회원탈퇴하기!
	public int deleteMember(String id, String password) throws SQLException {
		
		Connection conn = getConnect(); //연결해
		PreparedStatement st = conn.prepareStatement(p.getProperty("deleteMember"));//프로퍼티 연결해
		
		st.setString(1, id);
		st.setString(2, password);
		
		int result = st.executeUpdate();
		
		closeAll(st, conn);
		
		return result;

	}

	@Override //책 빌리기
	public int rentBook(Rent rent) throws SQLException {
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("rentBook"));
		
		st.setInt(1, rent.getMember().getMemberNo());
		st.setInt(2, rent.getBook().getBkNo());
		
		int result = st.executeUpdate();
		
		closeAll(st, conn);
		
		return result;

	}

	@Override //책 대여 취소하기!!
	public int deleteRent(int no) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("deleteRent"));
		
		st.setInt(1, no);
		
		int result = st.executeUpdate();
		
		closeAll(st, conn);
		
		return result;
	
	}
	@Override
	
	//SQL문 JOIN문 테이블 전부 엮기
	//rent_no, rent_date, bk_title, bk_author
	//조건은 member_id 가지고오니까
	//while문 안에서 Rent rent = new Rent();
	//setter 사용
	//rent.setBook(new Book(rs.getString("bk_title"), rs.getString("bk_authour");
	public ArrayList<Rent> printRentBook(String id) throws SQLException {
		
		Connection conn = getConnect();
		
		PreparedStatement st = conn.prepareStatement(p.getProperty("printRentBook"));
		
		st.setString(1,id);
		ResultSet rs = st.executeQuery();
		
		ArrayList<Rent> rentList =new ArrayList<>();
		
		while(rs.next()) {
			Rent rent = new Rent();
			rent.setRentNo(rs.getInt("rent_no"));
			rent.setRentDate(rs.getDate("rent_date"));
			rent.setBook(new Book(rs.getString("bk_title"), rs.getString("bk_author")));
			rentList.add(rent);
		}
		
		closeAll(rs, st, conn);
		return rentList;
	}
	
}
