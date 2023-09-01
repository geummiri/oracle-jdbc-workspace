INSERT INTO board(no, title, content, writer)
(SELECT SEQ_BOARD.NEXTVAL, title, content, writer FROM board);
commit;
SELECT * FROM BOARD;

--TABLE 생성 후 PRIMARY KEY 조건 걸기 (처음 생성 시 조건 걸지 않았음)
ALTER TABLE board ADD CONSTRAINT PK_BOARD PRIMARY KEY(NO);

SELECT * FROM board WHERE NO=12300;

--페이징 하는 쿼리
--힌트
SELECT /*+ INDEX_DESC(board PK_BOARD) */ 
ROWNUM NUM, NO, TITLE, CONTENT
FROM board
WHERE ROWNUM <= 10;
--순서 다르게 지정하려면 해당 방식으로 -> ROWNUM NUM, NO, TITLE, CONTENT 
--전체 작성하고 싶으면 ->  * 

--순서 다르게 지정해주기
SELECT ROWNUM NUM, NO, TITLE, CONTENT FROM BOARD;

--두번째 페이지 (11~20까지 지정하기)
SELECT /*+ INDEX_DESC(board PK_BOARD) */ 
ROWNUM NUM, NO, TITLE, CONTENT
FROM board
WHERE ROWNUM >= 11 AND ROWNUM <=20;
--ROWNUM은 1부터 시작해야되는데 1을 잘라버려서 아무것도 나오지 않음

--INLINEVIEW 사용해서 값 지정해주기
SELECT NUM, NO, TITLE, WRITER, REGDATE
FROM (
    SELECT /*+ INDEX_DESC(board PK_BOARD) */ 
    ROWNUM NUM, NO, TITLE, WRITER, REGDATE
    FROM board
    WHERE ROWNUM <= 20
)
WHERE NUM > 10;

--세번째 페이지 (21~30까지 지정하기)
SELECT NUM, NO, TITLE, WRITER, REGDATE
FROM (
    SELECT /*+ INDEX_DESC(board PK_BOARD) */ 
    ROWNUM NUM, NO, TITLE, WRITER, REGDATE
    FROM board
    WHERE ROWNUM <= 30
)
WHERE NUM > 20;

SELECT * FROM BOARD WHERE NO =12299;
ALTER TABLE board ADD url VARCHAR(200);