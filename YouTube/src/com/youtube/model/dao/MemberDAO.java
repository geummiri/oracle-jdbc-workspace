package com.youtube.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.AbstractListModel;

import com.youtube.model.vo.Member;
import com.youtube.model.vo.Subscribe;

import config.ServerInfo;

public class MemberDAO implements MemberDAOTemplate {

	private Properties p = new Properties();
	
	
	public MemberDAO() {
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
	@Override
	public Connection getConnect() throws SQLException {
		Connection conn = DriverManager.getConnection(ServerInfo.URL, ServerInfo.USER, ServerInfo.PASSWORD);
		return conn;
	}

	@Override
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
	public int register(Member memeber) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("register"));
		
		st.setString(1, memeber.getMemberId());
		st.setString(2, memeber.getMemberPwd());
		st.setString(3, memeber.getMemberNickName());
		
		int result = st.executeUpdate();
		
		closeAll(st, conn);
		
		return result;
	}

	@Override
	public Member login(String id, String password) throws SQLException {
		
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("login"));
		
		st.setString(1,id);
		st.setNString(2, password);
		
		ResultSet rs = st.executeQuery();
		
		Member m = null;
		while(rs.next()) {
			m = new Member();
			m.setMemberId(rs.getString("MEMBER_ID"));
			m.setMemberPwd(rs.getString("MEMBER_PASSWORD"));
			m.setMemberNickName(rs.getString("MEMBER_NICKNAME"));
//			m.setMemberEmail(rs.getString("MEMBER_NICKNAME"));
//			m.setMemberPhone(rs.getString("MEMBER_NICKNAME"));
//			m.setMemberGender(rs.getString("MEMBER_GENDER").charAt(0));
//			m.setMemberAuthority(rs.getString("MEMBER_AUTHORITY"));
		}
		
		closeAll(rs, st, conn);
		
		return m;
	}

	@Override //구독 추가
	public int addSubscribe(Subscribe subscribe) throws SQLException {
		return 0;
	}

	@Override
	public int deleteSubscribe(int subsCode) throws SQLException {
		return 0;
	}

	@Override
	public ArrayList<Subscribe> mySubscribeList(String memberId) throws SQLException {
		return null;
	}

}
