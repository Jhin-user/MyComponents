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

use MSM
update ITEM
set item = 'Sting', [dateTime] = '2023-12-6 20:57:30', [count] = 2, isItem = 1, price = 20000
where ID = 'IT005'

use MSM
update ITEM
set item = N'Gỏlden Sting', [dateTime] = '2023-12-6 20:57:30', [count] = 2, isItem = 1, price = 18000
where ID = 'IT005'

use MSM
insert into ITEM values
('IT010', '12', '2023-12-04 16:31:36', 12, 1, 12000)

use MSM
delete ITEM
where ID = 'IT010'