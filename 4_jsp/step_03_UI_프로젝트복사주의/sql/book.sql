mariadb

drop table book;
CREATE TABLE Book (
  bookno      int(4) PRIMARY KEY auto_increment,
  title       VARCHAR(40),
  publisher   VARCHAR(40),
  price       int(8) 
);

insert into book (title,publisher,price)
values ('자바','한빛',900);

insert into book (title,publisher,price) values ('java programming','한빛',900);
insert into book (title,publisher,price) values ('java ','한빛',900);
insert into book (title,publisher,price) values ('html5','이지스',900);
insert into book (title,publisher,price) values ('HTML5 CSS JavaScript','에이콘',900);

select * from book order by bookno desc;
select * from book order by bookno desc limit 1 , 3;

delete from book where bookno = 1;

select * from Book where title like '%html%' order by bookno desc;

select * from Book where publisher like '%명지%' order by bookno desc;

select * from Book where publisher like '%'||?||'%' " +" order by bookno desc";

commit;

/* user table */
drop table user;
create table user(
	id varchar(10)  primary key,
	password varchar(10)  not null,
	name varchar(20),
	role varchar(10) default 'user' check(role in ('user','admin'))
);

insert into user (id,password,name,role) values ('admin','1234','관리자','admin');
insert into user (id,password,name) values ('java01','1234','홍길동');

select * from user;
delete from user where id = 'java01';
UPDATE user SET password = '1234'  WHERE id ='java01';

select * from user where id='admin' and password='1234';
select * from user where id='java01' and password='1234';
########################################################

oracle
drop table book;
CREATE TABLE Book (
  bookno      NUMBER(4) PRIMARY KEY,
  title       VARCHAR2(40),
  publisher   VARCHAR2(40),
  price       NUMBER(8) 
);


insert into book (bookno ,title,publisher,price)
values ((select nvl(max(bookno),0)+1 from book)  ,'자바','한빛',900);


select * from book order by bookno desc

delete from book where bookno = ?

        
commit;

select * from book;

String sql = "select * from book order by bookno desc";

String sql = "insert into book (bookno ,title,publisher,price) " + 
       		"values ((select nvl(max(bookno),0)+1 from book)  ,?,?,?)";
       		
String sql = "delete from book where bookno = ?";

		





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
 					    
select * from book order by bookno desc limit 1 , 5;
 					    

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


---------------------------------------
-- 이름: demo.sql
-- 설명
-- madang 스키마를 생성하고 MADANG 서점 실습테이블과 데이터를 입력한다.
-- 본스크립트는 system 계정에서 실행해야한다.
---
-- SQLPlus 실행방법
-- SQL>@demo.sql


--madang/madang 계정 생성후 실행합니다.
--TABLESPACE는 bit 로 지덩합니다.
--권한은 connect,resource 권한을 부여합니다.
--(UTF-8=> 한글 깨지면 다른이름으로 저장하기 해서 안씨로 저장하면 된다)

conn system/1234

drop user madang;
create user madang identified by madang
default tablespace bit;

grant connect,resource to madang;


conn madang/madang;

drop table Orders;
drop table Customer;
drop table book;
drop table Imported_Book;

CREATE TABLE Book (
  bookid      NUMBER(2) PRIMARY KEY,
  bookname    VARCHAR2(40),
  publisher   VARCHAR2(40),
  price       NUMBER(8) 
);

CREATE TABLE  Customer (
  custid      NUMBER(2) PRIMARY KEY,  
  name        VARCHAR2(40),
  address     VARCHAR2(50),
  phone       VARCHAR2(20)
);


CREATE TABLE Orders (
  orderid NUMBER(2) PRIMARY KEY,
  custid  NUMBER(2) REFERENCES Customer(custid),
  bookid  NUMBER(2) REFERENCES Book(bookid),
  saleprice NUMBER(8) ,
  orderdate DATE
);
-- Book, Customer, Orders 데이터 생성
INSERT INTO Book VALUES(1, '축구의 역사', '굿스포츠', 7300);
INSERT INTO Book VALUES(2, '축구아는 사람', '나무수', 13000);
INSERT INTO Book VALUES(3, '축구의 이해', '대한미디어', 22000);
INSERT INTO Book VALUES(4, '골프 ', '대한미디어', 37000);
INSERT INTO Book VALUES(5, '피겨 ', '굿스포츠', 8000);
INSERT INTO Book VALUES(6, '역도 ', '굿스포츠', 6000);
INSERT INTO Book VALUES(7, '야구', '한빛', 20000);
INSERT INTO Book VALUES(8, '야구2', '한빛', 13000);
INSERT INTO Book VALUES(9, '올림픽 이야기', '한빛', 7500);
INSERT INTO Book VALUES(10, 'Olympic Champions', '한빛', 13000);

