create database ThucTap
use ThucTap
go

--Bảng Tên Quyền
create table Roles
(
    ID     varchar(10) primary key,
    Name   nvarchar(50) not null,
    Status int          not null
)
go

--Bảng Phân Quyền
create table Authorities
(
    ID       bigint identity (1, 1) primary key,
    Username varchar(20) not null,
    RoleID   varchar(10) not null
)
go

--Bảng Tài Khoản
create table Accounts
(
    Username    varchar(20) primary key,
    FullName    nvarchar(50)  not null,
    Email       nvarchar(50)  not null,
    NumberPhone varchar(10)   not null,
    Sex         bit           not null,
    Birthday    date          not null,
    Address     nvarchar(200) not null,
    Password    nvarchar(25)  not null,
    Status      int           not null
)
go

--Bảng Hóa Đơn
create table Orders
(
    ID          bigint identity (1, 1) primary key,
    Username    varchar(20)    not null,
    CreateDate  date           not null,
    Address     nvarchar(100)  not null,
    TotalAmount decimal(20, 3) not null, --Tong Tien Hoa Don
    Note        nvarchar(200)  null,	 --Ghi Chu
    Status      int            not null  -- Đã Thanh Toán Là 1, Đã Hủy Là 0
)
go

--Bảng Chi Tiết Hóa Đơn
create table OrderDetail
(
    ID              bigint identity (1, 1) primary key,
    OrderID         bigint         not null, --ID cua Hoa Don
    ProductDetailID bigint         not null, --ID cua Chi Tiet San Pham
    Quantity        int            not null, --So Luong
    Price           decimal(20, 3) not null, --Gia San Pham
)
go

--Bảng Sản Phẩm
create table Products
(
    ID     bigint identity (1, 1) primary key,
    Name   nvarchar(100) not null,
    Status int           not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

--Bảng Màu Sắc
create table Colors
(
    ID        bigint identity (1, 1) primary key,
    CodeColor nvarchar(100) not null,
    Status    int           not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

-- Bảng Kích Cỡ
create table Sizes
(
    ID     bigint identity (1, 1) primary key,
    Name   nvarchar(100) not null,
    Status int           not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

--Bảng Nhà Sản Xuất
create table Producers
(
    ID     bigint identity (1, 1) primary key,
    Name   nvarchar(100) not null,
    Status int           not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

--Bảng Danh Mục Sản Phẩm
create table Categories
(
    ID     bigint identity (1, 1) primary key,
    Name   nvarchar(100) not null,
    Status int           not null --Tồn Tại Là 1, Hết Hạn Là 0
)
go

--Bảng Chi Tiết Sản Phẩm
create table ProductDetails
(
    ID               bigint identity (1, 1) primary key,
    ProductID        bigint         not null, --ID của San Pham
    ColorID          bigint         not null, --ID của Mau Sac
    SizeID           bigint         not null, --ID của Size
    ProducerID       bigint         not null, --ID của Nhà Sản Xuất
    CategoryID       bigint         not null, --ID của Danh Muc
    Images           nvarchar(50)   not null, -- Anh San Pham
    AvaiableQuantity int            not null, --So Luong Ton
    PurchasePrice    decimal(20, 0) not null, --Gia Mua Vao
    CostPrice        decimal(20, 0) not null, --Gia Ban
    Status           int            not null  --Tồn Tại Là 1, Hết Hạn Là 0
)
go

--Bảng Giảm Gía
create table Promotions
(
    ID          bigint identity (1, 1) primary key,
    Code        varchar(50)    not null, --Ma Khuyen Mai
    Name        nvarchar(100)  not null,
    Persen      decimal(20, 3) not null, --Phan Tram Giam
    Quantity    int            not null,
    StartDate   date           not null, --Ngay Bat Dau
    EndDate     date           not null, --Ngay Ket Thuc
    Description nvarchar(100)  not null, --Mo Ta
    Status      int            not null, --Trang Thai; Tồn Tại Là 1, Hết Hạn Là 0, Sắp Ra Mắt Là 2
)
go

--Bảng Giảm Gía Chi Tiết
create table PromotionsProduct
(
    Id              bigint identity (1, 1) primary key,
    PromotionsID    bigint not null, --ID cua Khuyen Mai
    ProductDetailID bigint not null, --ID cua San Pham
    Status          int    not null  --Tồn Tại Là 1, Hết Hạn Là 0
)
go

--Bảng Authorities Nối Với Bảng Accounts
alter table Authorities
    add foreign key (Username) references Accounts (Username)
go

--Bảng Authorities Nối Với Bảng Roles
alter table Authorities
    add foreign key (RoleID) references Roles (ID)
go

--Bảng Orders Nối Với Bảng Accounts
alter table Orders
    add foreign key (Username) references Accounts (Username)
go

--Bảng OrderDetail Nối Với Bảng Orders
alter table OrderDetail
    add foreign key (OrderID) references Orders (ID)
go

--Bảng OrderDetail Nối Với Bảng ProductDetails
alter table OrderDetail
    add foreign key (ProductDetailID) references ProductDetails (ID)
go

--Bảng PrductDetails Nối Với Bảng Categories
alter table ProductDetails
    add foreign key (CategoryID) references Categories (ID)
go

--Bảng ProductDetails Nối Với Bảng Products
alter table ProductDetails
    add foreign key (ProductID) references Products (ID)
go

--Bảng ProductDetails Nối Với Bảng Sizes
alter table ProductDetails
    add foreign key (SizeID) references Sizes (ID)
go

--Bảng ProductDetails Nối Với Bảng Colors
alter table ProductDetails
    add foreign key (ColorID) references Colors (ID)
go

--Bảng ProductDetails Nối Với Bảng Producers
alter table ProductDetails
    add foreign key (ProducerID) references Producers (ID)
go

--Bảng PromotionsProduct Nối Với Bảng Promotions
alter table PromotionsProduct
    add foreign key (PromotionsID) references Promotions (ID)
go

--Bảng PromotionsProduct Nối Với Bảng ProductDetails
alter table PromotionsProduct
    add foreign key (ProductDetailID) references ProductDetails (ID)
go
