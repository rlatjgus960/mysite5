--users 테이블 삭제
drop table users;

--user 시퀀스 삭제
drop sequence seq_user_no;


--users 테이블 생성
create table users (
    no number,
    id varchar2(20) unique not null,
    password varchar2(20) not null,
    name varchar2(20),
    gender varchar2(10),
    primary key(no)
);

--users 시퀀스 생성
create sequence seq_user_no
increment by 1
start with 1
nocache;

insert into users
values (
    seq_user_no.nextval,
    'rlatjgus960',
    '1234',
    '김서현',
    'female'
);

select *
from users;

