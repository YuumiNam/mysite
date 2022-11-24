desc board;

select * from user;

select * from board;

-- 글쓰기
insert into board values(null, '안녕하세요', '이선무입니다', 0, now(), (select max(group_no)+1) , 1, 0, 1);

select max(group_no) from board;

-- update할 정보 불러오기
select title, contents, hit , user_no from board where no = 23;

-- 삭제
delete from board;

delete from board where no = '19' and user_no = '1';

-- findAll
select a.no, a.title, b.name as userName, a.hit, date_format(a.date, '%Y/%m/%d %H:%i:%s') as date, a.user_no as userNo, a.group_no as groupNo, a.order_no as orderNo, a.depth
	from board a, user b
    where a.user_no = b.no
order by group_no desc, order_no asc;

-- 글 수정
update board set title = "호이호이", contents = "호이호이", date = now() where no = 17;

-- 조회수 올리기
update board set hit = (SELECT(max(hit)+1)) where no = 17;

-- 답글
insert into board 
	values(null, '답글' , '답글' , 0, now(), (select max(group_no)+1), 1, (select max(depth)+1), 1);
