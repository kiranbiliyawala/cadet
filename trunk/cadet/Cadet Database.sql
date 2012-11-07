-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 07, 2012 at 12:43 PM
-- Server version: 5.1.53-community-log
-- PHP Version: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cadet`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `AUserName` varchar(100) NOT NULL COMMENT 'Stores Username Of Admin',
  `Password` varchar(250) DEFAULT NULL COMMENT 'Stores The Password of Admin',
  `Name` varchar(100) DEFAULT NULL COMMENT 'Stores Name of Admin',
  `Contact` varchar(100) DEFAULT NULL COMMENT 'Stores Contact of Admin',
  `Verified` varchar(1) DEFAULT NULL COMMENT 'Stores that User is verified or not',
  PRIMARY KEY (`AUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Admin Table';

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AUserName`, `Password`, `Name`, `Contact`, `Verified`) VALUES
('admin', 'admin', 'Admin', '123456789', 'Y'),
('admin1', 'admin1', 'Admin1', '87654321', 'Y'),
('admin@cadet.com', 'C68216EFBFBDEFBFBDZ7DsEFBFBD0AEFBFBDEFBFBDoEFBFBDC999EFBFBD1311EFBFBDdEFBFBDEFBFBD7DxEFBFBDfEFBFBDEFBFBDEFBFBD15EFBFBD13OEFBFBD1426EFBFBDEFBFBDhEFBFBDEFBFBD015DEFBFBDj087EWEFBFBDEEFBFBDL0DEFBFBDEFBFBD1EFBFBDr7DEFBFBDEFBFBDEFBFBDEFBFBD', 'Admin of Cadet', '0', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `candidate`
--

CREATE TABLE IF NOT EXISTS `candidate` (
  `CUserName` varchar(25) NOT NULL COMMENT 'Stores Username of User',
  `Password` varchar(250) DEFAULT NULL COMMENT 'Stores Password of User',
  `Name` varchar(100) DEFAULT NULL COMMENT 'Stores Name of User',
  `Contact` varchar(100) DEFAULT NULL COMMENT 'Stores Contact of User',
  `CandidateCategoryName` varchar(100) DEFAULT NULL COMMENT 'Stores User_Category_Name',
  `Verified` varchar(1) DEFAULT NULL COMMENT 'Stores whether user is Verified or not',
  PRIMARY KEY (`CUserName`),
  KEY `UserCategoryName` (`CandidateCategoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores Details of User';

--
-- Dumping data for table `candidate`
--

