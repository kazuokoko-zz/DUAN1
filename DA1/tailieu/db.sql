create database QLK
go

use QLK
go

create table ACCOUNTS
(
	[user] nvarchar(30) not null,
	pass nvarchar(30) not null,
	role nvarchar(2) not null,
	stat nvarchar(2) not null,

	constraint PK_ACCOUNTS primary key ([user])
)
go

create table TYPES
(
	type_id nvarchar(30) not null,
	type_name nvarchar(50) not null,
	type_stat nvarchar(2) not null,
	
	constraint PK_TYPES primary key (type_id)
)
go


create table SUPPLIERS
(
	sup_id int identity(1,1) not null,
	sup_name nvarchar(69) not null,
	sup_phone nvarchar(15) not null,
	sup_email nvarchar(50),
	sup_address nvarchar(123) not null,
	sup_taxcode nvarchar(20) not null,
	sup_fax nvarchar(15),
	sup_bank nvarchar(16),
	sup_evaluate nvarchar(150),
	
	constraint PK_SUPPLIERS primary key (sup_id)
)
go

create table AREAS
(
	id nvarchar(3) not null,
	name int not null,
	stat nvarchar(2) not null,
	
	constraint PK_AREAS primary key (id)
)
go
create table SHELVES
(
	shel_id nvarchar(10) not null,
	shel_stat int not null,
	
	constraint PK_SHELVES primary key (shel_id)
)
go

create table IMPORTS
(
	im_id nvarchar(20) not null,
	im_date datetime not null,
	im_sum_price money not null,
	sup_id int not null,
	[user] nvarchar(30) not null,
	im_checker nvarchar(45) not null,
	im_deliver nvarchar(45) not null,

	constraint PK_IMPORTS primary key (im_id),
	constraint FK_IMPORTS_ACCOUNTS foreign key ([user]) references ACCOUNTS([user]),
	constraint FK_IMPORTS_SUPPLIER foreign key (sup_id) references SUPPLIERS(sup_id)
)
go

create table IMPORT_DETAIL
(
	imd_id int identity(1,1) not null,
	im_id nvarchar(20) not null,
	type_id nvarchar(30) not null,
	imd_amount int not null,
	imd_price money not null,
	
	constraint PK_IMPORT_DETAIL primary key (imd_id),
	constraint FK_IMPORT_DETAIL_TYPES foreign key (type_id) references TYPES(type_id),
	constraint FK_IMPORT_DETAIL_IMPORTS foreign key (im_id) references IMPORTS(im_id)
)

create table PRODUCTS
(
	id int identity(1,1) not null,
	im_id nvarchar(20) not null,
	type_id nvarchar(30) not null,
	serial nvarchar(15) not null unique,
	shel_id nvarchar(10) not null,
	stat nvarchar(15) not null,
	
	constraint PK_PRODUCTS primary key (id),
	constraint FK_PRODUCTS_TYPES foreign key (type_id) references TYPES(type_id),
	constraint FK_PRODUCTS_IMPORTS foreign key (im_id) references IMPORTS(im_id),
	constraint FK_PRODUCTS_SHELVES foreign key (shel_id) references SHELVES (shel_id)
)


create table EXPORTS
(
	ex_id nvarchar(20) not null,
	ex_date datetime not null,
	[user] nvarchar(30) not null,
	ex_type nvarchar(2) not null,
	ex_receiver nvarchar(45) not null,
	ex_publisher nvarchar(45) not null,

	constraint PK_EXPORTS primary key (ex_id),
	constraint FK_EXPORTS_ACCOUNTS foreign key ([user]) references ACCOUNTS([user])
)
go

create table EXPORT_DETAIL
(
	exd_id int identity(1,1) not null,
	ex_id nvarchar(20) not null,
	serial nvarchar(15) not null,
	
	constraint PK_EXPORT_DETAIL primary key (exd_id) ,
	constraint FK_EXPORT_DETAIL_PRODUCTS foreign key (serial) references PRODUCTS(serial),
	constraint FK_EXPORT_DETAIL_EXPORTS foreign key (ex_id) references EXPORTS(ex_id)
)

create table PRODUCERS
(
	id nvarchar(2) not null,
	name nvarchar(20) not null unique,
	stat nvarchar(3) not null,
	
	constraint PK_PRODUCERS primary key (id)
)

create table PHONENAMES
(
	id nvarchar(2) not null,
	name nvarchar(25) not null,
	stat nvarchar(3) not null,
	
	constraint PK_PHONENAMES primary key (id,name),
	constraint FK_PHONENAMES_PRODUCERS foreign key (id) references PRODUCERS(id)
)

create table MEMORIES
(
	id int identity(1,1) not null,
	amount int not null,
	stat nvarchar(3) not null,
	
	constraint PK_MEMORIES primary key (id)
)

create table COLOURS
(
	id int identity(1,1) not null,
	name nvarchar(20) not null,
	stat nvarchar(3) not null,
	
	constraint PK_COLOURS primary key (id)
)


insert into ACCOUNTS([user],pass,role,stat)
values ('admin','admin','QL','AC')
insert into ACCOUNTS([user],pass,role,stat)
values ('nv01','nv01','NV','AC')





























