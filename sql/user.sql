desc user;

select * from user;

-- join
insert into user values(null, '둘리', 'dooli@gmail.com', '1234', 'male', now());

-- select
select * from user;

-- login
select no, name from user where email = 'dooli@gmail.com' and password = '1234';
    
