DROP TABLE IF EXISTS tblBoard;

CREATE TABLE `tblBoard` (
	`num`               int(11)              NOT NULL  auto_increment  ,
	`name`              varchar(20)                    ,
	`subject`           varchar(50)                    ,
	`content`           text                           ,
	`pos`                smallint(7) unsigned           ,
	`ref`               smallint(7)                    ,
	`depth`             smallint(7) unsigned           ,
	`regdate`          date                           ,
	`pass`              varchar(15)                    ,
	`ip`                  varchar(15)                    ,
	`count`             smallint(7) unsigned           ,
	`filename`         varchar(30)                    ,
	`filesize`           int(11)                        ,
	PRIMARY KEY ( `num` )
);

ÇÑ±Û±úÁü¹æÁö 
alter table user convert to charset utf8;


insert tblBoard(name,content,subject,ref,pos,depth,regdate,pass,count,ip,filename,filesize)
values('aaa', 'bbb', 'ccc', 0, 0, 0, now(), '1111',0, '127.0.0.1', null, 0)


select num,name,pos,ref,depth from tblBoard order by ref desc, pos;

´ä±ÛÃ³¸®
update tblBoard set pos = pos + 1 where ref = ? and pos > ?";

insert tblBoard (name,content,subject,ref,pos,depth,regdate,pass,count,ip)
			values(?,?,?,?,?,?,now(),?,0,?)
