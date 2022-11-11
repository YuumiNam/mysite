desc board;

select * from user;

select * from board;

insert into board values(null, '안녕하세요', '이선무입니다', 0, now(), max(group_no)+1, 1, 0, 1);

delete from board;

select a.no, a.title, b.name, a.hit, date_format(a.date, '%Y/%m/%d %H:%i:%s') as date
	from board a, user b
    where a.user_no = b.no
order by group_no desc, order_no asc;