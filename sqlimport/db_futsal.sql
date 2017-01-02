-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 01, 2017 at 03:32 AM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_futsal`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE IF NOT EXISTS `booking` (
  `id` int(11) NOT NULL,
  `uniqueID` varchar(50) NOT NULL,
  `bookingDate` datetime NOT NULL,
  `scheduleDate` date NOT NULL,
  `memberNo` varchar(50) DEFAULT NULL,
  `amountBefore` varchar(20) NOT NULL,
  `amountAfter` varchar(20) NOT NULL,
  `receipt` varchar(50) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0 = new || 1=approved || 2=cancel',
  `couponCode` varchar(10) NOT NULL,
  `reason` varchar(50) DEFAULT NULL COMMENT 'reason for booking cancellation',
  `dateUpdate` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `uniqueID`, `bookingDate`, `scheduleDate`, `memberNo`, `amountBefore`, `amountAfter`, `receipt`, `status`, `couponCode`, `reason`, `dateUpdate`) VALUES
(26, 'ksc12112216143', '2016-12-30 22:11:46', '2016-06-15', NULL, '240', '240', 'ksc12112216143.ppt', 0, 'NOCOUPON', NULL, '2016-12-30 22:11:46'),
(27, 'ksc12122216153', '2016-12-30 22:12:24', '2016-11-16', NULL, '120', '120', 'ksc12122216153.ppt', 0, 'NOCOUPON', NULL, '2016-12-30 22:12:24'),
(28, 'ksc12132216126', '2016-12-30 22:13:10', '2016-12-29', NULL, '560', '504', 'ksc12132216126.ppt', 0, 'NEWYEAR10', NULL, '2016-12-30 22:13:10');

-- --------------------------------------------------------

--
-- Table structure for table `coupon`
--

CREATE TABLE IF NOT EXISTS `coupon` (
  `id` int(11) NOT NULL,
  `couponcode` varchar(10) NOT NULL,
  `discountrate` int(3) NOT NULL,
  `dateCreated` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coupon`
--

INSERT INTO `coupon` (`id`, `couponcode`, `discountrate`, `dateCreated`) VALUES
(1, 'NEWYEAR10', 10, '2016-12-21 00:00:00'),
(2, 'ABE5', 5, '2016-12-22 00:00:00'),
(3, '123', 2, '2016-12-30 17:03:36');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `uniqueID` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nric` varchar(50) NOT NULL,
  `telno` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`uniqueID`, `name`, `nric`, `telno`, `email`) VALUES
('ksc12112216143', 'Farizal Bin Mat Top', '12345', '013245678', 'asakura@ip.com'),
('ksc12122216153', 'Farizal Bin Mat Top', '12345', '013245678', 'asakura@ip.com'),
('ksc12132216126', 'Mat Ali', '432141', '01201821', 'matali@mat');

-- --------------------------------------------------------

--
-- Table structure for table `holiday`
--

CREATE TABLE IF NOT EXISTS `holiday` (
  `id` int(11) NOT NULL,
  `holdate` date NOT NULL,
  `holidayname` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE IF NOT EXISTS `member` (
  `id` int(50) NOT NULL,
  `memberNo` varchar(15) NOT NULL,
  `name` varchar(50) NOT NULL,
  `NRIC` varchar(50) NOT NULL,
  `dateRegistered` datetime NOT NULL,
  `birthDate` date NOT NULL,
  `address` varchar(100) NOT NULL,
  `telNo` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `dateUpdate` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `memberNo`, `name`, `NRIC`, `dateRegistered`, `birthDate`, `address`, `telNo`, `email`, `dateUpdate`) VALUES
(1, 'A00001', 'Farizal Bin Mat Top', '12345', '2016-12-08 00:00:00', '1998-12-01', 'Skudai', '013245678', 'asakura@ip.com', '2016-12-30 22:26:59'),
(2, 'A01952', 'Abdul Muis', '98120791872', '2016-12-30 16:37:47', '1995-10-24', 'Skudai', '01212312141', 'asds@ashals.co', '2016-12-30 16:37:47');

-- --------------------------------------------------------

--
-- Table structure for table `reservedcourt`
--

CREATE TABLE IF NOT EXISTS `reservedcourt` (
  `id` int(11) NOT NULL,
  `code` int(11) NOT NULL,
  `date` date NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0=locked for approval || 1=booked || 2=maintenance || 3=cancel',
  `uniqueID` varchar(50) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservedcourt`
--

INSERT INTO `reservedcourt` (`id`, `code`, `date`, `status`, `uniqueID`) VALUES
(55, 12, '2016-06-15', 0, 'ksc12112216143'),
(56, 13, '2016-06-15', 0, 'ksc12112216143'),
(57, 22, '2016-06-15', 0, 'ksc12112216143'),
(58, 23, '2016-06-15', 0, 'ksc12112216143'),
(59, 11, '2016-11-16', 0, 'ksc12122216153'),
(60, 21, '2016-11-16', 0, 'ksc12122216153'),
(61, 13, '2016-12-29', 0, 'ksc12132216126'),
(62, 14, '2016-12-29', 0, 'ksc12132216126'),
(63, 15, '2016-12-29', 0, 'ksc12132216126'),
(64, 16, '2016-12-29', 0, 'ksc12132216126'),
(65, 23, '2016-12-29', 0, 'ksc12132216126'),
(66, 24, '2016-12-29', 0, 'ksc12132216126'),
(67, 25, '2016-12-29', 0, 'ksc12132216126'),
(68, 26, '2016-12-29', 0, 'ksc12132216126');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(15) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fullname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `role` int(11) NOT NULL COMMENT '1=staf || 2=admin',
  `dateregistered` datetime NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `fullname`, `name`, `role`, `dateregistered`) VALUES
(1, 'admin', 'admin', 'Rizalman Bin Sudirman', 'Rizalman', 2, '2016-12-21 00:00:00'),
(2, 'kenji', 'kenji', 'Tamao Kenji', 'Kenji', 1, '2016-12-22 00:00:00'),
(3, 'serizawa', 'serizawa', 'Tamao Serizawa', 'Serizawa', 1, '2016-12-30 20:57:32');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `coupon`
--
ALTER TABLE `coupon`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`uniqueID`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reservedcourt`
--
ALTER TABLE `reservedcourt`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT for table `coupon`
--
ALTER TABLE `coupon`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `reservedcourt`
--
ALTER TABLE `reservedcourt`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=69;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(15) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