INSERT INTO Customer VALUES (1, '박지성', '영국', '000-5110-0001');
INSERT INTO Customer VALUES (2, '이순신', '대한민국 서울', '000-6110-0001');  
INSERT INTO Customer VALUES (3, '홍길동', '대한민국 강원도', '000-7110-0001');
INSERT INTO Customer VALUES (4, '아리', '미국', '000-8110-0001');
INSERT INTO Customer VALUES (5, '고길동', '대한민국 대전',  NULL);

-- 주문(Orders) 테이블의 책값은 할인 판매를 가정함
INSERT INTO Orders VALUES (1, 1, 1, 6000, TO_DATE('2019-07-01','yyyy-mm-dd')); 
INSERT INTO Orders VALUES (2, 1, 3, 21000, TO_DATE('2019-07-03','yyyy-mm-dd'));
INSERT INTO Orders VALUES (3, 2, 5, 8000, TO_DATE('2019-07-03','yyyy-mm-dd')); 
INSERT INTO Orders VALUES (4, 3, 6, 6000, TO_DATE('2019-07-04','yyyy-mm-dd')); 
INSERT INTO Orders VALUES (5, 4, 7, 20000, TO_DATE('2019-07-05','yyyy-mm-dd'));
INSERT INTO Orders VALUES (6, 1, 2, 12000, TO_DATE('2019-07-07','yyyy-mm-dd'));
INSERT INTO Orders VALUES (7, 4, 8, 13000, TO_DATE( '2019-07-07','yyyy-mm-dd'));
INSERT INTO Orders VALUES (8, 3, 10, 12000, TO_DATE('2020-07-08','yyyy-mm-dd')); 
INSERT INTO Orders VALUES (9, 2, 10, 7000, TO_DATE('2020-07-09','yyyy-mm-dd')); 
INSERT INTO Orders VALUES (10, 3, 8, 13000, TO_DATE('2020-07-10','yyyy-mm-dd'));

-- 여기는 3장에서 사용되는 Imported_book 테이블

CREATE TABLE Imported_Book (
  bookid      NUMBER ,
  bookname    VARCHAR(40),
  publisher   VARCHAR(40),
  price       NUMBER(8) 
);
INSERT INTO Imported_Book VALUES(21, 'Zen Golf', 'Pearson', 12000);
INSERT INTO Imported_Book VALUES(22, 'Soccer Skills', 'Human Kinetics', 15000);

COMMIT;


##############
rownum
##############

select rownum,ename,sal,hiredate
from emp;

select rownum,ename,sal,hiredate
from emp
order by sal desc;


select rownum,ename,sal,hiredate
from (select * from emp order by sal desc);

select rownum,ename,sal,hiredate
from (select * from emp order by sal desc)
where rownum<4;    // O

##주의 
select rownum,ename,sal,hiredate
from (select * from emp order by sal desc)
where rownum>4;       // X

select rownum,ename,sal,hiredate
from (select * from emp order by sal desc)
where rownum between 1 and 4;    // O


select rownum,ename,sal,hiredate
from (select * from emp order by sal desc)
where rownum between 4 and 7;    //   X

 
select rownum,ename,sal,hiredate
from (select * from emp order by sal desc)
where rownum between 1 and 4; 


##################################
page 처리
##################################

select * from (
select rownum row#,ename,sal,hiredate
from (select * from emp order by sal desc)
)
where row# between 11 and 15; 


                start       end
1page  1~5      5*0+1       +5
2page  6~10     5*1+1       +10
3page  11~15    5*2+1       +15


select * from (
     select rownum row# , deptno,dname,loc 
     from (select * from dept order by deptno)
) where row# between 3 and 5; 




String sql="";
sql="SELECT r, seq,title,contents,writer,time,viewcount " + 
		"FROM(select rownum r, seq,title,contents,writer,time,viewcount " + 
		"from(select * from board order by time desc)) " + 
		"WHERE r>=? and r<=?";//인덱스 1번부터


PreparedStatement st=con.prepareStatement(sql);
int start=((pageNum-1)*count)+1;
int end=(pageNum*count);
st.setInt(1, start);
st.setInt(2,end);


