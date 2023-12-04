create database MSM
go

use MSM
create table ITEM(
	ID nvarchar(5) primary key,
	item nvarchar(40) not null,
	[dateTime] datetime not null,
	[count] float not null,
	-- item or kg
	isItem bit default 1,
	price int not null
)
go

/* Query */
use MSM
insert into ITEM values
('IT001', 'vegetable', '2023-12-3 10:10:10', 1.2, 0, 20000),
('IT002', 'st', '2023-12-3 10:10:10', 2, 0, 80000),
('IT003', 'tomato', '2023-12-3 10:10:10', 1, 0, 5000)