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

UPDATE Book SET price = ?  WHERE bookid = ?
     

select * from Book where "+condition+" like '%'||?||'%' " +"order by bookno desc

        
commit;

select * from book;

String sql = "select * from book order by bookno desc";

String sql = "insert into book (bookno ,title,publisher,price) " + 
       		"values ((select nvl(max(bookno),0)+1 from book)  ,?,?,?)";
       		
String sql = "delete from book where bookno = ?";

String sql  = "UPDATE Book SET price = ?  WHERE bookid = ?";
		

String sql = "select * from Book where "+condition+" like '%'||?||'%' " +"order by bookno desc";
