######################################################
Table 생성   DDL  (auto commit)

테이블은 실제로 데이터들이 저장되는 곳 이라고 생각하면 쉽게 이해 할 수 있으며, 
CREATE TABLE 명령어를 이용해서 테이블을 생성 할 수 있다. 
######################################################

데이터타입설명
	VARCHAR2 타입
	- 가변길이 문자형 데이터 타입 
	- 최대 길이 : 2000 바이트(반드시 길이 지정) 
	- 다른 타입에 비해 제한이 적다 
	- 일부만 입력시 뒷부분은 NULL 
	- 입력한 값의 뒷부분에 있는 BLANK도 같이 입력 
	- 전혀 값을 주지 않으면 NULL 상태 입력 
	- 지정된 길이보다 길면 입력시 에러 발생 
	- 컬럼 길이의 편차가 심한 경우, NULL 로 입력되는 경우가 많은 경우 VARCHAR2 사용 

	NUMBER 타입
	- 숫자형 데이타 타입, 음수, ZERO, 양수 저장 
	- 전체 자리수는 38자리를 넘을 수 없다 
	- 소수점이 지정되지 않았을 때 소수점이 입력되거나, 지정된 소수점자리 이상 입력되면 반올림되어 저장 
	- 지정한 정수 자리수 이상 입력시 에러 발생 
	- 연산이 필요한 컬럼은 NUMBER타입으로 지정한다. 
	- NUMBER(p,s) 로 지정시 p는 s의 자리수를 포함한 길이므로 감안하여 P의 자리수를 결정 
	- NUMBER 타입은 항상 가변길이므로 충분하게 지정할 것 

	DATE 타입
	- 일자와 시간을 저장하는 날짜형 타입 
	- 일자나 시간의 연산이 빈번한 경우 사용 
	- 포함정보 : 세기, 년도, 월, 일, 시간, 분, 초 
	- NLS_DATE_FORMAT을 이용하여 국가별 특수성을 해결 
	- 특별히 시간을 지정하지 않으면 00:00:00로 입력 됨 
	- 특별히 일자를 지정하지 않았다면 현재월의 1일로 지정 됨 
	- SYSDATE는 현재일과 시간을 제공 


drop table book;	
create table book( 
   bookno   number(5),
   title    varchar2(30),
   author   varchar2(30),
   pubdate  date
);

commit;
rollback;

select * from book;

insert into book values(1,'java','kim',sysdate);
insert into book values(2,'jsp','홍',sysdate);
insert into book values(2,'jsp','홍',sysdate);
insert into book values(2,'jsp','홍','19/02/02');

commit;
rollback;

insert into book (bookno,title) values(3,'db');

insert into book (bookno,title,author,pubdate) 
values(4,'db',null,null);

delete from book;
rollback;
delete from book where title='db';
delete from book where author is null;
delete from book where author = '홍';

desc book;  // 테이블 구조 

alter table book add(price number(7));  //구조 변경 컬럼 추가  

insert into book (bookno,title,author,pubdate,price) 
values(4,'db',null,null,900);

update book set price=1000.99;
commit;
rollback;
update book set price=900 where title='db';
select * from book;

update book set  author='~~~~',price=900,pubdate=sysdate where bookno=3;


update book set price=null;
alter table book modify(price number(7,2));
update book set price=1000.99;
commit;

alter table book drop column price;

######################################################################
#######################################################################
drop table book;	
create table book( 
   bookno   number(5),
   title    varchar2(30),
   author   varchar2(30),
   pubdate  date
);
alter table book add(price number(7)); 
alter table book modify(price number(7,2));
alter table book drop column price;
rename book to book2;
rename book2 to book;

insert into book (bookno,title,author,pubdate) values(4,'db',null,null);
delete from book where author = '홍';
update book set  author='~~~~',price=900,pubdate=sysdate where bookno=3;


delete from book;      rollback 가능
truncate table book;   auto commit;
drop table book;	   auto commit;



select * from book;
######################################################################
######################################################################

select * from emp;
select * from dept;

create table emp2 as select * from emp;
create table dept2 as select * from dept;

insert into dept(deptno,dname,loc) values(50,'EDU','SEOUL');
insert into dept2 values(50,'EDU','SEOUL');
commit;

insert into dept values(10,'EDU','SEOUL');
insert into dept2 values(10,'EDU','SEOUL');

insert into emp (empno,ename,hiredate,sal,deptno) values(9999,'홍길동',sysdate,0,40);

insert into emp2 (empno,ename,hiredate,sal,deptno) 
             values(9999,'홍길동',sysdate,0,90);

