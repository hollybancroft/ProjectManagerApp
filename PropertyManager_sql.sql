-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 31, 2021 at 08:57 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `property_manager`
--
DROP DATABASE IF EXISTS `property_manager`;
CREATE DATABASE IF NOT EXISTS `property_manager` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `property_manager`;

-- --------------------------------------------------------

--
-- Table structure for table `maintenance`
--

DROP TABLE IF EXISTS `maintenance`;
CREATE TABLE `maintenance` (
  `maintenance_id` int(11) NOT NULL,
  `maintenance_performed` varchar(60) DEFAULT NULL,
  `maintenance_date` date DEFAULT NULL,
  `maintenance_cost` decimal(11,2) DEFAULT NULL,
  `vendor_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `maintenance`
--

INSERT INTO `maintenance` (`maintenance_id`, `maintenance_performed`, `maintenance_date`, `maintenance_cost`, `vendor_id`) VALUES
(1, 'none', '2021-07-01', '0.00', 0),
(2, 'Service A/C', '2021-07-14', '250.00', 2),
(3, 'Paint exterior', '2021-07-03', '3000.00', 3);

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
CREATE TABLE `property` (
  `propertyid` int(11) NOT NULL,
  `address` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `zip` int(11) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `mortgage_amount` double(11,2) DEFAULT NULL,
  `mortgage_due` date DEFAULT NULL,
  `unit_type` varchar(30) DEFAULT NULL,
  `maintenance_id` int(11) DEFAULT NULL,
  `occupied` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `property`
--

INSERT INTO `property` (`propertyid`, `address`, `city`, `state`, `zip`, `purchase_date`, `mortgage_amount`, `mortgage_due`, `unit_type`, `maintenance_id`, `occupied`) VALUES
(1, '123 Main Street', 'Atlanta', 'GA', 12345, '2021-02-05', 180000.00, '2021-09-05', 'Single Family', 3, 1),
(2, '543 Oak Avenue', 'Sanford', 'FL', 32771, '2019-07-18', 80000.00, '2021-09-18', 'Duplex', 2, 0),
(3, '678 Sunset Lane', 'Orlando', 'FL', 12345, '2016-05-06', 130000.00, '2021-09-06', 'Single Family', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tenant`
--

DROP TABLE IF EXISTS `tenant`;
CREATE TABLE `tenant` (
  `tenant_id` int(11) NOT NULL,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `propertyid` int(11) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `rent_due` date DEFAULT NULL,
  `rent_amount` double(11,2) DEFAULT NULL,
  `missing_rent` tinyint(1) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tenant`
--

INSERT INTO `tenant` (`tenant_id`, `first_name`, `last_name`, `propertyid`, `phone`, `rent_due`, `rent_amount`, `missing_rent`, `active`) VALUES
(1, 'John', 'Doe', 1, '999-999-9999', '2021-06-01', 1800.00, 1, 1),
(2, 'Jane', 'Smith', NULL, '987-543-2211', '2021-08-01', 1050.00, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
CREATE TABLE `vendor` (
  `vendor_id` int(11) NOT NULL,
  `vendor_name` varchar(60) DEFAULT NULL,
  `vendor_type` varchar(60) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vendor`
--

INSERT INTO `vendor` (`vendor_id`, `vendor_name`, `vendor_type`, `phone`) VALUES
(0, NULL, NULL, NULL),
(2, 'Bryan\'s Cooling and Heating', 'HVAC', '987-654-3210'),
(3, 'Painter\'s Plus', 'Painter', '111-222-3333'),
(4, 'Smith and Son\'s Roofing', 'Roofing ', '987-654-3211');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `maintenance`
--
ALTER TABLE `maintenance`
  ADD PRIMARY KEY (`maintenance_id`),
  ADD KEY `vendor_id` (`vendor_id`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`propertyid`),
  ADD KEY `maintenance_id` (`maintenance_id`);

--
-- Indexes for table `tenant`
--
ALTER TABLE `tenant`
  ADD PRIMARY KEY (`tenant_id`),
  ADD KEY `propertyid` (`propertyid`);

--
-- Indexes for table `vendor`
--
ALTER TABLE `vendor`
  ADD PRIMARY KEY (`vendor_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `maintenance`
--
ALTER TABLE `maintenance`
  MODIFY `maintenance_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `property`
--
ALTER TABLE `property`
  MODIFY `propertyid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tenant`
--
ALTER TABLE `tenant`
  MODIFY `tenant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `vendor`
--
ALTER TABLE `vendor`
  MODIFY `vendor_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `maintenance`
--
ALTER TABLE `maintenance`
  ADD CONSTRAINT `maintenance_ibfk_1` FOREIGN KEY (`vendor_id`) REFERENCES `vendor` (`vendor_id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `property`
--
ALTER TABLE `property`
  ADD CONSTRAINT `property_ibfk_1` FOREIGN KEY (`maintenance_id`) REFERENCES `maintenance` (`maintenance_id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `tenant`
--
ALTER TABLE `tenant`
  ADD CONSTRAINT `tenant_ibfk_1` FOREIGN KEY (`propertyid`) REFERENCES `property` (`propertyid`) ON DELETE SET NULL ON UPDATE SET NULL;

GRANT ALL PRIVILEGES ON *.* TO `root`@`localhost` WITH GRANT OPTION;

GRANT ALL PRIVILEGES ON `property_manager`.* TO `root`@`localhost` WITH GRANT OPTION;

GRANT PROXY ON ''@'%' TO 'root'@'localhost' WITH GRANT OPTION;

COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