INSERT INTO `candidate` (`CUserName`, `Password`, `Name`, `Contact`, `CandidateCategoryName`, `Verified`) VALUES
('cadet.daiict@gmail.com', 'C68216EFBFBDEFBFBDZ7DsEFBFBD0AEFBFBDEFBFBDoEFBFBDC999EFBFBD1311EFBFBDdEFBFBDEFBFBD7DxEFBFBDfEFBFBDEFBFBDEFBFBD15EFBFBD13OEFBFBD1426EFBFBDEFBFBDhEFBFBDEFBFBD015DEFBFBDj087EWEFBFBDEEFBFBDL0DEFBFBDEFBFBD1EFBFBDr7DEFBFBDEFBFBDEFBFBDEFBFBD', 'cadet', '1234567890', 'BTECH', 'Y'),
('da201112014@gmail.com', 'G47DuEFBFBD5B2218EFBFBDEFBFBDEFBFBDEFBFBDEFBFBDEFBFBD082323EFBFBDaEFBFBDEFBFBDEFBFBDIZYEFBFBD3CDFB9EFBFBDEFBFBD0026EFBFBD1BEFBFBD_EFBFBDEFBFBDeEFBFBDEFBFBDYAEFBFBDEFBFBD09EFBFBDjEFBFBDiEFBFBDjEFBFBDEFBFBDEFBFBDEFBFBDEFBFBD5D0Bu0BEFBFBD', 'killer', '777777777777', 'BTECH', 'N'),
('user1', 'user1', 'User1', '1', 'BTECH', 'Y'),
('user10', 'user10', 'User10', '10', 'MDES', 'N'),
('user2', 'user2', 'User2', '2', 'BTECH', 'Y'),
('user3', 'user3', 'User3', '3', 'MDES', 'Y'),
('user4', 'user4', 'User4', '4', 'MSCITARD', 'Y'),
('user5', 'user5', 'User5', '5', 'MTECH', 'Y'),
('user6', 'user6', 'User6', '6', 'MSCIT', 'Y'),
('user7', 'user7', 'User7', '7', 'MSCIT', 'Y'),
('user8', 'user8', 'User8', '8', 'PHD', 'Y'),
('user9', 'user9', 'User9', '9', 'PHD', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `candidatecategory`
--

CREATE TABLE IF NOT EXISTS `candidatecategory` (
  `CandidateCategoryName` varchar(100) NOT NULL COMMENT 'Stores User Category Name',
  PRIMARY KEY (`CandidateCategoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores User Category Details';

--
-- Dumping data for table `candidatecategory`
--

INSERT INTO `candidatecategory` (`CandidateCategoryName`) VALUES
('BTECH'),
('MDES'),
('MSCIT'),
('MSCITARD'),
('MTECH'),
('PHD');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `CategoryId` int(3) NOT NULL AUTO_INCREMENT COMMENT 'Stores the Category ID',
  `CategoryName` varchar(100) DEFAULT NULL COMMENT 'Stores Name of Category',
  `CategoryDescription` varchar(100) DEFAULT NULL COMMENT 'Stores Description of Category',
  `Ausername` varchar(100) DEFAULT NULL COMMENT 'Stores the name of admin who added the Category',
  PRIMARY KEY (`CategoryId`),
  KEY `Ausername` (`Ausername`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Stores Details of Category' AUTO_INCREMENT=6 ;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CategoryId`, `CategoryName`, `CategoryDescription`, `Ausername`) VALUES
(1, 'Quantitative', 'Contains the Quantitative Questions', 'admin'),
(2, 'Varbal', 'Contains all the Verbal Ability Questions', 'admin'),
(3, 'Logical Reasoni', 'Contains all the Logical Reasoning Questions ', 'admin1'),
(4, 'Comprehension', 'Contains all the Comprehensive Questions', 'admin'),
(5, 'Aptitude', 'Contains all the Aptitude Questions', 'admin1');

-- --------------------------------------------------------

--
-- Table structure for table `levelmarks`
--

CREATE TABLE IF NOT EXISTS `levelmarks` (
  `LevelId` int(5) NOT NULL COMMENT 'Stores the level of the question',
  `TestId` int(5) NOT NULL COMMENT 'Stores ID of the Test',
  `Marks` int(100) DEFAULT NULL COMMENT 'Stores the marks of a particular level',
  PRIMARY KEY (`LevelId`,`TestId`),
  KEY `Testid` (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores what level has how many marks';

--
-- Dumping data for table `levelmarks`
--

INSERT INTO `levelmarks` (`LevelId`, `TestId`, `Marks`) VALUES
(1, 1, 2),
(1, 2, 2),
(1, 5, 1),
(2, 1, 2),
(2, 2, 4),
(2, 5, 2),
(3, 1, 3),
(3, 2, 6),
(3, 5, 3),
(4, 5, 4),
(5, 5, 5),
(6, 5, 6),
(7, 5, 7),
(8, 5, 8),
(9, 5, 9),
(10, 5, 10);

-- --------------------------------------------------------

--
-- Table structure for table `questionbank`
--

CREATE TABLE IF NOT EXISTS `questionbank` (
  `QuestionId` int(6) NOT NULL AUTO_INCREMENT COMMENT 'Stores Question''s ID',
  `CategoryId` int(3) DEFAULT NULL COMMENT 'Stores the Id of Category',
  `LevelId` int(5) DEFAULT NULL COMMENT 'Stores the Level Id',
  `Question` text COMMENT 'Stores Question',
  `OptA` varchar(255) DEFAULT NULL COMMENT 'Stores Option A of the Question',
  `OptB` varchar(255) DEFAULT NULL COMMENT 'Stores Option B of the Question',
  `OptC` varchar(255) DEFAULT NULL COMMENT 'Stores Option C of the Question',
  `OptD` varchar(255) DEFAULT NULL COMMENT 'Stores Option D of the Question',
  `CorrectAnswer` varchar(255) DEFAULT NULL COMMENT 'Stores Correct Option of the Question',
  PRIMARY KEY (`QuestionId`),
  KEY `CategoryId` (`CategoryId`,`LevelId`),
  KEY `LevelId` (`LevelId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Stores the details of the all the Question' AUTO_INCREMENT=4 ;

--
-- Dumping data for table `questionbank`
--

INSERT INTO `questionbank` (`QuestionId`, `CategoryId`, `LevelId`, `Question`, `OptA`, `OptB`, `OptC`, `OptD`, `CorrectAnswer`) VALUES
(1, 1, 1, 'Question 1', 'option a', 'option b', 'option c', 'option d', 'A'),
(2, 2, 2, 'Question 2', 'option a', 'option b', 'option c', 'option d', 'B');

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE IF NOT EXISTS `result` (
  `CUserName` varchar(25) NOT NULL COMMENT 'Stores the Name of the user who appeared for the Test',
  `TestId` int(5) NOT NULL COMMENT 'Stores the Test ID for which the user appeared for the Test',
  `Marks` decimal(30,25) NOT NULL COMMENT 'Stores the marks of the Candidate',
  `Attempted` int(20) DEFAULT NULL COMMENT 'Stores the number of attempted question',
  `Correct` int(20) DEFAULT NULL COMMENT 'Stores number of correct question',
  PRIMARY KEY (`CUserName`,`TestId`),
  KEY `Testid` (`TestId`),
  KEY `SUsername` (`CUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the Result of the User';

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`CUserName`, `TestId`, `Marks`, `Attempted`, `Correct`) VALUES
('cadet.daiict@gmail.com', 1, '66079.8973844201500000000000000', 6, 0),
('user1', 3, '20.0000000000000000000000000', 5, 2),
('user2', 3, '9.0000000000000000000000000', 2, 6),
('user4', 3, '90.0000000000000000000000000', 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `TestId` int(5) NOT NULL AUTO_INCREMENT COMMENT 'Stores the test ID',
  `TestType` varchar(100) DEFAULT NULL COMMENT 'Stores the type of the Test',
  `TestName` varchar(100) DEFAULT NULL COMMENT 'Stores the Name of the Test',
  `TestDesc` text COMMENT 'Stores the Description of the test',
  `TestDate` date DEFAULT NULL COMMENT 'Stores the date of the test',
  `StartTime` datetime DEFAULT NULL COMMENT 'Stores start time of the test',
  `EndTime` datetime DEFAULT NULL COMMENT 'Stores end time of the test',
  `TestDuration` int(5) DEFAULT NULL COMMENT 'Stores the duration of the test',
  `InitialDifficulty` int(5) DEFAULT NULL COMMENT 'Stores the initial difficulty of the Test',
  `NegMark` int(100) DEFAULT NULL COMMENT 'Stores Negative Marks',
  PRIMARY KEY (`TestId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Stores the Details of the Test' AUTO_INCREMENT=6 ;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`TestId`, `TestType`, `TestName`, `TestDesc`, `TestDate`, `StartTime`, `EndTime`, `TestDuration`, `InitialDifficulty`, `NegMark`) VALUES
(1, 'Adaptive', 'ABC Company Test', 'This test is for ABC Company', '2012-11-07', '2012-11-07 01:00:00', '2012-11-07 23:00:00', 2, 3, 0),
(2, 'NonAdaptive', 'Infosys', 'This Test is for Infosys.', '2012-11-11', '2012-11-11 03:05:06', '2012-11-11 20:52:53', 3, 2, NULL),
(3, 'Adaptive', 'XYZ', 'This Test is for XYZ Company', '2012-10-02', '2012-10-02 02:08:08', '2012-10-02 20:52:51', 1, 2, NULL),
(5, 'Adaptive', 'Trial', 'Trial Test<br>', '2012-11-07', '2012-11-07 01:00:00', '2012-11-07 23:00:00', 120, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `testcandidatecategory`
--

CREATE TABLE IF NOT EXISTS `testcandidatecategory` (
  `TestId` int(5) NOT NULL COMMENT 'Stores ID of the Test',
  `CandidateCategoryName` varchar(100) NOT NULL COMMENT 'Stores Candidate Category Name',
  PRIMARY KEY (`TestId`,`CandidateCategoryName`),
  KEY `CandidateCategoryName` (`CandidateCategoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores Which candidate category can apply for which test';

--
-- Dumping data for table `testcandidatecategory`
--

INSERT INTO `testcandidatecategory` (`TestId`, `CandidateCategoryName`) VALUES
(1, 'BTECH'),
(5, 'BTECH'),
(1, 'MDES'),
(3, 'MSCIT'),
(2, 'PHD');

-- --------------------------------------------------------

--
-- Table structure for table `testcategory`
--

CREATE TABLE IF NOT EXISTS `testcategory` (
  `TestId` int(5) NOT NULL,
  `CategoryId` int(5) NOT NULL,
  `TimePerCategory` int(3) DEFAULT NULL COMMENT 'Stores the time per category in MINUTES',
  `QuestionPerCategory` int(3) DEFAULT NULL COMMENT 'Stores the number of Question per Category',
  PRIMARY KEY (`TestId`,`CategoryId`),
  KEY `Categoryname` (`CategoryId`),
  KEY `TestID` (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `testcategory`
--

INSERT INTO `testcategory` (`TestId`, `CategoryId`, `TimePerCategory`, `QuestionPerCategory`) VALUES
(1, 1, 15, 3),
(1, 2, 20, 3),
(3, 1, 5, 5),
(3, 3, 10, 10);

-- --------------------------------------------------------

--
-- Table structure for table `testquestion`
--

CREATE TABLE IF NOT EXISTS `testquestion` (
  `TestId` int(5) NOT NULL COMMENT 'Stores the testid',
  `QuestionId` int(5) NOT NULL COMMENT 'Stores question id',
  PRIMARY KEY (`TestId`,`QuestionId`),
  KEY `Quesitonid` (`QuestionId`),
  KEY `Testid` (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the list of question (used only if non adaptive)';

--
-- Dumping data for table `testquestion`
--

INSERT INTO `testquestion` (`TestId`, `QuestionId`) VALUES
(1, 1),
(1, 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `candidate`
--
ALTER TABLE `candidate`
  ADD CONSTRAINT `candidate_ibfk_1` FOREIGN KEY (`CandidateCategoryName`) REFERENCES `candidatecategory` (`CandidateCategoryName`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`Ausername`) REFERENCES `admin` (`AUserName`);

--
-- Constraints for table `levelmarks`
--
ALTER TABLE `levelmarks`
  ADD CONSTRAINT `levelmarks_ibfk_1` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `questionbank`
--
ALTER TABLE `questionbank`
  ADD CONSTRAINT `questionbank_ibfk_1` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`);

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_1` FOREIGN KEY (`CUserName`) REFERENCES `candidate` (`CUserName`),
  ADD CONSTRAINT `result_ibfk_2` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`);

--
-- Constraints for table `testcandidatecategory`
--
ALTER TABLE `testcandidatecategory`
  ADD CONSTRAINT `testcandidatecategory_ibfk_1` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`),
  ADD CONSTRAINT `testcandidatecategory_ibfk_2` FOREIGN KEY (`CandidateCategoryName`) REFERENCES `candidatecategory` (`CandidateCategoryName`);

--
-- Constraints for table `testcategory`
--
ALTER TABLE `testcategory`
  ADD CONSTRAINT `testcategory_ibfk_1` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`),
  ADD CONSTRAINT `testcategory_ibfk_2` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`);

--
-- Constraints for table `testquestion`
--
ALTER TABLE `testquestion`
  ADD CONSTRAINT `testquestion_ibfk_2` FOREIGN KEY (`QuestionId`) REFERENCES `questionbank` (`QuestionId`),
  ADD CONSTRAINT `testquestion_ibfk_1` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
