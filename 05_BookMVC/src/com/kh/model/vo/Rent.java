package com.kh.model.vo;

import java.util.Date;

public class Rent {

//	 SQL에서 FK로 지정되어있어서 객체 생성할 때 조심조심
//	
//	RENT_NO NUMBER CONSTRAINT TB_RENT_RENT_NO_PK PRIMARY KEY,
//    -- TB_BOOK, TB_MEMBER 두개 테이블 다 FK 걸어야됨
//    RENT_MEM_NO NUMBER CONSTRAINT TB_RENT_RENT_MEM_NO_FK REFERENCES TB_MEMBER ON DELETE SET NULL,
//    RENT_BOOK_NO NUMBER CONSTRAINT TB_RENT_RENT_BOOK_NO_FK REFERENCES TB_BOOK ON DELETE SET NULL,
//    RENT_DATE DATE DEFAULT SYSDATE
	
	private int rentNo;
	private Member member; //FK 지정되어있어서 객체 가져올 때 조심조심
	private Book book; //FK 지정되어있어서 객체 가져올 때 조심조심
	private Date rentDate;
	
	public Rent() {}

	public Rent(int rentNo, Member member, Book book, Date rentDate) {
		this.rentNo = rentNo;
		this.member = member;
		this.book = book;
		this.rentDate = rentDate;
	}

	public int getRentNo() {
		return rentNo;
	}

	public void setRentNo(int rentNo) {
		this.rentNo = rentNo;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	@Override
	public String toString() {
		return "Rent [rentNo=" + rentNo + ", member=" + member + ", book=" + book + ", rentDate=" + rentDate + "]";
	}
	
}
