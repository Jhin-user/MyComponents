﻿create database MSM
go

use MSM
create table ITEM(
	ID nvarchar(7) primary key,
	item nvarchar(40) not null,
	[dateTime] datetime not null,
	[count] float not null,
	-- item or kg
	isItem bit default 1,
	price int not null
)
go

/* Database */
use master
drop database MSM

/* Query */
use MSM
select * from ITEM where month(dateTime) = 11 and year(dateTime) = 2023

use MSM
select * from ITEM where dateTime between '2023-11-1' and '2023-11-30'

use MSM
insert into ITEM values
('2312001', N'Sườn', '2023-12-01 16:00:59.200', 1.0, 1, 30000),
('2312002', N'Nạc xay', '2023-12-01 16:01:19.503', 1.0, 1, 7000),
('2312003', N'Cà chua', '2023-12-01 16:01:43.170', 1.0, 1, 2000),
('2312004', N'Dưa leo', '2023-12-01 16:01:54.300', 1.0, 1, 4000),
('2312005', N'Bắp cải', '2023-12-01 16:02:04.620', 1.0, 1, 3000),
('2312006', N'Sốt ướp thịt', '2023-12-01 16:02:27.177', 1.0, 1, 8000),
('2312007', N'Warior dâu', '2023-12-02 16:02:44.657', 2.0, 1, 22000),
('2312008', N'Cốt hủ tíu', '2023-12-02 16:03:29.427', 1.0, 1, 15000),
('2312009', N'Cần tây', '2023-12-02 16:03:46.333', 1.0, 1, 2000),
('2312010', N'Sườn non', '2023-12-02 16:03:57.633', 1.0, 1, 56000),
('2312011', N'Coffee', '2023-12-02 16:04:23.090', 1.0, 1, 50000),
('2312012', N'breakfast', '2023-12-03 16:37:15.220', 1.0, 1, 15000),
('2312013', N'Cà chua', '2023-12-03 16:37:37.290', 1.0, 1, 3000),
('2312014', N'Nạc xay', '2023-12-03 16:37:57.517', 1.0, 1, 9000),
('2312015', N'Thanh long', '2023-12-03 16:38:18.200', 1.0, 1, 15000),
('2312016', N'Nước mía', '2023-12-03 16:38:42.633', 1.0, 1, 12000),
('2312017', N'Mì Omachi', '2023-12-03 16:39:09.537', 1.0, 1, 44000),
('2312018', N'Sườn', '2023-12-03 16:39:45.530', 1.0, 1, 34000),
('2312019', N'Dưa leo', '2023-12-03 16:40:02.017', 1.0, 1, 6000),
('2312020', N'Giặt balo', '2023-12-04 16:40:42.120', 1.0, 1, 50000),
('2312021', N'breakfast', '2023-12-05 16:41:08.333', 1.0, 1, 30000),
('2312022', N'Trà', '2023-12-05 16:41:38.227', 1.0, 1, 10000),
('2312023', N'Sốt ướp thịt.', '2023-12-06 16:44:32.357', 1.0, 1, 8000),
('2312024', N'Cà chua.', '2023-12-06 16:44:38.790', 1.0, 1, 6000),
('2312025', N'Dưa leo.', '2023-12-06 16:44:42.747', 2.0, 1, 8000),
('2312026', N'Sườn.', '2023-12-06 16:44:47.533', 1.0, 1, 40000),
('2312027', N'Nạc xay.', '2023-12-06 16:44:51.803', 1.0, 1, 5000),
('2312028', N'Trà Bouncha', '2023-12-06 16:44:59.267', 1.0, 1, 13000),
('2312029', N'Give xăng', '2023-12-06 16:45:04.843', 1.0, 1, 50000),
('2312030', N'Dinner', '2023-12-06 16:44:17.510', 1.0, 1, 40000),
('2312031', N'Bánh mì', '2023-12-07 16:45:33.687', 1.0, 1, 20000),
('2312032', N'Bạc xỉu', '2023-12-07 16:45:50.527', 1.0, 1, 15000),
('2312033', N'Bánh canh', '2023-12-07 16:46:03.827', 1.0, 1, 32000),
('2312034', N'Giặt đồ', '2023-12-07 16:46:17.527', 1.0, 1, 35000)

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
delete from ITEM
where ID = ''