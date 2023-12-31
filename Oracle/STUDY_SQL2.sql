--TEST1
SELECT STUDENT_NO 학번,STUDENT_NAME 이름, TO_CHAR(ENTRANCE_DATE, 'YYYY"/"MM"/"DD') 입학년도
FROM TB_STUDENT S, TB_DEPARTMENT D
WHERE S.DEPARTMENT_NO = D.DEPARTMENT_NO
AND D.DEPARTMENT_NAME = '영어영문학과'
ORDER BY STUDENT_NAME;

--TEST2
SELECT PROFESSOR_NAME, PROFESSOR_SSN
FROM TB_PROFESSOR
WHERE LENGTH(PROFESSOR_NAME) != 3;

--TEST3
SELECT PROFESSOR_NAME,

CASE
WHEN 
TO_CHAR(SYSDATE, 'MMDD') > SUBSTR(PROFESSOR_SSN, 3,4)
THEN
(TO_CHAR(SYSDATE, 'YY')+2000 -((SUBSTR(PROFESSOR_SSN, 1,2))+1900))+1

WHEN
TO_CHAR(SYSDATE, 'MMDD') < SUBSTR(PROFESSOR_SSN, 3,4)
THEN
TO_CHAR(SYSDATE, 'YY')+2000 -((SUBSTR(PROFESSOR_SSN, 1,2))+1900)

END "나이"

FROM TB_PROFESSOR
WHERE SUBSTR(PROFESSOR_SSN,8,1) = 1
ORDER BY 2;

--TEST4
SELECT SUBSTR(PROFESSOR_NAME,2,2)
FROM TB_PROFESSOR;

--TEST5
SELECT STUDENT_NO, STUDENT_NAME
FROM TB_STUDENT
WHERE EXTRACT(YEAR FROM ENTRANCE_DATE) - (SUBSTR(STUDENT_SSN, 1 ,2)+1900) > 19;

--TEST6
SELECT TO_CHAR(TO_DATE(20201225, 'YYYYMMDD'), 'DAY') FROM DUAL;

--TEST7 모르겠음
SELECT TO_DATE('99/10/11','YY/MM/DD') 값1, TO_dATE('49/10/11','YY/MM/DD') 값2, 
        TO_DATE('99/10/11','RR/MM/DD') 값3, TO_DATE('49/10/11','RR/MM/DD')+ 값4
FROM DUAL;

--TEST8
SELECT STUDENT_NO, STUDENT_NAME
FROM TB_STUDENT
WHERE EXTRACT(YEAR FROM ENTRANCE_DATE) < 2000;

--TEST9
SELECT ROUND(AVG(POINT),1) 평점
FROM TB_STUDENT S, TB_GRADE T
WHERE S.STUDENT_NO = T.STUDENT_NO
AND S.STUDENT_NO = 'A517178';

--TEST10
SELECT DEPARTMENT_NO 학과번호, COUNT(STUDENT_NAME) 학생수
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY 1;

--TEST11
SELECT COUNT(*)
FROM TB_STUDENT
WHERE COACH_PROFESSOR_NO IS NULL;

--TEST12
SELECT SUBSTR(TERM_NO,1,4), ROUND(AVG(POINT),1)
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY SUBSTR(TERM_NO,1,4)
ORDER BY 1;

--TEST13
--1)
SELECT DEPARTMENT_NO, SUM("ABSENCE_YN")
FROM( SELECT DEPARTMENT_NO, COUNT(ABSENCE_YN) AS  ABSENCE_YN
    FROM TB_STUDENT
    WHERE ABSENCE_YN = 'Y'
    GROUP BY DEPARTMENT_NO
    UNION
    SELECT DEPARTMENT_NO,0
    FROM TB_STUDENT
    GROUP BY DEPARTMENT_NO
    ORDER BY 1
   )
GROUP BY DEPARTMENT_NO
ORDER BY  1;
--2)
SELECT DEPARTMENT_NO "학과코드명",
    COUNT(CASE WHEN ABSENCE_YN = 'Y'
                THEN 1
                ELSE null
                END ) "휴학생 수"
FROM TB_STUDENT
GROUP BY DEPARTMENT_NO
ORDER BY 1;

--3)
--SELECT DEPARTMENT_NO "학과코드명",
--    COUNT(DECODE(ABSENCE_YN, 'Y' , 'Y')
--FROM TB_STUDENT
--GROUP BY DEPARTMENT_NO
--ORDER BY 1;

--TEST14
SELECT STUDENT_NAME, COUNT(STUDENT_NAME)
FROM TB_STUDENT
GROUP BY STUDENT_NAME
HAVING COUNT(STUDENT_NAME) > 1;

--TEST15
SELECT NVL(SUBSTR(TERM_NO,1,4),' '), NVL(SUBSTR(TERM_NO,5,2),' '), ROUND(AVG(POINT),1)
FROM TB_GRADE
WHERE STUDENT_NO = 'A112113'
GROUP BY ROLLUP(SUBSTR(TERM_NO,1,4), SUBSTR(TERM_NO,5,2));







