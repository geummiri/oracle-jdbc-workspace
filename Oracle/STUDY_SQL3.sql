 --TEST1
 SELECT STUDENT_NAME 학생이름, STUDENT_ADDRESS 주소지
 FROM TB_STUDENT
 ORDER BY 1;
 
 --TEST2
 SELECT STUDENT_NAME, STUDENT_SSN
 FROM TB_STUDENT
 WHERE ABSENCE_YN = 'Y'
 ORDER BY 2 DESC;
 
 --TEST3
 SELECT STUDENT_NAME 학생이름, STUDENT_NO 학번, STUDENT_ADDRESS "거주지 주소"
 FROM TB_STUDENT
 WHERE STUDENT_ADDRESS LIKE '%강원도%' OR STUDENT_ADDRESS LIKE '경기도%'
 AND SUBSTR(STUDENT_NO,1,1)= '9'
 ORDER BY 1;
 
 --TEST4
SELECT PROFESSOR_NAME, PROFESSOR_SSN
FROM TB_PROFESSOR
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE DEPARTMENT_NAME = '법학과'
ORDER BY 2;

--TEST5
SELECT STUDENT_NO, TO_CHAR(POINT,'FM99.00')
FROM TB_STUDENT
JOIN TB_GRADE USING(STUDENT_NO)
WHERE TERM_NO = '200402'
AND CLASS_NO = 'C3118100'
ORDER BY 2 DESC;

--TEST6
SELECT STUDENT_NO, STUDENT_NAME, DEPARTMENT_NAME
FROM TB_STUDENT
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
ORDER BY 2; 

--TEST7
SELECT CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO);

--TEST8
SELECT CLASS_NAME, PROFESSOR_NAME
FROM TB_CLASS C , TB_PROFESSOR P
WHERE C.DEPARTMENT_NO = P.DEPARTMENT_NO
ORDER BY 2 ;

--TEST9
SELECT S.STUDENT_NO 학번, STUDENT_NAME "학생 이름", ROUND(AVG(POINT),1) "전체 평점"
FROM TB_STUDENT S, TB_GRADE G, TB_DEPARTMENT D
WHERE S.STUDENT_NO = G.STUDENT_NO
AND S.DEPARTMENT_NO = D.DEPARTMENT_NO
AND DEPARTMENT_NAME = '음악학과'
GROUP BY S.STUDENT_NO, STUDENT_NAME
ORDER BY 1;

--TEST10
SELECT DEPARTMENT_NAME 학과이름, STUDENT_NAME 학생이름, PROFESSOR_NAME 교수이름
FROM TB_DEPARTMENT D, TB_STUDENT S, TB_PROFESSOR P
WHERE S.COACH_PROFESSOR_NO = P.PROFESSOR_NO
AND D.DEPARTMENT_NO = S.DEPARTMENT_NO
AND STUDENT_NO = 'A313047';

--TEST11
SELECT STUDENT_NAME, TERM_NO
FROM TB_STUDENT S, TB_GRADE G,TB_CLASS C
WHERE S.STUDENT_NO = G.STUDENT_NO
AND G.CLASS_NO = C.CLASS_NO
AND SUBSTR(TERM_NO,1, 4) = '2007'
AND CLASS_NAME = '인간관계론';

--TEST12
--1)
SELECT CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS C , TB_DEPARTMENT D, TB_CLASS_PROFESSOR CP
WHERE C.CLASS_NO = CP.CLASS_NO(+)
AND  C.DEPARTMENT_NO = D.DEPARTMENT_NO
AND CATEGORY = '예체능'
MINUS
SELECT CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS C , TB_DEPARTMENT D, TB_CLASS_PROFESSOR CP
WHERE C.DEPARTMENT_NO(+) = D.DEPARTMENT_NO
AND C.CLASS_NO = CP.CLASS_NO
AND CATEGORY = '예체능';

--2)
SELECT CLASS_NO, CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS C
JOIN TB_DEPARTMENT D ON C.DEPARTMENT_NO = D.DEPARTMENT_NO
WHERE CATEGORY = '예체능'
MINUS
SELECT CP.CLASS_NO, CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS C
JOIN TB_DEPARTMENT D ON C.DEPARTMENT_NO = D.DEPARTMENT_NO
JOIN TB_CLASS_PROFESSOR CP ON C.CLASS_NO = CP.CLASS_NO
WHERE CATEGORY = '예체능';

SELECT C.CLASS_NO, C.CLASS_NAME, D.DEPARTMENT_NAME
FROM TB_CLASS C
JOIN TB_DEPARTMENT D ON (C.DEPARTMENT_NO = D.DEPARTMENT_NO)
WHERE CATEGORY = '예체능'
MINUS
SELECT C.CLASS_NO, CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS C
RIGHT JOIN TB_CLASS_PROFESSOR CP ON (C.CLASS_NO = CP.CLASS_NO)
JOIN TB_DEPARTMENT D ON (C.DEPARTMENT_NO = D.DEPARTMENT_NO);

--종휘답
SELECT CLASS_NO, CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS C
JOIN TB_DEPARTMENT D ON C.DEPARTMENT_NO = D.DEPARTMENT_NO
WHERE CATEGORY = '예체능'
MINUS
SELECT TP.CLASS_NO, CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS_PROFESSOR TP
JOIN TB_CLASS C ON C.CLASS_NO = TP.CLASS_NO
JOIN TB_DEPARTMENT D ON (C.DEPARTMENT_NO = D.DEPARTMENT_NO);

