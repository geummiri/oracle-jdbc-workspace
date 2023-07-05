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
	
	
//	public BookDAO() {
//		try {
//			p.load(new FileInputStream("src/config/jdbc.properties"));
//			Class.forName(ServerInfo.DRIVER_NAME);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@Override //디비연결
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		return conn;
	}
	
	
//	public Connection getConnect() throws SQLException {
//		Connection con = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
//		return con;
//	}
//	@Override
//	public void closeAll(PreparedStatement st, Connection con) throws SQLException {
//		if (st != null)
//			st.close();
//		if (con != null)
//			con.close();
//	}
	@Override //자원반납
	public void closeAll(PreparedStatement st, Connection conn) throws SQLException {
		st.close();
		conn.close();
	}

	@Override
	public void closeAll(ResultSet rs, PreparedStatement st, Connection conn) throws SQLException {
		closeAll(st, conn);
		rs.close();
	}

	@Override
	
	public ArrayList<Book> printBookAll() throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("printBookAll"));
		
		return null;
	}
	//책 등록하기
	@Override
	public int registerBook(Book book) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("registerBook"));
		
		st.setString(1, book.getBkTitle());
		st.setString(2, book.getBkAuthor());
		
		int result = st.executeUpdate();
		System.out.println(result + "권 등록");
		
		closeAll(st, conn);
		
		return result;
		
		
//		Connection con = getConnect();
//		PreparedStatement st = con.prepareStatement(p.getProperty("registerBook"));
//		st.setString(1, book.getBkTitle());
//		st.setString(2, book.getBkAuthor());
//		int result = st.executeUpdate();
//		System.out.println(result + "권 등록");
//		closeAll(st, con);
//		return result;

	}

	@Override //책 삭제
	public int sellBook(int no) throws SQLException {
		
		return 0;
	}

	@Override
	public int registerMember(Member member) throws SQLException {
		return 0;
	}

	@Override //rs.getString("status").charAt(0);
	public Member login(String id, String password) throws SQLException {
		return null;
	}

	@Override
	public int deleteMember(String id, String password) throws SQLException {
		return 0;
	}

	@Override
	public int rentBook(Rent rent) throws SQLException {
		return 0;
	}

	@Override
	public int deleteRent(int no) throws SQLException {
		return 0;
	}
	@Override
	
	//SQL문 JOIN문 테이블 전부 엮기
	//rent_no, rent_date, bk_title, bk_author
	//조건은 member_id 가지고오니까
	//while문 안에서 Rent rent = new Rent();
	//setter 사용
	//rent.setBook(new Book(rs.getString("bk_title"), rs.getString("bk_authour");
	public ArrayList<Rent> printRentBook(String id) throws SQLException {
		return null;
	}
	
}
