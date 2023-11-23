-- Create Database
create database spendMng
go

-- Create Table
use spendMng

create table Account(
	id varchar(4) primary key,
	[name] nvarchar(25) not null,
	avatarURL varchar(40) null
)

create table Buy(
	id varchar(5) primary key,
	[day] datetime not null,
	item nvarchar(40) not null,
	price int not null
)
go

-- Add Data
insert into Account values
('AC01', N'Tài khoản 1', null),
('AC02', N'Tài khoản 2', null)

insert into Buy values
('IT001', '2023-11-16 14:40:00', 'Sting', 10),
('IT002', '2023-11-16 14:40:00', 'Sting', 10),
('IT003', '2023-11-16 14:40:00', 'Sting', 10)

-- Query
use spendMng
select * from Item

use spendMng
update Item set day = '2023-11-30 17:00:00', item = 'Sting', price = 10

use spendMng
update Item set day = '2023-11-30 17:00:00', item = 'GoldenSting', price = 9 where id = 'IT001'

-- Modify table
use spendMng
EXEC sp_rename 'Buy', 'Item';
