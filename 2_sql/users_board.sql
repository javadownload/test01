
/* user table */
drop table users;
create table users(
	id varchar2(10) constraint users_id_pk primary key,
	password varchar2(10) constraint password_not_null not null,
	name varchar2(20),
	role varchar2(10) default 'user' check(role in ('user','admin'))
);

/* board table */
drop table board;
create table board(
	seq number(5) constraint board_seq_pk primary key,
	title varchar2(100) constraint title_not_null not null,
	content varchar2(1000),
	regdate date default sysdate,
	cnt number(5) default 0,
	id varchar2(10) constraint id_fk references users
);

/*회원 등록 */
insert into users values ('admin', '1234', '관리자', 'admin');
insert into users values ('java01', 'java01', '홍자바', 'user');
insert into users values ('java02', '1234', 'lee', 'user');
insert into users values ('java03', '1234', '홍길동', 'user');

insert into users(id, password, name) values ('java04', 'java04', '고길동');
commit;

/* 회원정보 수정 */
update users set role = 'user';
update users set password = '1234' where id='java01';
commit;

/* 로그인 */

select * from users where id='java01' and password ='java01';
select * from users where id='java01' and password ='1234';


/* 게시판 글등록 */
create sequence seq;
drop  sequence seq;

insert into board 
values (seq.nextval, 'java의 정석','java for while loop', '19/05/05', default, 'java01');

insert into board 
values (seq.nextval, 'sql 더쉽게, 더 깊게', 'create grant select',
'19/02/24', default, 'java02');

insert into board (seq,title,contents,id)
values (seq.nextval, 'sql 더쉽게, 더 깊게', 'create grant select', 'java02');

insert into board (seq,title,content,id)
values ((select nvl(max(seq),0)+1 from board), 'sql 더쉽게, 더 깊게', 'create grant select', 'java02');


delete from board;
commit;

select * from board;


delete from board;


select hiredate , to_char(hiredate,'mm') from emp; 



