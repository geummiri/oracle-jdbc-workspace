package com.youtube.controller;

import java.sql.SQLException;

import com.youtube.model.dao.ChannelDAO;
import com.youtube.model.dao.MemberDAO;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.Member;

public class YouTubeController {

	private MemberDAO memberDao = new MemberDAO();
	private ChannelDAO channelDao = new ChannelDAO();
	private Channel channel = new Channel();
	
	private Member member = new Member();
	
	public boolean register(Member memeber) {
		
		try {
			if(memberDao.register(memeber) == 1) {
				return true;
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return false;
	}
	
	public Member login(String id, String password) {
		
		try {
			member = memberDao.login(id, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return member;
	}
	
	public boolean addChannel(Channel channel) {
		
		channel.setMember(member); //로그인정보 담겨있게끔 하기 위해서
		try {
			
			if(channelDao.addChannel(channel) == 1) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateChannel(Channel channel) {
		
		try {
			channel.setChannelCode(this.channel.getChannelCode());
			
			if(channelDao.updateChannel(channel) == 1) {
				return true;
			};
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean deleteChannel() {
		
		myChannel();
		
		try {
			if(channelDao.deleteChannel(channel.getChannelCode()) ==1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Channel myChannel() {
		
		try {
			channel = channelDao.myChannel(member.getMemberId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return channel;
	}
}
