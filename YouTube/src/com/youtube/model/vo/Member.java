package com.youtube.model.vo;

public class Member {

	private String memberId;
	private String memberPwd;
	private String memberNickName;
	private String memberEmail;
	private String memberPhone;
	private char memberGender;
	private String memberAuthority;  //DEFAULT 'ROLE_USER' 되어있음
	
	public Member() {}
	
	public Member(String memberId, String memberPwd, String memberNickName, String memberEmail, String memberPhone,
			char memberGender, String memberAuthority) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNickName = memberNickName;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberGender = memberGender;
		this.memberAuthority = memberAuthority;
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
	public String getMemberNickName() {
		return memberNickName;
	}
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberPhone() {
		return memberPhone;
	}
	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}
	public char getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(char memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberAuthority() {
		return memberAuthority;
	}
	public void setMemberAuthority(String memberAuthority) {
		this.memberAuthority = memberAuthority;
	}
	
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPwd=" + memberPwd + ", memberNickName=" + memberNickName
				+ ", memberEmail=" + memberEmail + ", memberPhone=" + memberPhone + ", memberGender=" + memberGender
				+ ", memberAuthority=" + memberAuthority + "]";
	}

	
	
}
