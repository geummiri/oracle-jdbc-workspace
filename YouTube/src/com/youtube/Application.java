package com.youtube;

import java.util.Scanner;

import com.youtube.controller.YouTubeController;
import com.youtube.model.dao.ChannelDAO;
import com.youtube.model.vo.Channel;
import com.youtube.model.vo.Member;

public class Application {

	
	private Scanner sc = new Scanner(System.in);
	private YouTubeController yc = new YouTubeController();
	
	

	
	public static void main(String[] args) {
		Application app = new Application();
		Scanner sc = new Scanner(System.in);
		boolean check = true;
		while(check) {
			
			System.out.println("1. 회원가입");
			System.out.println("2. 로그인");
			System.out.println("3. 채널추가");
			System.out.println("4. 채널변경");
			System.out.println("5. 내 채널보기");
			System.out.println("6. 채널삭제");
			System.out.println("9. 종료");
			System.out.print("메뉴 번호 입력 : ");
			switch(Integer.parseInt(sc.nextLine())) {
			case 1:
				app.register();
				break;
			case 2:
				app.login();
				break;
			case 3:
				app.addChannel();
				break;
			case 4:
				app.updateChannel();
				break;
			case 5:
				app.myChannel();
				break;
			case 6:
				app.deleteChannel();
				break;
			case 9:
				check = false;
				System.out.println("프로그램 종료");
				break;
			}
			
		}
		
	}

	public void register() {
		System.out.println("아이디 입력 : ");
		String id = sc.nextLine();
		System.out.println("패스워드 입력 : ");
		String pw = sc.nextLine();
		System.out.println("닉네임 입력 : ");
		String nickname = sc.nextLine();
		
		Member member = new Member();
		member.setMemberId(id);
		member.setMemberPwd(pw);
		member.setMemberNickName(nickname);
		
		if(yc.register(member)) {
			System.out.println("회원가입 완료되었습니다!!!");
		} else {
			System.out.println("회원가입 실패하였습니다ㅠㅠㅠ");
		}
		
	}
	
	public void login() {
		
		System.out.println("아이디 입력 : ");
		String id = sc.nextLine();
		System.out.println("패스워드 입력 : ");
		String pw = sc.nextLine();
		
		Member member = yc.login(id, pw);
		if(member != null) {
			System.out.println(member.getMemberNickName() + "님 환영");
		} else {
			System.out.println("아이디 또는 비밀번호가 틀렸습니다.");
			
		}
		
	}
	
	public void addChannel() {
		yc.login("v", "b");
		System.out.println("채널명 : ");
		String title = sc.nextLine();
		
		Channel channel = new Channel();
		channel.setChannelName(title);
		
		if(yc.addChannel(channel)) {
			System.out.println("채널 추가 성공");
		} else {
			System.out.println("채널 추가 실패 ㅠ");
		}
	}
	
	
	public void updateChannel() {
		yc.login("v", "b");
		yc.myChannel();
//		System.out.println("채널명 : ");
//		String title1 = sc.nextLine();
		
		System.out.println("변경할 채널명 : ");
		String title = sc.nextLine();
		
		
		Channel channel = new Channel();
		channel.setChannelName(title);
		
		if(yc.updateChannel(channel)) {
			System.out.println("채널명 변경 성공");
		} else {
			System.out.println("채널명 변경실패");
		}
	}
	
	public void myChannel() {
		
		yc.login("v", "b");
		
		Channel channel = yc.myChannel();
		System.out.println(channel.getChannelName() + " " + channel.getChannelCode() + " " + channel.getMember());
	}
	
	
	public void deleteChannel() {
		
		yc.login("v", "b");
		
		if(yc.deleteChannel()) {
			System.out.println("삭제성공");
		} else {
			System.out.println("삭제실패");
		}
		
	}
	}
