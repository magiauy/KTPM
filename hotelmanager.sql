-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 06, 2024 lúc 11:25 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `hotelmanager`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdichvu`
--

CREATE TABLE `chitietdichvu` (
  `ID_HD` int(11) NOT NULL,
  `ID_DV` int(11) NOT NULL,
  `SOLUONG_DV` int(11) NOT NULL,
  `TONGTIEN_DV` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdichvu`
--

INSERT INTO `chitietdichvu` (`ID_HD`, `ID_DV`, `SOLUONG_DV`, `TONGTIEN_DV`) VALUES
(1, 1, 2, 4),
(2, 1, 1, 2),
(2, 2, 1, 3),
(3, 1, 1, 2),
(3, 2, 1, 3),
(3, 3, 1, 2.5),
(4, 1, 1, 2),
(4, 2, 2, 6),
(5, 1, 3, 6),
(5, 2, 1, 3),
(6, 1, 1, 2),
(6, 3, 4, 12),
(7, 2, 1, 3),
(8, 1, 1, 2),
(8, 2, 1, 3),
(9, 1, 1, 2),
(9, 2, 1, 3),
(9, 3, 1, 2.5),
(10, 1, 5, 10),
(11, 3, 1, 2.5),
(12, 1, 1, 2),
(12, 2, 1, 3),
(12, 3, 1, 2.5),
(13, 1, 1, 2),
(13, 2, 1, 3),
(14, 1, 1, 2),
(14, 2, 1, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietphieudat`
--

CREATE TABLE `chitietphieudat` (
  `ID_PHIEUDAT` int(11) NOT NULL,
  `ID_PHG` int(11) NOT NULL,
  `TONGTIEN_CTPHIEUDAT` double NOT NULL,
  `TRANGTHAI` enum('Chưa checkout','Đã checkout') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietphieudat`
--

INSERT INTO `chitietphieudat` (`ID_PHIEUDAT`, `ID_PHG`, `TONGTIEN_CTPHIEUDAT`, `TRANGTHAI`) VALUES
(1, 29, 28, 'Đã checkout'),
(2, 37, 18, 'Đã checkout'),
(3, 42, 54, 'Chưa checkout'),
(4, 44, 18, 'Đã checkout'),
(5, 6, 238, 'Đã checkout'),
(6, 32, 42, 'Đã checkout'),
(7, 35, 56, 'Đã checkout'),
(8, 24, 30, 'Đã checkout'),
(9, 20, 20, 'Chưa checkout'),
(10, 40, 36, 'Đã checkout'),
(11, 27, 28, 'Đã checkout'),
(12, 4, 56, 'Đã checkout'),
(13, 19, 30, 'Đã checkout'),
(14, 11, 8, 'Chưa checkout'),
(15, 14, 8, 'Chưa checkout'),
(16, 1, 8, 'Chưa checkout'),
(17, 19, 10, 'Chưa checkout'),
(18, 18, 10, 'Chưa checkout'),
(19, 23, 10, 'Đã checkout'),
(20, 16, 10, 'Chưa checkout'),
(21, 17, 20, 'Chưa checkout'),
(22, 39, 8, 'Đã checkout'),
(23, 23, 10, 'Đã checkout');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chucvu`
--

CREATE TABLE `chucvu` (
  `ID_CHUCVU` int(11) NOT NULL,
  `TEN_CHUCVU` enum('QUẢN LÝ','NHÂN VIÊN') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chucvu`
--

INSERT INTO `chucvu` (`ID_CHUCVU`, `TEN_CHUCVU`) VALUES
(1, 'QUẢN LÝ'),
(2, 'NHÂN VIÊN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dichvu`
--

CREATE TABLE `dichvu` (
  `ID_DV` int(11) NOT NULL,
  `TEN_DV` varchar(100) NOT NULL,
  `DONGIA_DV` double NOT NULL,
  `MOTA_DV` varchar(300) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `dichvu`
--

INSERT INTO `dichvu` (`ID_DV`, `TEN_DV`, `DONGIA_DV`, `MOTA_DV`) VALUES
(1, 'Ăn sáng', 2, 'các món: bánh mì, nước cam'),
(2, 'Massage', 3, 'massage vai gáy 30 phút'),
(3, 'Giặt ủi', 2.5, 'giặt ủi quần áo bẩn');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `ID_HD` int(11) NOT NULL,
  `TONGTIEN_HD` double NOT NULL,
  `NGAYLAP_HD` date NOT NULL,
  `ID_KH` int(11) DEFAULT NULL,
  `ID_PHIEUDAT` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`ID_HD`, `TONGTIEN_HD`, `NGAYLAP_HD`, `ID_KH`, `ID_PHIEUDAT`) VALUES
(1, 32, '2024-05-06', 1, 1),
(2, 23, '2024-05-06', 1, 4),
(3, 37.5, '2024-05-06', 9, 8),
(4, 64, '2024-05-06', 1, 12),
(5, 39, '2024-05-06', 3, 13),
(6, 70, '2024-05-06', 1, 7),
(7, 241, '2024-05-06', 1, 5),
(8, 23, '2024-05-06', 1, 2),
(9, 43.5, '2024-05-06', 1, 10),
(10, 52, '2024-05-06', 1, 6),
(11, 30.5, '2024-05-06', 6, 11),
(12, 17.5, '2024-05-06', 10, 19),
(13, 15, '2024-05-06', 23, 23),
(14, 13, '2024-05-06', 24, 22);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `ID_KH` int(11) NOT NULL,
  `TEN_KH` varchar(100) NOT NULL,
  `PHAI_KH` enum('NAM','NỮ') NOT NULL,
  `SDT_KH` varchar(10) NOT NULL,
  `EMAIL_KH` varchar(200) NOT NULL,
  `CCCD_KH` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`ID_KH`, `TEN_KH`, `PHAI_KH`, `SDT_KH`, `EMAIL_KH`, `CCCD_KH`) VALUES
(1, 'Nguyễn Thế Anh', 'NAM', '0456787665', 'theanh124@gmail.com', '056789766789'),
(2, 'Lê Ngọc Bình An', 'NỮ', '0987656789', 'ngocbinh456@gmail.com', '056785435678'),
(3, 'Nguyễn Quốc Việt', 'NAM', '0567898776', 'quocviet123@gmail.com', '056789877654'),
(5, 'Bình Anh Quân', 'NAM', '0878987667', 'anhquan23@gmail.com', '067890988765'),
(6, 'Nguyễn Phúc Quân', 'NAM', '0678754559', 'phucquan123@gmail.com', '056345777898'),
(9, 'Đỗ Bảo Châu', 'NỮ', '0678765667', 'chaubao12@gmail.com', '089786544567'),
(10, 'Hoàng Minh Tùng', 'NAM', '0567654556', 'bon123@gmail.com', '056787655456'),
(19, 'Lý Hoàng Ánh Tuyết', 'NỮ', '0656765445', 'tuyethoang22@gmail.com', '05678988658'),
(20, 'Phạm Thị Thuy Thủy', 'NỮ', '0656765444', 'thuythuy33@gmail.com', '067876544565'),
(21, 'Nguyễn Đức Hòa', 'NAM', '0656765448', 'henxui123@gmail.com', '076765678765'),
(22, 'Lê Thanh Uyên', 'NỮ', '0656765422', 'boru13@gmail.com', '078765666787'),
(23, 'Huỳnh Trần Nhật Hạ', 'NỮ', '0456576554', 'sunny22@gmail.com', '067876544111'),
(24, 'Đinh Hoàng Cát Tiên', 'NỮ', '0567876554', 'tien33@gmail.com', '056787655432'),
(25, 'Nguyễn Nhật Minh', 'NAM', '0567876554', 'minh3334@gmail.com', '067898766543'),
(26, 'Lê Đức Trí', 'NAM', '0567876554', 'trijames22@gmail.com', '056787655457');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaiphong`
--

CREATE TABLE `loaiphong` (
  `ID_LOAIPHG` int(11) NOT NULL,
  `TEN_LOAIPHG` varchar(100) NOT NULL,
  `DONGIA_PHG` double NOT NULL,
  `MOTA_PHG` varchar(300) NOT NULL,
  `TRANGTHAI_LOAIPHG` enum('Hoạt động','Không hoạt động') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `loaiphong`
--

INSERT INTO `loaiphong` (`ID_LOAIPHG`, `TEN_LOAIPHG`, `DONGIA_PHG`, `MOTA_PHG`, `TRANGTHAI_LOAIPHG`) VALUES
(1, 'Phòng đơn bình dân', 8, 'giường đơn , có tivi, nhà vệ sinh', 'Hoạt động'),
(2, 'Phòng đôi bình dân', 10, 'giường đôi, có tivi, nhà vệ sinh', 'Hoạt động'),
(3, 'Phòng đơn VIP', 14, 'giường đơn, tivi lớn, có bồn tắm, view biển', 'Hoạt động'),
(4, 'Phòng đôi VIP', 18, 'giường đôi, tivi lớn,  có bồn tắm, bếp ăn, view biển', 'Hoạt động');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `ID_NV` int(11) NOT NULL,
  `TEN_NV` varchar(100) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `SDT_NV` varchar(10) NOT NULL,
  `CCCD_NV` varchar(12) NOT NULL,
  `PHAI_NV` enum('NAM','NỮ') NOT NULL,
  `ID_CHUCVU` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`ID_NV`, `TEN_NV`, `EMAIL`, `SDT_NV`, `CCCD_NV`, `PHAI_NV`, `ID_CHUCVU`) VALUES
(1, 'Nguyễn Văn An', 'annguyen123@gmail.com', '0356787997', '056754344567', 'NAM', 1),
(2, 'Nguyễn Hoa Cúc Anh', 'maihoa123@gmail.com', '0765647800', '056785433567', 'NỮ', 2),
(3, 'Nguyễn An Nhiên', 'annhien123@gmail.com', '0876789887', '065688766567', 'NỮ', 2),
(4, 'Hà Quốc Việt', 'quocviet123@gmail.com', '0898989776', '076789888765', 'NAM', 2),
(5, 'Ngô Đức Trọng', 'trongngo02@gmail.com', '0567898776', '056787666453', 'NAM', 1),
(6, 'Nguyễn Văn A', 'vana234@gmail.com', '0567876554', '067876566787', 'NAM', 2),
(7, 'Nguyễn Lâm', 'lammnguyen34@gmail.com', '0678765667', '067876544566', 'NAM', 2),
(8, 'Nguyễn Duy Lâm', 'lam12@gmail.com', '0545676554', '067564566545', 'NAM', 2),
(9, 'Nguyễn Thanh Lam', 'thanhlam33@gmail.com', '0456543334', '087678766567', 'NỮ', 2),
(10, 'Đinh Hoàng', 'hoang223@gmail.com', '0675645443', '056787544567', 'NAM', 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieudatphong`
--

CREATE TABLE `phieudatphong` (
  `ID_PHIEUDAT` int(11) NOT NULL,
  `NGAYDAT` date NOT NULL,
  `NGATRA` date NOT NULL,
  `ID_NV` int(11) DEFAULT NULL,
  `ID_KH` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phieudatphong`
--

INSERT INTO `phieudatphong` (`ID_PHIEUDAT`, `NGAYDAT`, `NGATRA`, `ID_NV`, `ID_KH`) VALUES
(1, '2024-05-06', '2024-05-08', 5, 1),
(2, '2024-05-15', '2024-05-16', 5, 1),
(3, '2024-05-06', '2024-05-09', 5, 1),
(4, '2024-05-06', '2024-05-07', 5, 1),
(5, '2024-05-06', '2024-05-23', 5, 1),
(6, '2024-05-06', '2024-05-09', 5, 1),
(7, '2024-05-06', '2024-05-10', 5, 1),
(8, '2024-05-06', '2024-05-09', 5, 9),
(9, '2024-05-16', '2024-05-18', 5, 5),
(10, '2024-05-11', '2024-05-13', 5, 1),
(11, '2024-05-07', '2024-05-09', 5, 6),
(12, '2024-05-08', '2024-05-12', 5, 1),
(13, '2024-05-07', '2024-05-10', 5, 3),
(14, '2024-05-06', '2024-05-07', 5, 1),
(15, '2024-05-06', '2024-05-07', 5, 1),
(16, '2024-05-06', '2024-05-07', 5, 1),
(17, '2024-05-06', '2024-05-07', 5, 1),
(18, '2024-05-06', '2024-05-07', 5, 6),
(19, '2024-05-06', '2024-05-07', 5, 10),
(20, '2024-05-06', '2024-05-07', 5, 26),
(21, '2024-05-07', '2024-05-09', 5, 25),
(22, '2024-05-07', '2024-05-08', 5, 24),
(23, '2024-05-17', '2024-05-18', 5, 23);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phong`
--

CREATE TABLE `phong` (
  `ID_PHG` int(11) NOT NULL,
  `TEN_PHG` varchar(100) NOT NULL,
  `ID_LOAIPHG` int(11) DEFAULT NULL,
  `TINHTRANG_PHG` enum('TRỐNG','ĐÃ ĐẶT') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phong`
--

INSERT INTO `phong` (`ID_PHG`, `TEN_PHG`, `ID_LOAIPHG`, `TINHTRANG_PHG`) VALUES
(1, 'A100', 1, 'ĐÃ ĐẶT'),
(3, 'A101', 1, 'TRỐNG'),
(4, 'C100', 3, 'TRỐNG'),
(5, 'B100', 2, 'TRỐNG'),
(6, 'C101', 3, 'TRỐNG'),
(7, 'A102', 1, 'TRỐNG'),
(8, 'A103', 1, 'TRỐNG'),
(9, 'A104', 1, 'TRỐNG'),
(10, 'A105', 1, 'TRỐNG'),
(11, 'A106', 1, 'ĐÃ ĐẶT'),
(12, 'A107', 1, 'TRỐNG'),
(13, 'A108', 1, 'TRỐNG'),
(14, 'A109', 1, 'ĐÃ ĐẶT'),
(15, 'A110', 1, 'TRỐNG'),
(16, 'B101', 2, 'ĐÃ ĐẶT'),
(17, 'B102', 2, 'ĐÃ ĐẶT'),
(18, 'B103', 2, 'ĐÃ ĐẶT'),
(19, 'B104', 2, 'ĐÃ ĐẶT'),
(20, 'B105', 2, 'ĐÃ ĐẶT'),
(22, 'B106', 2, 'TRỐNG'),
(23, 'B107', 2, 'TRỐNG'),
(24, 'B108', 2, 'TRỐNG'),
(25, 'B109', 2, 'TRỐNG'),
(26, 'B110', 2, 'TRỐNG'),
(27, 'C102', 3, 'TRỐNG'),
(28, 'C103', 3, 'TRỐNG'),
(29, 'C104', 3, 'TRỐNG'),
(30, 'C105', 3, 'TRỐNG'),
(31, 'C106', 3, 'TRỐNG'),
(32, 'C107', 3, 'TRỐNG'),
(33, 'C108', 3, 'TRỐNG'),
(34, 'C109', 3, 'TRỐNG'),
(35, 'C110', 3, 'TRỐNG'),
(36, 'D100', 4, 'TRỐNG'),
(37, 'D101', 4, 'TRỐNG'),
(38, 'D102', 4, 'TRỐNG'),
(39, 'D103', 1, 'TRỐNG'),
(40, 'D104', 4, 'TRỐNG'),
(41, 'D105', 4, 'TRỐNG'),
(42, 'D106', 4, 'ĐÃ ĐẶT'),
(43, 'D107', 4, 'TRỐNG'),
(44, 'D108', 4, 'TRỐNG'),
(45, 'D109', 4, 'TRỐNG'),
(46, 'D110', 4, 'TRỐNG');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `ID_TK` int(11) NOT NULL,
  `USERNAME` varchar(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  `ID_NV` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`ID_TK`, `USERNAME`, `password`, `ID_NV`) VALUES
(1, 'trong111', 'trong@111', 5),
(2, 'mai123', 'mai@123', 2),
(3, 'annhien111', '22222', 3),
(4, 'anvan111', 'anvan@123', 1),
(5, 'lam333', 'lam@333', 7),
(6, 'lam1334', 'lam@111', 8);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdichvu`
--
ALTER TABLE `chitietdichvu`
  ADD PRIMARY KEY (`ID_HD`,`ID_DV`),
  ADD KEY `FK_ID_DV` (`ID_DV`),
  ADD KEY `ID_HD` (`ID_HD`);

--
-- Chỉ mục cho bảng `chitietphieudat`
--
ALTER TABLE `chitietphieudat`
  ADD PRIMARY KEY (`ID_PHIEUDAT`,`ID_PHG`),
  ADD KEY `FK_ID_PHG` (`ID_PHG`),
  ADD KEY `ID_PHIEUDAT` (`ID_PHIEUDAT`);

--
-- Chỉ mục cho bảng `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`ID_CHUCVU`);

--
-- Chỉ mục cho bảng `dichvu`
--
ALTER TABLE `dichvu`
  ADD PRIMARY KEY (`ID_DV`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`ID_HD`),
  ADD KEY `FK_ID_KH1` (`ID_KH`),
  ADD KEY `FK_ID_PHIEUDAT` (`ID_PHIEUDAT`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`ID_KH`);

--
-- Chỉ mục cho bảng `loaiphong`
--
ALTER TABLE `loaiphong`
  ADD PRIMARY KEY (`ID_LOAIPHG`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`ID_NV`),
  ADD KEY `FK_ID_CHUCVU` (`ID_CHUCVU`);

--
-- Chỉ mục cho bảng `phieudatphong`
--
ALTER TABLE `phieudatphong`
  ADD PRIMARY KEY (`ID_PHIEUDAT`),
  ADD KEY `FK_ID_NV` (`ID_NV`),
  ADD KEY `FK_ID_KH` (`ID_KH`);

--
-- Chỉ mục cho bảng `phong`
--
ALTER TABLE `phong`
  ADD PRIMARY KEY (`ID_PHG`),
  ADD KEY `FK_ID_LOAIPHG` (`ID_LOAIPHG`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`ID_TK`),
  ADD KEY `FK_TK_NV` (`ID_NV`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chucvu`
--
ALTER TABLE `chucvu`
  MODIFY `ID_CHUCVU` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `dichvu`
--
ALTER TABLE `dichvu`
  MODIFY `ID_DV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `ID_HD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `ID_KH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT cho bảng `loaiphong`
--
ALTER TABLE `loaiphong`
  MODIFY `ID_LOAIPHG` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `ID_NV` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `phieudatphong`
--
ALTER TABLE `phieudatphong`
  MODIFY `ID_PHIEUDAT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT cho bảng `phong`
--
ALTER TABLE `phong`
  MODIFY `ID_PHG` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `ID_TK` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitietdichvu`
--
ALTER TABLE `chitietdichvu`
  ADD CONSTRAINT `FK_ID_DV` FOREIGN KEY (`ID_DV`) REFERENCES `dichvu` (`ID_DV`),
  ADD CONSTRAINT `FK_ID_HD` FOREIGN KEY (`ID_HD`) REFERENCES `hoadon` (`ID_HD`);

--
-- Các ràng buộc cho bảng `chitietphieudat`
--
ALTER TABLE `chitietphieudat`
  ADD CONSTRAINT `FK_ID_PHG` FOREIGN KEY (`ID_PHG`) REFERENCES `phong` (`ID_PHG`),
  ADD CONSTRAINT `FK_ID_PHIEUDAT1` FOREIGN KEY (`ID_PHIEUDAT`) REFERENCES `phieudatphong` (`ID_PHIEUDAT`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK_ID_KH1` FOREIGN KEY (`ID_KH`) REFERENCES `khachhang` (`ID_KH`),
  ADD CONSTRAINT `FK_ID_PHIEUDAT` FOREIGN KEY (`ID_PHIEUDAT`) REFERENCES `phieudatphong` (`ID_PHIEUDAT`);

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `FK_ID_CHUCVU` FOREIGN KEY (`ID_CHUCVU`) REFERENCES `chucvu` (`ID_CHUCVU`);

--
-- Các ràng buộc cho bảng `phieudatphong`
--
ALTER TABLE `phieudatphong`
  ADD CONSTRAINT `FK_ID_KH` FOREIGN KEY (`ID_KH`) REFERENCES `khachhang` (`ID_KH`),
  ADD CONSTRAINT `FK_ID_NV` FOREIGN KEY (`ID_NV`) REFERENCES `nhanvien` (`ID_NV`);

--
-- Các ràng buộc cho bảng `phong`
--
ALTER TABLE `phong`
  ADD CONSTRAINT `FK_ID_LOAIPHG` FOREIGN KEY (`ID_LOAIPHG`) REFERENCES `loaiphong` (`ID_LOAIPHG`);

--
-- Các ràng buộc cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_TK_NV` FOREIGN KEY (`ID_NV`) REFERENCES `nhanvien` (`ID_NV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
