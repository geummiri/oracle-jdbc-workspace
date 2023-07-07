package com.youtube.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.youtube.model.vo.Channel;
import com.youtube.model.vo.Member;

import config.ServerInfo;

public class ChannelDAO implements ChannelDAOTemplate {
	
	private Properties p = new Properties();
	
	public ChannelDAO() {
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

	@Override//채널 추가하기
	public int addChannel(Channel channel) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("addChannel"));
		
		st.setString(1, channel.getChannelName());
		st.setString(2, channel.getMember().getMemberId());
		
		int result = st.executeUpdate();
		
		closeAll(st, conn);
		
		return result;
	}

	@Override//채널 업데이트
	public int updateChannel(Channel channel) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("updateChannel"));
		
		st.setString(1, channel.getChannelName());
		st.setInt(2, channel.getChannelCode());
		
		int result = st.executeUpdate();
		
		closeAll(st, conn);
		return result;
	}

	@Override//채널 삭제!
	public int deleteChannel(int channelCode) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("deleteChannel"));
		
		st.setInt(1, channelCode);
		
		int result = st.executeUpdate();
		
		closeAll(st, conn);
		
		return result;
	}

	@Override //내 채널 보기
	public Channel myChannel(String memberId) throws SQLException {
		Connection conn = getConnect();
		PreparedStatement st = conn.prepareStatement(p.getProperty("myChannel"));
		
		st.setString(1,memberId);
		
		ResultSet rs = st.executeQuery();
		
		Channel channel = null;
		if(rs.next()) {
			channel = new Channel();
			channel.setChannelCode(rs.getInt("CHANNEL_CODE"));
			channel.setChannelName(rs.getString("CHANNEL_NAME"));
			Member member = new Member();
			member.setMemberNickName(rs.getString("MEMBER_NICKNAME"));
		}
		
		closeAll(rs, st, conn);
		
		return channel;
	}

}
