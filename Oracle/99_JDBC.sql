 DROP TABLE TB_RENT; --FK 걸려있어서 RENT 부터 삭제해야됨
 DROP TABLE TB_BOOK;
 DROP TABLE TB_MEMBER;
 DROP SEQUENCE SEQ_TB_BOOK;
DROP SEQUENCE SEQ_TB_MEMBER;
DROP SEQUENCE SEQ_TB_RENT;
 CREATE TABLE TB_BOOK (
    BK_NO NUMBER,
    BK_TITLE VARCHAR2(100) NOT NULL,
    BK_AUTHOR VARCHAR2(20) NOT NULL,
    CONSTRAINT TB_BOOK_BK_NO_PK PRIMARY KEY(BK_NO) --PK 지정
 );
 
 CREATE TABLE TB_MEMBER(
    MEMBER_NO NUMBER,
    MEMBER_ID VARCHAR2(20) UNIQUE,
    MEMBER_PWD VARCHAR2(30) NOT NULL,
    MEMBER_NAME VARCHAR2(20) NOT NULL,
    STATUS VARCHAR2(1) DEFAULT 'N' CHECK(STATUS IN('Y','N')), --Y면 회원탈퇴
    ENROLL_DATE DATE DEFAULT SYSDATE NOT NULL,
    CONSTRAINT TB_MEMBER_MEMBER_NO_PK PRIMARY KEY(MEMBER_NO)
 );
 
 CREATE TABLE TB_RENT(
    RENT_NO NUMBER CONSTRAINT TB_RENT_RENT_NO_PK PRIMARY KEY,
    -- TB_BOOK, TB_MEMBER 두개 테이블 다 FK 걸어야됨
    RENT_MEM_NO NUMBER CONSTRAINT TB_RENT_RENT_MEM_NO_FK REFERENCES TB_MEMBER ON DELETE SET NULL,
    RENT_BOOK_NO NUMBER CONSTRAINT TB_RENT_RENT_BOOK_NO_FK REFERENCES TB_BOOK ON DELETE SET NULL,
    RENT_DATE DATE DEFAULT SYSDATE
    
 );

 CREATE SEQUENCE SEQ_TB_BOOK;
 CREATE SEQUENCE SEQ_TB_MEMBER;
 CREATE SEQUENCE SEQ_TB_RENT;
 
 SELECT * FROM TB_BOOK;
 
-- printRentBook = 
-- SELECT RENT_NO, RENT_DATE, BK_TITLE, BK_AUTHOR 
-- FROM TB_RENT
-- JOIN TB_BOOK ON(RENT_BOOK_NO = BK_NO) 
-- JOIN tb_member ON (rent_mem_no = member_no)
-- WHERE member_id=?