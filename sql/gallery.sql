desc gallery;

-- findAll --
select no, url, comments 
	from gallery
order by no desc;

-- insert --
insert into gallery values(null, 'url', '하이');

-- delete --
delete from gallery where no = '1';