insert into emp (empno,ename,hiredate,sal,deptno) 
             values(9999,'홍길동',sysdate,0,40);



             
drop table book;	
create table book( 
   bookno   number(5)  constraint scott_book_pk primary key  ,
   title    varchar2(30) constraint scott_book_title_unique unique,
   author   varchar2(30),
   price    number(7,2) constraint book_price_check check(price>0),
   pubdate  date default sysdate
);     

insert into book (bookno,title,author,price,pubdate) 
              values(1,'....','홍길동',900,sysdate);

insert into book (bookno,title,author,price,pubdate) 
              values(2,'jsp','홍길동',22000,sysdate);
             
insert into book (bookno,title,author,price,pubdate) 
              values(1,'java','김길동',32000,default);
              
commit;             
             
select CONSTRAINT_name 
from user_cons_columns
where table_name='BOOK';             
             
             
drop table book;	
create table book( 
   bookno   number(5) primary key ,
   title    varchar2(30)  unique,
   author   varchar2(30),
   price    number(7,2) check(price>0),
   pubdate  date default sysdate
);   

drop table book;	
create table book( 
   bookno   number(5) ,
   title    varchar2(30)  unique,
   author   varchar2(30),
   price    number(7,2) check(price>0),
   pubdate  date default sysdate
);   

alter table book add CONSTRAINT book_bookno_pk primary key(bookno);
alter table book drop CONSTRAINT book_bookno_pk;

insert into book (bookno,title,author,price,pubdate) 
              values(1,null,'김길동',32000,default);

insert into book (bookno,title,author,price) 
              values(5,null,'최길동',32000);
 
              
select * from book;
commit;

select CONSTRAINT_name 
from user_cons_columns
where table_name='BOOK';             
 
#################################################
emp   dept
#################################################
delete from dept where deptno=50;
commit;

drop table dept2;
create table dept2 as select * from dept;
alter table dept2 add CONSTRAINT dept2_pk primary key(deptno);


drop table emp2;
create table emp2 as select * from emp;
alter table emp2 add CONSTRAINT emp2_pk primary key(empno);

alter table emp2 add CONSTRAINT emp2_fk_mgr foreign key(mgr) references emp2;

alter table emp2 add CONSTRAINT emp2_fk1 foreign key(deptno) references dept2;
delete from dept2 where deptno=20;   X

alter table emp2 drop CONSTRAINT emp2_fk1;

alter table emp2 
add CONSTRAINT emp2_fk1 
foreign key(deptno) references dept2 ON DELETE SET NULL;

delete from dept2 where deptno=20;  O  --삭제되고 자식은 null로 
select * from emp2;
rollback;

alter table emp2 drop CONSTRAINT emp2_fk1;

alter table emp2 
add CONSTRAINT emp2_fk1 
foreign key(deptno) references dept2 ON DELETE CASCADE;

delete from dept2 where deptno=20;  O --삭제되고 자식 튜플도 삭제 
rollback;


select * from dept2;
insert into dept2 values(50,'EDU','SEOUL');

select * from emp2;
insert into emp2 (empno,ename,hiredate,sal,deptno) 
             values(9999,'홍길동',sysdate,0,50);

insert into emp2 (empno,ename,hiredate,sal,deptno) 
             values(7777,'고길동',sysdate,0,null);
             
insert into emp2 (empno,ename,hiredate,sal,deptno,mgr) 
             values(8888,'이길동',sysdate,0,null,10);
 
             
drop table emp2 cascade CONSTRAINT;
drop table dept2 cascade CONSTRAINT;
             

             
###############################################
트랜잭션
###############################################
create table emp2 as select * from emp;
select * from emp2;

sqlplus 창1
delete from emp2 where deptno=10;


sqlplus 창2
update emp2 set comm=0 where deptno=10;
block 되는 거 확인  => 창1에서 commit or rollback 명령 수행하면 lock 풀림 


##############################################
-- FK delete 옵션 실습 
##############################################
delete from dept where deptno=10;
select * from emp;

alter table emp drop CONSTRAINT FK_DEPTNO;

alter table emp 
add CONSTRAINT emp_FK_DEPTNO 
foreign key(deptno)  REFERENCES dept  on delete set null ;

delete from dept where deptno=10;
select * from emp;

alter table emp drop CONSTRAINT emp_FK_DEPTNO;

alter table emp 
add CONSTRAINT emp_FK_DEPTNO 
foreign key(deptno)  REFERENCES dept  on delete cascade ;

delete from dept where deptno=30;
select * from emp;

