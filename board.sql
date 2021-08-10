
--테이블 삭제
drop table board;

--시퀀스 삭제
drop sequence seq_board_no;

--테이블 생성
create table board(
    no number,
    title varchar2(500) not null,
    content varchar2(4000),
    hit number default 0,
    reg_date date not null,
    user_no number not null,
    PRIMARY KEY(no),
    constraint board_fk foreign key(user_no) 
    references users(no)
);

--시퀀스 생성
CREATE SEQUENCE seq_board_no
INCREMENT BY 1 
START WITH 1 
nocache;

--insert문
insert into board values (seq_board_no.nextval, '게시판1', '첫번째 게시글', 0 , sysdate, 1);
insert into board values (seq_board_no.nextval, '게시판2', '두번째 게시글', 0 , sysdate, 1);

select *
from board;

select  b.no,
        b.title,
        b.content,
        b.hit,
        to_char(b.reg_date, 'YY-MM-DD HH24:MI') regDate,
        b.user_no,
        u.name 
from board b, users u 
where b.user_no = u.no
order by b.no desc ;

commit;

update board
set title = '1번 게시물',
    content = '게시글 수정했음'
where no = 3;

update board  set title = '으아아' , content = '아아아아아'  where no = 3;



insert into board values (seq_board_no.nextval, '게시판2', '두번째 게시글', 0 , sysdate, 1);

select  rt.rn,
        rt.no,
        rt.title,
        rt.hit,
        rt.regDate,
        rt.userNo,
        rt.name
from ( select  rownum rn,
               ot.no,
               ot.title,
               ot.hit,
               ot.regDate,
               ot.userNo,
               ot.name
       from( select  b.no,
                     b.title,
                     b.content,
                     b.hit,
                     to_char(b.reg_date, 'YY-MM-DD HH24:MI') regDate,
                     b.user_no userNo,
                     u.name 
             from board b, users u 
             where b.user_no = u.no
             order by b.no desc ) ot ) rt 
where rn >= 1
and rn <= 10;


( select  rownum rn,
        ot.no,
        ot.title,
        ot.hit,
        ot.regDate,
        ot.user_no,
        ot.name
 from( select  b.no,
               b.title,
               b.content,
               b.hit,
               to_char(b.reg_date, 'YY-MM-DD HH24:MI') regDate,
               b.user_no,
               u.name 
       from board b, users u 
       where b.user_no = u.no
       order by b.no desc ) ot ) rt ;
      

select  b.no,
        b.title,
        b.content,
        b.hit,
        to_char(b.reg_date, 'YY-MM-DD HH24:MI') regDate,
        b.user_no,
        u.name 
from board b, users u 
where b.user_no = u.no
order by b.no desc;


select  b.no,
        b.title,
        b.hit,
        to_char(b.reg_date, 'YY-MM-DD HH24:MI') regDate,
        b.user_no userNo,
        u.name 
from board b, users u 
where b.user_no = u.no
and b.title like '%1%'
order by b.no desc;


select count(*)
from board;