SELECT C.CLASS_NAME, D.DEPARTMENT_NAME
FROM TB_CLASS C
JOIN TB_DEPARTMENT D ON (C.DEPARTMENT_NO = D.DEPARTMENT_NO)
WHERE CATEGORY = '예체능'
AND (C.CLASS_NO, C.CLASS_NAME) NOT IN (
    SELECT CP.CLASS_NO, CLASS_NAME
    FROM TB_CLASS_PROFESSOR CP
    JOIN TB_CLASS C ON (CP.CLASS_NO = C.CLASS_NO)
);

--이게 진짜 답
SELECT CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS C
JOIN TB_DEPARTMENT D ON ( C.DEPARTMENT_NO = D.DEPARTMENT_NO )
WHERE D.CATEGORY = '예체능'
AND C.CLASS_NO IN (
    SELECT CLASS_NO
    FROM TB_CLASS
    MINUS
    SELECT CLASS_NO
    FROM TB_CLASS_PROFESSOR
)
ORDER BY 2;

--전체과목에서 한명도 받지 못한 과목 MINUS하기
--TB_CLASS 에서 TB_CLASS_PROFESSOR 과목을 빼야해
--전체과목이야
SELECT CLASS_NAME, DEPARTMENT_NAME
FROM TB_CLASS
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)


--TEST13
SELECT STUDENT_NAME 학생이름, NVL(PROFESSOR_NAME ,'지도교수미지정') 지도교수
FROM TB_STUDENT S, TB_DEPARTMENT D, TB_PROFESSOR P
WHERE S.DEPARTMENT_NO = D.DEPARTMENT_NO
AND S.COACH_PROFESSOR_NO = P.PROFESSOR_NO(+)
AND DEPARTMENT_NAME  = '서반아어학과';

--TEST14
SELECT S.STUDENT_NO 학번, STUDENT_NAME 이름, DEPARTMENT_NAME 학과이름, ROUND(AVG(G.POINT),1) 평점
FROM TB_STUDENT S, TB_DEPARTMENT D, TB_GRADE G
WHERE S.DEPARTMENT_NO = D.DEPARTMENT_NO
AND S.STUDENT_NO = G.STUDENT_NO
AND ABSENCE_YN = 'N'
GROUP BY S.STUDENT_NO, STUDENT_NAME, DEPARTMENT_NAME
HAVING ROUND(AVG(G.POINT),1) >= 4.0
ORDER BY 1;

--TEST15
SELECT C.CLASS_NO, CLASS_NAME, ROUND(AVG(POINT),1)
FROM TB_CLASS C, TB_GRADE G, TB_DEPARTMENT D
WHERE C.CLASS_NO = G.CLASS_NO
AND C.DEPARTMENT_NO = D.DEPARTMENT_NO
GROUP BY C.CLASS_NO, CLASS_NAME, DEPARTMENT_NAME, CLASS_TYPE
HAVING  DEPARTMENT_NAME = '환경조경학과'
AND CLASS_TYPE LIKE '전공%'
ORDER BY 1;

--TEST16
SELECT STUDENT_NAME, STUDENT_ADDRESS
FROM TB_STUDENT S , TB_DEPARTMENT D
WHERE S.DEPARTMENT_NO = D.DEPARTMENT_NO
AND STUDENT_ADDRESS IS NOT NULL
AND DEPARTMENT_NAME =
(SELECT DEPARTMENT_NAME
FROM  TB_STUDENT S , TB_DEPARTMENT D
WHERE S.DEPARTMENT_NO = D.DEPARTMENT_NO 
AND STUDENT_NAME = '최경희');

--TEST17
SELECT S.STUDENT_NO, STUDENT_NAME
FROM TB_STUDENT S , TB_GRADE G, TB_DEPARTMENT D
WHERE S.STUDENT_NO = G.STUDENT_NO
AND S.DEPARTMENT_NO = D.DEPARTMENT_NO
AND DEPARTMENT_NAME = '국어국문학과'
GROUP BY S.STUDENT_NO, STUDENT_NAME
HAVING AVG(POINT) =
(SELECT MAX(AVG(POINT))
FROM TB_GRADE G, TB_DEPARTMENT D, TB_STUDENT S
WHERE S.DEPARTMENT_NO = D.DEPARTMENT_NO
AND S.STUDENT_NO = G.STUDENT_NO
GROUP BY STUDENT_NAME, DEPARTMENT_NAME
HAVING DEPARTMENT_NAME = '국어국문학과');

--TEST18
SELECT DEPARTMENT_NAME "계열 학과명", ROUND(NVL(AVG(POINT),0),1) 전공평점
FROM TB_GRADE
JOIN TB_CLASS USING(CLASS_NO)
JOIN TB_DEPARTMENT USING(DEPARTMENT_NO)
WHERE CATEGORY IN (SELECT CATEGORY FROM TB_DEPARTMENT WHERE DEPARTMENT_NAME ='환경조경학과')
AND (CLASS_TYPE = '전공선택' OR CLASS_TYPE = '전공필수')
GROUP BY DEPARTMENT_NAME;