drop table book;

CREATE TABLE Book (
  bookid      NUMBER(2) PRIMARY KEY,
  bookname    VARCHAR2(40),
  publisher   VARCHAR2(40),
  price       NUMBER(8) 
);


select * from ( select rownum row#,bookid, bookname, publisher, price 
 					   from (select * from Book order by bookid desc)  
 					    ) where row# between 1 and 5 ;
 					    
 					    

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
	contents varchar2(1000),
	writer varchar2(20),
	regdate date default sysdate,
	viewcount number(5) default 0,
	id varchar2(10) constraint id_fk references member
);

 /* member table */
drop table member;
create table member(
	memberid varchar2(10) constraint member_id_pk primary key,
	password varchar2(10) constraint password_not_null not null,
	name varchar2(30),
	email varchar2(40),
	gender varchar2(10) default '남' check(gender in ('남','여'))
);

 /* member table */
drop table member;
create table member(
	memberid varchar2(10) primary key,
	password varchar2(10) not null,
	name varchar2(30),
	email varchar2(40),
	role varchar2(10) default 'user' check(role in ('user','admin'))
);


