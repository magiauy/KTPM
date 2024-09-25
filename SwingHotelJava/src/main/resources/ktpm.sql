-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for ktpm
CREATE DATABASE IF NOT EXISTS `swinghotel` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci */;
USE `swinghotel`;

-- Dumping structure for table ktpm.chitietdichvu
CREATE TABLE IF NOT EXISTS `chitietdichvu` (
  `ID_PHIEUDAT` int(11) NOT NULL,
  `ID_DV` int(11) NOT NULL,
  `SOLUONG_DV` int(11) NOT NULL,
  `TONGTIEN_DV` double NOT NULL,
  PRIMARY KEY (`ID_PHIEUDAT`,`ID_DV`) USING BTREE,
  KEY `FK_ID_DV` (`ID_DV`),
  KEY `ID_HD` (`ID_PHIEUDAT`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.chitietdichvu: ~31 rows (approximately)
INSERT IGNORE INTO `chitietdichvu` (`ID_PHIEUDAT`, `ID_DV`, `SOLUONG_DV`, `TONGTIEN_DV`) VALUES
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
	(14, 1, 5, 10),
	(14, 2, 5, 15),
	(15, 2, 20, 60),
	(16, 1, 1, 2),
	(16, 2, 2, 6),
	(19, 2, 20, 60);

-- Dumping structure for table ktpm.chitietphieudat
CREATE TABLE IF NOT EXISTS `chitietphieudat` (
  `ID_PHIEUDAT` int(11) NOT NULL,
  `ID_PHG` int(11) NOT NULL,
  `TONGTIEN_CTPHIEUDAT` double NOT NULL,
  `TRANGTHAI` enum('Chưa checkout','Đã checkout') NOT NULL,
  `TEN_PHG` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_PHIEUDAT`,`ID_PHG`),
  KEY `FK_ID_PHG` (`ID_PHG`),
  KEY `ID_PHIEUDAT` (`ID_PHIEUDAT`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.chitietphieudat: ~24 rows (approximately)
INSERT IGNORE INTO `chitietphieudat` (`ID_PHIEUDAT`, `ID_PHG`, `TONGTIEN_CTPHIEUDAT`, `TRANGTHAI`, `TEN_PHG`) VALUES
	(1, 29, 28, 'Đã checkout', 'C104'),
	(2, 37, 18, 'Đã checkout', 'D101'),
	(3, 42, 54, 'Đã checkout', 'D106'),
	(4, 44, 18, 'Đã checkout', 'D108'),
	(5, 6, 238, 'Đã checkout', 'C101'),
	(6, 32, 42, 'Đã checkout', 'C107'),
	(7, 35, 56, 'Đã checkout', 'C110'),
	(8, 24, 30, 'Đã checkout', 'B108'),
	(9, 20, 20, 'Đã checkout', 'B105'),
	(10, 40, 36, 'Đã checkout', 'D104'),
	(11, 27, 28, 'Đã checkout', 'C102'),
	(12, 4, 56, 'Đã checkout', 'C100'),
	(13, 19, 30, 'Đã checkout', 'B104'),
	(14, 11, 8, 'Chưa checkout', 'A106'),
	(15, 14, 8, 'Đã checkout', 'A109'),
	(16, 1, 8, 'Chưa checkout', 'A100'),
	(17, 19, 10, 'Đã checkout', 'B104'),
	(18, 18, 10, 'Chưa checkout', 'B103'),
	(19, 23, 10, 'Đã checkout', 'B107'),
	(20, 16, 10, 'Chưa checkout', 'B101'),
	(21, 17, 20, 'Chưa checkout', 'B102'),
	(22, 39, 8, 'Đã checkout', 'D103'),
	(23, 23, 10, 'Đã checkout', 'B107'),
	(24, 9, 40, 'Đã checkout', 'A104');

-- Dumping structure for table ktpm.chucvu
CREATE TABLE IF NOT EXISTS `chucvu` (
  `ID_CHUCVU` int(11) NOT NULL AUTO_INCREMENT,
  `TEN_CHUCVU` enum('QUẢN LÝ','NHÂN VIÊN') DEFAULT NULL,
  PRIMARY KEY (`ID_CHUCVU`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.chucvu: ~2 rows (approximately)
INSERT IGNORE INTO `chucvu` (`ID_CHUCVU`, `TEN_CHUCVU`) VALUES
	(1, 'QUẢN LÝ'),
	(2, 'NHÂN VIÊN');

-- Dumping structure for table ktpm.dichvu
CREATE TABLE IF NOT EXISTS `dichvu` (
  `ID_DV` int(11) NOT NULL AUTO_INCREMENT,
  `TEN_DV` varchar(100) NOT NULL,
  `DONGIA_DV` double NOT NULL,
  `MOTA_DV` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID_DV`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.dichvu: ~3 rows (approximately)
INSERT IGNORE INTO `dichvu` (`ID_DV`, `TEN_DV`, `DONGIA_DV`, `MOTA_DV`) VALUES
	(1, 'Ăn sáng', 2, 'các món: bánh mì, nước cam'),
	(2, 'Massage', 3, 'massage vai gáy 30 phút'),
	(3, 'Giặt ủi', 2.5, 'giặt ủi quần áo bẩn');

-- Dumping structure for table ktpm.hoadon
CREATE TABLE IF NOT EXISTS `hoadon` (
  `ID_HD` int(11) NOT NULL AUTO_INCREMENT,
  `TONGTIEN_HD` double NOT NULL,
  `NGAYLAP_HD` date NOT NULL,
  `ID_PHIEUDAT` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_HD`),
  KEY `FK_ID_PHIEUDAT` (`ID_PHIEUDAT`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.hoadon: ~19 rows (approximately)
INSERT IGNORE INTO `hoadon` (`ID_HD`, `TONGTIEN_HD`, `NGAYLAP_HD`, `ID_PHIEUDAT`) VALUES
	(1, 32, '2024-05-06', 1),
	(2, 23, '2024-05-06', 4),
	(3, 37.5, '2024-05-06', 8),
	(4, 64, '2024-05-06', 12),
	(5, 39, '2024-05-06', 13),
	(6, 70, '2024-05-06', 7),
	(7, 241, '2024-05-06', 5),
	(8, 23, '2024-05-06', 2),
	(9, 43.5, '2024-05-06', 10),
	(10, 52, '2024-05-06', 6),
	(11, 30.5, '2024-05-06', 11),
	(12, 17.5, '2024-05-06', 19),
	(13, 15, '2024-05-06', 23),
	(14, 13, '2024-05-06', 22),
	(15, 40, '2024-09-13', 24),
	(16, 54, '2024-09-13', 3),
	(17, 20, '2024-09-13', 9),
	(18, 10, '2024-09-13', 17),
	(19, 68, '2024-09-13', 15);

-- Dumping structure for table ktpm.khachhang
CREATE TABLE IF NOT EXISTS `khachhang` (
  `ID_KH` int(11) NOT NULL AUTO_INCREMENT,
  `TEN_KH` varchar(100) NOT NULL,
  `PHAI_KH` enum('NAM','NỮ') NOT NULL,
  `SDT_KH` varchar(10) NOT NULL,
  `EMAIL_KH` varchar(200) NOT NULL,
  `CCCD_KH` varchar(12) NOT NULL,
  `TRANGTHAI_KH` enum('HOẠT ĐỘNG','NGỪNG HOẠT ĐỘNG') NOT NULL DEFAULT 'HOẠT ĐỘNG',
  PRIMARY KEY (`ID_KH`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.khachhang: ~15 rows (approximately)
INSERT IGNORE INTO `khachhang` (`ID_KH`, `TEN_KH`, `PHAI_KH`, `SDT_KH`, `EMAIL_KH`, `CCCD_KH`, `TRANGTHAI_KH`) VALUES
	(1, 'Nguyễn Thế Anh', 'NAM', '0456787665', 'theanh124@gmail.com', '056789766789', 'NGỪNG HOẠT ĐỘNG'),
	(2, 'Lê Ngọc Bình An', 'NỮ', '0987656789', 'ngocbinh456@gmail.com', '056785435678', 'HOẠT ĐỘNG'),
	(3, 'Nguyễn Quốc Việt', 'NAM', '0567898776', 'quocviet123@gmail.com', '056789877654', 'HOẠT ĐỘNG'),
	(4, 'Bình Anh Quân', 'NAM', '0878987667', 'anhquan23@gmail.com', '067890988765', 'HOẠT ĐỘNG'),
	(5, 'Nguyễn Phúc Quân', 'NAM', '0678754559', 'phucquan123@gmail.com', '056345777898', 'HOẠT ĐỘNG'),
	(6, 'Đỗ Bảo Châu', 'NỮ', '0678765667', 'chaubao12@gmail.com', '089786544567', 'NGỪNG HOẠT ĐỘNG'),
	(7, 'Hoàng Minh Tùng', 'NAM', '0567654556', 'bon123@gmail.com', '056787655456', 'HOẠT ĐỘNG'),
	(8, 'Lý Hoàng Ánh Tuyết', 'NỮ', '0656765445', 'tuyethoang22@gmail.com', '05678988658', 'NGỪNG HOẠT ĐỘNG'),
	(9, 'Phạm Thị Thuy Thủy', 'NỮ', '0656765444', 'thuythuy33@gmail.com', '067876544565', 'HOẠT ĐỘNG'),
	(10, 'Nguyễn Đức Hòa', 'NAM', '0656765448', 'henxui123@gmail.com', '076765678765', 'HOẠT ĐỘNG'),
	(11, 'Lê Thanh Uyên', 'NỮ', '0656765422', 'boru13@gmail.com', '078765666787', 'HOẠT ĐỘNG'),
	(12, 'Huỳnh Trần Nhật Hạ', 'NỮ', '0456576554', 'sunny22@gmail.com', '067876544111', 'NGỪNG HOẠT ĐỘNG'),
	(13, 'Đinh Hoàng Cát Tiên', 'NỮ', '0567876554', 'tien33@gmail.com', '056787655432', 'HOẠT ĐỘNG'),
	(14, 'Nguyễn Nhật Minh', 'NAM', '0567876554', 'minh3334@gmail.com', '067898766543', 'HOẠT ĐỘNG'),
	(15, 'Lê Đức Trí', 'NAM', '0567876554', 'trijames22@gmail.com', '056787655457', 'NGỪNG HOẠT ĐỘNG');

-- Dumping structure for table ktpm.loaiphong
CREATE TABLE IF NOT EXISTS `loaiphong` (
  `ID_LOAIPHG` int(11) NOT NULL AUTO_INCREMENT,
  `TEN_LOAIPHG` varchar(100) NOT NULL,
  `DONGIA_PHG` double NOT NULL,
  `MOTA_PHG` varchar(300) NOT NULL,
  `TRANGTHAI_LOAIPHG` enum('Hoạt động','Không hoạt động') NOT NULL,
  PRIMARY KEY (`ID_LOAIPHG`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.loaiphong: ~4 rows (approximately)
INSERT IGNORE INTO `loaiphong` (`ID_LOAIPHG`, `TEN_LOAIPHG`, `DONGIA_PHG`, `MOTA_PHG`, `TRANGTHAI_LOAIPHG`) VALUES
	(1, 'Phòng đơn bình dân', 8, 'giường đơn , có tivi, nhà vệ sinh', 'Hoạt động'),
	(2, 'Phòng đôi bình dân', 10, 'giường đôi, có tivi, nhà vệ sinh', 'Hoạt động'),
	(3, 'Phòng đơn VIP', 14, 'giường đơn, tivi lớn, có bồn tắm, view biển', 'Hoạt động'),
	(4, 'Phòng đôi VIP', 18, 'giường đôi, tivi lớn,  có bồn tắm, bếp ăn, view biển', 'Hoạt động');

-- Dumping structure for table ktpm.nhanvien
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `ID_NV` int(11) NOT NULL AUTO_INCREMENT,
  `TEN_NV` varchar(100) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `SDT_NV` varchar(10) NOT NULL,
  `CCCD_NV` varchar(12) NOT NULL,
  `PHAI_NV` enum('NAM','NỮ') NOT NULL,
  `ID_CHUCVU` int(11) DEFAULT NULL,
  `TRANGTHAI_NV` enum('HOẠT ĐỘNG','NGỪNG HOẠT ĐỘNG') NOT NULL DEFAULT 'HOẠT ĐỘNG',
  PRIMARY KEY (`ID_NV`),
  KEY `FK_ID_CHUCVU` (`ID_CHUCVU`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.nhanvien: ~12 rows (approximately)
INSERT IGNORE INTO `nhanvien` (`ID_NV`, `TEN_NV`, `EMAIL`, `SDT_NV`, `CCCD_NV`, `PHAI_NV`, `ID_CHUCVU`, `TRANGTHAI_NV`) VALUES
	(1, 'Nguyễn Văn An', 'annguyen123@gmail.com', '0356787997', '056754344567', 'NAM', 1, 'NGỪNG HOẠT ĐỘNG'),
	(2, 'Nguyễn Hoa Cúc Anh', 'maihoa123@gmail.com', '0765647800', '056785433567', 'NỮ', 1, 'HOẠT ĐỘNG'),
	(3, 'Nguyễn An Nhiên', 'annhien123@gmail.com', '0876789887', '065688766567', 'NỮ', 2, 'HOẠT ĐỘNG'),
	(4, 'Hà Quốc Việt', 'quocviet123@gmail.com', '0898989776', '076789888765', 'NAM', 2, 'HOẠT ĐỘNG'),
	(5, 'Ngô Đức Trọng', 'trongngo02@gmail.com', '0567898776', '056787666453', 'NAM', 1, 'HOẠT ĐỘNG'),
	(6, 'Nguyễn Văn A', 'vana234@gmail.com', '0567876554', '067876566787', 'NAM', 2, 'HOẠT ĐỘNG'),
	(7, 'Nguyễn Lâm', 'lammnguyen34@gmail.com', '0678765667', '067876544566', 'NAM', 2, 'HOẠT ĐỘNG'),
	(8, 'Nguyễn Duy Lâm', 'lam12@gmail.com', '0545676554', '067564566545', 'NAM', 2, 'HOẠT ĐỘNG'),
	(9, 'Nguyễn Thanh Lam', 'thanhlam33@gmail.com', '0456543334', '087678766567', 'NỮ', 2, 'HOẠT ĐỘNG'),
	(10, 'Đinh Hoàng', 'hoang223@gmail.com', '0675645443', '056787544567', 'NAM', 2, 'HOẠT ĐỘNG'),
	(11, 'Mã Gia Uy', 'magiauy46@gmail.com', '0339702531', '111111111111', 'NAM', 1, 'HOẠT ĐỘNG'),
	(12, 'Võ Thị Yến Thuỳ', 'yuuchan@gmail.com', '0399920196', '111111111112', 'NỮ', 2, 'NGỪNG HOẠT ĐỘNG');

-- Dumping structure for table ktpm.phieudatphong
CREATE TABLE IF NOT EXISTS `phieudatphong` (
  `ID_PHIEUDAT` int(11) NOT NULL,
  `NGAYDAT` date NOT NULL,
  `NGATRA` date NOT NULL,
  `ID_NV` int(11) DEFAULT NULL,
  `ID_KH` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_PHIEUDAT`),
  KEY `FK_ID_NV` (`ID_NV`),
  KEY `FK_ID_KH` (`ID_KH`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.phieudatphong: ~24 rows (approximately)
INSERT IGNORE INTO `phieudatphong` (`ID_PHIEUDAT`, `NGAYDAT`, `NGATRA`, `ID_NV`, `ID_KH`) VALUES
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
	(23, '2024-05-17', '2024-05-18', 5, 23),
	(24, '2024-09-13', '2024-09-18', 11, 5);

-- Dumping structure for table ktpm.phong
CREATE TABLE IF NOT EXISTS `phong` (
  `ID_PHG` int(11) NOT NULL AUTO_INCREMENT,
  `TEN_PHG` varchar(100) NOT NULL,
  `ID_LOAIPHG` int(11) DEFAULT NULL,
  `TINHTRANG_PHG` enum('TRỐNG','ĐÃ ĐẶT','BẢO TRÌ','NGỪNG KINH DOANH') DEFAULT NULL,
  PRIMARY KEY (`ID_PHG`),
  KEY `FK_ID_LOAIPHG` (`ID_LOAIPHG`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.phong: ~44 rows (approximately)
INSERT IGNORE INTO `phong` (`ID_PHG`, `TEN_PHG`, `ID_LOAIPHG`, `TINHTRANG_PHG`) VALUES
	(1, 'A100', 1, 'ĐÃ ĐẶT'),
	(2, 'A101', 1, 'TRỐNG'),
	(3, 'C100', 3, 'TRỐNG'),
	(4, 'B100', 2, 'TRỐNG'),
	(5, 'C101', 3, 'TRỐNG'),
	(6, 'A102', 1, 'TRỐNG'),
	(7, 'A103', 1, 'TRỐNG'),
	(8, 'A104', 1, 'TRỐNG'),
	(9, 'A105', 1, 'NGỪNG KINH DOANH'),
	(10, 'A106', 1, 'ĐÃ ĐẶT'),
	(11, 'A107', 1, 'TRỐNG'),
	(12, 'A108', 1, 'TRỐNG'),
	(13, 'A109', 1, 'TRỐNG'),
	(14, 'A110', 1, 'BẢO TRÌ'),
	(15, 'B101', 2, 'ĐÃ ĐẶT'),
	(16, 'B102', 2, 'ĐÃ ĐẶT'),
	(17, 'B103', 2, 'ĐÃ ĐẶT'),
	(18, 'B104', 2, 'TRỐNG'),
	(19, 'B105', 2, 'TRỐNG'),
	(20, 'B106', 2, 'TRỐNG'),
	(21, 'B107', 2, 'TRỐNG'),
	(22, 'B108', 2, 'TRỐNG'),
	(23, 'B109', 2, 'TRỐNG'),
	(24, 'B110', 2, 'TRỐNG'),
	(25, 'C102', 3, 'TRỐNG'),
	(26, 'C103', 3, 'TRỐNG'),
	(27, 'C111', 3, 'TRỐNG'),
	(28, 'C105', 3, 'TRỐNG'),
	(29, 'C106', 3, 'TRỐNG'),
	(30, 'C107', 3, 'TRỐNG'),
	(31, 'C108', 3, 'TRỐNG'),
	(32, 'C109', 3, 'TRỐNG'),
	(33, 'C110', 3, 'TRỐNG'),
	(34, 'D100', 4, 'TRỐNG'),
	(35, 'D101', 4, 'TRỐNG'),
	(36, 'D102', 4, 'TRỐNG'),
	(37, 'D103', 1, 'TRỐNG'),
	(38, 'D104', 4, 'TRỐNG'),
	(39, 'D105', 4, 'TRỐNG'),
	(40, 'D106', 4, 'TRỐNG'),
	(41, 'D107', 4, 'NGỪNG KINH DOANH'),
	(42, 'D108', 4, 'TRỐNG'),
	(43, 'D109', 4, 'TRỐNG'),
	(44, 'D110', 4, 'TRỐNG');

-- Dumping structure for table ktpm.taikhoan
CREATE TABLE IF NOT EXISTS `taikhoan` (
  `ID_TK` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  `ID_NV` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_TK`),
  KEY `FK_TK_NV` (`ID_NV`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table ktpm.taikhoan: ~7 rows (approximately)
INSERT IGNORE INTO `taikhoan` (`ID_TK`, `USERNAME`, `password`, `ID_NV`) VALUES
	(1, 'trong111', 'trong@111', 5),
	(2, 'mai123', 'mai@123', 2),
	(3, 'annhien111', '22222', 3),
	(4, 'anvan111', 'anvan@123', 1),
	(5, 'lam333', 'lam@333', 7),
	(7, 'admin', '1', 11),
	(8, 'nv', '1', 12);
-- Check and drop existing foreign key constraints if they exist
ALTER TABLE `chitietdichvu`
  DROP FOREIGN KEY IF EXISTS `FK_ID_DV`,
  DROP FOREIGN KEY IF EXISTS `FK_ID_PD`;

ALTER TABLE `chitietphieudat`
  DROP FOREIGN KEY IF EXISTS `FK_ID_PHG`,
  DROP FOREIGN KEY IF EXISTS `FK_ID_PD`;

ALTER TABLE `hoadon`
  DROP FOREIGN KEY IF EXISTS `FK_ID_PD`;

ALTER TABLE `nhanvien`
  DROP FOREIGN KEY IF EXISTS `FK_ID_CHUCVU`;

ALTER TABLE `phieudatphong`
  DROP FOREIGN KEY IF EXISTS `FK_ID_KH`,
  DROP FOREIGN KEY IF EXISTS `FK_ID_NV`;

ALTER TABLE `phong`
  DROP FOREIGN KEY IF EXISTS `FK_ID_LOAIPHG`;

ALTER TABLE `taikhoan`
  DROP FOREIGN KEY IF EXISTS `FK_TK_NV`;

-- Add new foreign key constraints with unique names
ALTER TABLE `chitietdichvu`
  ADD CONSTRAINT `FK_ID_DV_NEW` FOREIGN KEY (`ID_DV`) REFERENCES `dichvu` (`ID_DV`),
  ADD CONSTRAINT `FK_ID_PD_NEW` FOREIGN KEY (`ID_PHIEUDAT`) REFERENCES `phieudatphong` (`ID_PHIEUDAT`);

ALTER TABLE `chitietphieudat`
  ADD CONSTRAINT `FK_ID_PHG_NEW` FOREIGN KEY (`ID_PHG`) REFERENCES `phong` (`ID_PHG`),
  ADD CONSTRAINT `FK_ID_PD_NEW2` FOREIGN KEY (`ID_PHIEUDAT`) REFERENCES `phieudatphong` (`ID_PHIEUDAT`);

ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK_ID_PD_NEW3` FOREIGN KEY (`ID_PHIEUDAT`) REFERENCES `phieudatphong` (`ID_PHIEUDAT`);

ALTER TABLE `nhanvien`
  ADD CONSTRAINT `FK_ID_CHUCVU_NEW` FOREIGN KEY (`ID_CHUCVU`) REFERENCES `chucvu` (`ID_CHUCVU`);

ALTER TABLE `phieudatphong`
  ADD CONSTRAINT `FK_ID_KH_NEW` FOREIGN KEY (`ID_KH`) REFERENCES `khachhang` (`ID_KH`),
  ADD CONSTRAINT `FK_ID_NV_NEW` FOREIGN KEY (`ID_NV`) REFERENCES `nhanvien` (`ID_NV`);

ALTER TABLE `phong`
  ADD CONSTRAINT `FK_ID_LOAIPHG_NEW` FOREIGN KEY (`ID_LOAIPHG`) REFERENCES `loaiphong` (`ID_LOAIPHG`);

ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_TK_NV_NEW` FOREIGN KEY (`ID_NV`) REFERENCES `nhanvien` (`ID_NV`);






/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
