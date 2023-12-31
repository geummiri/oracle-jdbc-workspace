package com.kh.model.vo;

import java.util.Date;

public class Member {

//	 	MEMBER_NO NUMBER,
//	    MEMBER_ID VARCHAR2(20) UNIQUE,
//	    MEMBER_PWD VARCHAR2(30) NOT NULL,
//	    MEMBER_NAME VARCHAR2(20) NOT NULL,
//	    STATUS VARCHAR2(1) DEFAULT 'N' CHECK(STATUS IN('Y','N')), --Y면 회원탈퇴
//	    ENROLL_DATE DATE DEFAULT SYSDATE NOT NULL,
	
	private int memberNo;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private char status;
	private Date enrollDate; //import할때 util로 해주기 sql로 하면 안됨!!!!!!
	
	public Member() {}

	public Member(int memberNo, String memberId, String memberPwd, String memberName, char status, Date enrollDate) {
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.status = status;
		this.enrollDate = enrollDate;
	}

	public Member(String id, String password, String name) {
		this.memberId = id;
		this.memberPwd = password;
		this.memberName = name;
	}

	public Member(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "Member [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberName="
				+ memberName + ", status=" + status + ", enrollDate=" + enrollDate + "]";
	}
	
	
	
	
}
