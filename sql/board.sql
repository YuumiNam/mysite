desc board;

select * from user;

select * from board;

select title, contents from board where no = 17;

insert into board values(null, '안녕하세요', '이선무입니다', 0, now(), (SELECT max(group_no) + 1), 1, 0, 1);

select max(group_no)+1 from board;

delete from board;

delete from board where no = '1';

select a.no, a.title, b.name, a.hit, date_format(a.date, '%Y/%m/%d %H:%i:%s') as date
	from board a, user b
    where a.user_no = b.no
order by group_no desc, order_no asc;

update board set title = "호이호이", contents = "호이호이", date = now() where no = 17;