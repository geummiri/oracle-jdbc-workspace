CREATE TABLE MEMBER(
    NAME VARCHAR(50),
    AGE NUMBER,
    ADDR VARCHAR(50)
    );

SELECT * FROM MEMBER;

INSERT INTO MEMBER(NAME, AGE, ADDR) VALUES('미리', 20, '서울');

COMMIT;

DROP TABLE MEMBER;


CREATE TABLE MEMBER(
    ID VARCHAR2(20) PRIMARY KEY,
    PASSWORD VARCHAR2(20),
    NAME VARCHAR2(20),
    ADDRESS VARCHAR2(40)
);