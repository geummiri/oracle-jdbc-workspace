package com.youtube.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.youtube.model.vo.Channel;

public interface ChannelDAOTemplate {

	Connection getConnect() throws SQLException;
	void closeAll(PreparedStatement st, Connection conn) throws SQLException;
	void closeAll(ResultSet rs, PreparedStatement st, Connection conn) throws SQLException;
	
	//채널보기
	int addChannel(Channel channel) throws SQLException;
	int updateChannel(Channel channel) throws SQLException;
	int deleteChannel(int channelCode) throws SQLException;
	Channel myChannel(String memberId) throws SQLException;
}
