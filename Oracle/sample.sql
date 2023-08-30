--MEMBER 테이블 생성
CREATE TABLE MEMBER (
    ID VARCHAR2(50) PRIMARY KEY,
    PASSWORD VARCHAR2(50) NOT NULL,
    NAME VARCHAR2(50) NOT NULL,
    ADDRESS VARCHAR(100) NOT NULL
);

--MEMBER 테이블 INSERT 구문
INSERT INTO MEMBER(NAME, AGE,ADDR) VALUES ('가가가', 1, '나나나');
COMMIT;

DROP SEQUENCE SEQ_ITEM;
SELECT * FROM MEMBER;
SELECT * FROM MEMBER2;
DROP TABLE ITEM;
DROP TABLE MEMBER2;




-- ITEM 테이블 생성
CREATE TABLE ITEM(
    ITEM_ID NUMBER PRIMARY KEY,
    ITEM_NAME VARCHAR2(45),
    PRICE NUMBER,
    DESCRIPTION VARCHAR2(100),
    PICTURE_URL VARCHAR2(45),
    COUNT NUMBER
);
-- ITEM 테이블 SEQUENCE 생성
CREATE SEQUENCE SEQ_ITEM;
-- ITEM 테이블 INSERT 구문 생성
INSERT INTO ITEM VALUES(SEQ_ITEM.NEXTVAL, '딸기', 4500, '면역력을 강화시켜 질병에 효과적이다.', 'img/berry.jpg', 0);
INSERT INTO ITEM VALUES(SEQ_ITEM.NEXTVAL, '포도', 5000, '철분이 많고 빈혈을 예방한다.', 'img/grape.jpg', 0);
INSERT INTO ITEM VALUES(SEQ_ITEM.NEXTVAL, '귤', 4000, '눈 건강 살리고 피로 회복, 혈압 안정시키는 비타민 A가 많다.', 'img/gual.jpg', 0);
INSERT INTO ITEM VALUES(SEQ_ITEM.NEXTVAL, '키위', 8000, '키위 1개에 함유된 비타민 C는 오렌지의 2배, 비타민 E는 사과의 6배나 된다.', 'img/kiwi.jpg', 0);
INSERT INTO ITEM VALUES(SEQ_ITEM.NEXTVAL, '레몬', 3000, '구연산 비타민C가 풍부하여 피로회복에 도움이 된다.', 'img/lemon.jpg', 0);
COMMIT;
SELECT * FROM ITEM;

-- MEMBER2 테이블 생성
CREATE TABLE MEMBER2 (
    MEMBERID VARCHAR2(50) PRIMARY KEY,
    MEMBERPASS VARCHAR2(50) NOT NULL,
    MEMBERNAME VARCHAR2(50) NOT NULL,
    MEMBERADDR VARCHAR(100) NOT NULL
);

INSERT INTO MEMBER2(MEMBERID, MEMBERPASS, MEMBERNAME, MEMBERADDR)
SELECT ID, PASSWORD, NAME, ADDRESS FROM MEMBER;


DROP TABLE MEMBER;
SELECT * FROM MEMBER;
CREATE TABLE MEMBER(
     NAME VARCHAR2(20),
     AGE NUMBER,
     ADDR VARCHAR2(20)
     );
     
INSERT INTO MEMBER VALUES('미리', 20,'서울');
COMMIT;


CREATE TABLE TB_USER(
USER_NO NUMBER PRIMARY KEY,
USER_ID VARCHAR2(50) UNIQUE NOT NULL,
USER_NAME VARCHAR2(50) NOT NULL,
USER_AGE NUMBER NOT NULL
);

DROP SEQUENCE SEQ_UNO;
CREATE SEQUENCE SEQ_UNO
START WITH 1
INCREMENT BY 1
NOCACHE;


INSERT INTO TB_USER VALUES(SEQ_UNO.NEXTVAL, 'gd_hong', '홍길동', 20);
INSERT INTO TB_USER VALUES(SEQ_UNO.NEXTVAL, 'sh_han', '한소희', 28);
INSERT INTO TB_USER VALUES(SEQ_UNO.NEXTVAL, 'jm_park', '지민', 27);
COMMIT;

SELECT * FROM TB_USER;

SELECT * FROM TB_USER WHERE USER_NO=1;

CREATE TABLE student(
    student_number NUMBER PRIMARY KEY,
    student_name VARCHAR2(7) NOT NULL,
    korean_score NUMBER NOT NULL,
    english_score NUMBER NOT NULL,
    math_score NUMBER NOT NULL
    );

CREATE TABLE manager(
    manager_number NUMBER PRIMARY KEY,
    manager_name VARCHAR2(7) NOT NULL,
    manager_hire_date VARCHAR2(20) DEFAULT 'YYYY-MM-DD' NOT NULL
);

DROP TABLE MANAGER;


SELECT * FROM MEMBER;


CREATE TABLE MEMBER(
USER_NO NUMBER PRIMARY KEY,
USER_ID VARCHAR2(20) NOT NULL,
USER_PW VARCHAR2(20) NOT NULL,
USER_NAME VARCHAR2(20) NOT NULL,
USER_ADDR VARCHAR(50) NOT NULL,
REG_DATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE SEQ_MEMBER;

INSERT INTO MEMBER VALUES(4, 'd', 'd', 'd','d', sysdate);

