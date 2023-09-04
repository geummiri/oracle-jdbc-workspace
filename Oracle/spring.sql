DROP TABLE MEMBER;

create table member(
    id varchar2(50) primary key,
    password varchar2(100) not null,
    name varchar2(50) not null,
    address varchar2(200),
    auth varchar2(50) default 'ROLE_MEMBER' not null,
    --활성화 되어있는지 확인하는 역할(1 OR 2)
    enabled number(1) default 1 not null
);

SELECT * FROM MEMBER;
COMMIT;