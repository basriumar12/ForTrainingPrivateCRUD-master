-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2019 at 07:44 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.1.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `berita`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_berita`
--

CREATE TABLE `tbl_berita` (
  `id_berita` int(10) NOT NULL,
  `title` varchar(150) NOT NULL,
  `content` text NOT NULL,
  `foto` text NOT NULL,
  `id_user` int(3) NOT NULL,
  `publish_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_berita`
--

INSERT INTO `tbl_berita` (`id_berita`, `title`, `content`, `foto`, `id_user`, `publish_date`, `update_date`) VALUES
(11, 'Test', 'Test', 'IMG_20190404_105500.jpg', 3, '2019-04-25 16:47:49', '2019-04-25 16:47:49'),
(12, '121', '1212', 'Home page.png', 3, '2019-04-25 16:53:06', '2019-04-25 16:53:06'),
(13, 'Tst', 'Asda', 'IMG_20190404_105500.jpg', 3, '2019-04-25 16:56:51', '2019-04-25 16:56:51'),
(14, '1212', 'Asada', 'IMG_20190404_105500.jpg', 3, '2019-04-25 16:58:58', '2019-04-25 16:58:58'),
(15, 'Asa', 'Asas', 'IMG_20190404_105500.jpg', 3, '2019-04-25 17:04:41', '2019-04-25 17:04:41'),
(16, '121', '1212', 'Home page.png', 3, '2019-04-25 17:19:01', '2019-04-25 17:19:01'),
(17, 'Asd', 'Asasas', 'IMG_20190404_105500.jpg', 3, '2019-04-25 17:38:01', '2019-04-25 17:38:01'),
(18, 'Hanya', 'Hanya', 'IMG_20190404_105500.jpg', 3, '2019-04-25 17:38:19', '2019-04-25 17:38:19');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` int(3) NOT NULL,
  `fullname` varchar(150) NOT NULL,
  `username` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `access` varchar(100) NOT NULL DEFAULT 'reader',
  `insert_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_active_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `fullname`, `username`, `password`, `access`, `insert_date`, `update_date`, `last_active_date`) VALUES
(1, 'Firas Luthfi', 'vrras', '827ccb0eea8a706c4c34a16891f84e7b', 'admin', '2018-02-12 14:19:52', '2018-02-12 19:07:11', '2018-02-12 19:07:11'),
(2, 'Firas', 'reader', '827ccb0eea8a706c4c34a16891f84e7b', 'reader', '2018-02-12 19:21:42', '2018-02-12 19:21:42', '2018-02-12 19:21:42'),
(3, 'Aku', 'aku', 'a2849a25e3e700642b0dcca640a50be2', 'reader', '2019-04-25 16:35:58', '2019-04-25 16:35:58', '2019-04-25 16:35:58'),
(4, 'akucoba', 'coba', 'c3ec0f7b054e729c5a716c8125839829', 'reader', '2019-04-25 17:16:32', '2019-04-25 17:16:32', '2019-04-25 17:16:32'),
(5, '', '', 'd41d8cd98f00b204e9800998ecf8427e', 'reader', '2019-04-25 17:18:44', '2019-04-25 17:18:44', '2019-04-25 17:18:44');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_berita`
--
ALTER TABLE `tbl_berita`
  ADD PRIMARY KEY (`id_berita`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_berita`
--
ALTER TABLE `tbl_berita`
  MODIFY `id_berita` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id_user` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
