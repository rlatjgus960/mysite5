drop table gallery;

drop sequence seq_gallery_no;


create table gallery (
    no number,
    user_no number,
    content varchar2(1000),
    filePath varchar2(500),
    orgName varchar2(200),
    saveName varchar2(500),
    fileSize number,
    PRIMARY KEY (no),
    CONSTRAINT gallery_no FOREIGN KEY(user_no)
    REFERENCES users(no)
);


CREATE SEQUENCE seq_gallery_no
INCREMENT BY 1 
START WITH 1 
nocache;

select *
from gallery;

insert into gallery 
values(
    seq_gallery_no.nextval,
    13,
    '사진업로드',
    'C:\javaStudy\upload\\1628180768735f8df7045-8db3-46a5-8518-b28bfe72c9fe.jpg',
    'Lee-Kwang-soo.jpg',
    '1628180768735f8df7045-8db3-46a5-8518-b28bfe72c9fe.jpg',
    68001
);

insert into gallery 
values(
    seq_gallery_no.nextval,
    13,
    '송지효 사진',
    'C:\javaStudy\upload\\16281810729737ba07863-4098-4c67-a3aa-b0f19b8c87c5.jpg',
    'Song-Ji-Hyo.jpg',
    '16281810729737ba07863-4098-4c67-a3aa-b0f19b8c87c5.jpg',
    96263
);

select  g.no, 
        g.user_no, 
        g.content, 
        g.filepath, 
        g.orgname, 
        g.savename, 
        g.filesize, 
        u.name
from gallery g, users u
where g.user_no = u.no;

commit;

