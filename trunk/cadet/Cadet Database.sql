-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 31, 2012 at 07:46 PM
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
  `AUserName` varchar(25) NOT NULL COMMENT 'Stores Username Of Admin',
  `Password` varchar(250) DEFAULT NULL COMMENT 'Stores The Password of Admin',
  `Name` varchar(30) DEFAULT NULL COMMENT 'Stores Name of Admin',
  `Contact` int(15) DEFAULT NULL COMMENT 'Stores Contact of Admin',
  `Verified` varchar(1) DEFAULT NULL COMMENT 'Stores that User is verified or not',
  PRIMARY KEY (`AUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Admin Table';

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AUserName`, `Password`, `Name`, `Contact`, `Verified`) VALUES
('admin', 'admin', 'Admin', 123456789, 'Y'),
('admin1', 'admin1', 'Admin1', 87654321, 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `candidate`
--

CREATE TABLE IF NOT EXISTS `candidate` (
  `CUserName` varchar(25) NOT NULL COMMENT 'Stores Username of User',
  `Password` varchar(250) DEFAULT NULL COMMENT 'Stores Password of User',
  `Name` varchar(30) DEFAULT NULL COMMENT 'Stores Name of User',
  `Contact` int(15) DEFAULT NULL COMMENT 'Stores Contact of User',
  `CandidateCategoryName` varchar(25) DEFAULT NULL COMMENT 'Stores User_Category_Name',
  `Verified` varchar(1) DEFAULT NULL COMMENT 'Stores whether user is Verified or not',
  PRIMARY KEY (`CUserName`),
  KEY `UserCategoryName` (`CandidateCategoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores Details of User';

--
-- Dumping data for table `candidate`
--

INSERT INTO `candidate` (`CUserName`, `Password`, `Name`, `Contact`, `CandidateCategoryName`, `Verified`) VALUES
('user1', 'user1', 'User1', 1, 'BTECH', 'Y'),
('user10', 'user10', 'User10', 10, 'MDES', 'N'),
('user2', 'user2', 'User2', 2, 'BTECH', 'Y'),
('user3', 'user3', 'User3', 3, 'MDES', 'Y'),
('user4', 'user4', 'User4', 4, 'MSCITARD', 'Y'),
('user5', 'user5', 'User5', 5, 'MTECH', 'Y'),
('user6', 'user6', 'User6', 6, 'MSCIT', 'Y'),
('user7', 'user7', 'User7', 7, 'MSCIT', 'Y'),
('user8', 'user8', 'User8', 8, 'PHD', 'Y'),
('user9', 'user9', 'User9', 9, 'PHD', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `candidatecategory`
--

CREATE TABLE IF NOT EXISTS `candidatecategory` (
  `CandidateCategoryName` varchar(25) NOT NULL COMMENT 'Stores User Category Name',
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
  `CategoryId` int(3) NOT NULL COMMENT 'Stores the Category ID',
  `CategoryName` varchar(15) DEFAULT NULL COMMENT 'Stores Name of Category',
  `Description` varchar(50) DEFAULT NULL COMMENT 'Stores Description of Category',
  `Ausername` varchar(25) DEFAULT NULL COMMENT 'Stores the name of admin who added the Category',
  PRIMARY KEY (`CategoryId`),
  KEY `Ausername` (`Ausername`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores Details of Category';

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CategoryId`, `CategoryName`, `Description`, `Ausername`) VALUES
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
  `Marks` int(5) DEFAULT NULL COMMENT 'Stores the marks of a particular level',
  PRIMARY KEY (`LevelId`,`TestId`),
  KEY `Testid` (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores what level has how many marks';

--
-- Dumping data for table `levelmarks`
--

INSERT INTO `levelmarks` (`LevelId`, `TestId`, `Marks`) VALUES
(1, 1, 2),
(1, 2, 2),
(2, 1, 2),
(2, 2, 4),
(3, 1, 3),
(3, 2, 6);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Stores the details of the all the Question' AUTO_INCREMENT=3 ;

--
-- Dumping data for table `questionbank`
--

INSERT INTO `questionbank` (`QuestionId`, `CategoryId`, `LevelId`, `Question`, `OptA`, `OptB`, `OptC`, `OptD`, `CorrectAnswer`) VALUES
(1, 1, 1, 'Question 1', 'option a', 'option b', 'option c', 'option d', 'option a'),
(2, 2, 2, 'Question 2', 'option a', 'option b', 'option c', 'option d', 'option b');

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE IF NOT EXISTS `result` (
  `CUserName` varchar(25) NOT NULL COMMENT 'Stores the Name of the user who appeared for the Test',
  `TestId` int(5) NOT NULL COMMENT 'Stores the Test ID for which the user appeared for the Test',
  `Marks` int(5) DEFAULT NULL COMMENT 'Stores Marks of the User',
  `Attempted` int(5) DEFAULT NULL COMMENT 'Stores the number of attempted question',
  `Correct` int(5) DEFAULT NULL COMMENT 'Stores number of correct question',
  PRIMARY KEY (`CUserName`,`TestId`),
  KEY `Testid` (`TestId`),
  KEY `SUsername` (`CUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the Result of the User';

--
-- Dumping data for table `result`
--

INSERT INTO `result` (`CUserName`, `TestId`, `Marks`, `Attempted`, `Correct`) VALUES
('user1', 3, 20, 5, 2),
('user2', 3, 9, 2, 6),
('user4', 3, 90, 4, 4);

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `TestId` int(5) NOT NULL AUTO_INCREMENT COMMENT 'Stores the test ID',
  `TestType` varchar(15) DEFAULT NULL COMMENT 'Stores the type of the Test',
  `TestName` varchar(30) DEFAULT NULL COMMENT 'Stores the Name of the Test',
  `TestDesc` text COMMENT 'Stores the Description of the test',
  `TestDate` date DEFAULT NULL COMMENT 'Stores the date of the test',
  `StartTime` datetime DEFAULT NULL COMMENT 'Stores start time of the test',
  `EndTime` datetime DEFAULT NULL COMMENT 'Stores end time of the test',
  `TestDuration` int(5) DEFAULT NULL COMMENT 'Stores the duration of the test',
  `InitialDifficulty` int(5) DEFAULT NULL COMMENT 'Stores the initial difficulty of the Test',
  PRIMARY KEY (`TestId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COMMENT='Stores the Details of the Test' AUTO_INCREMENT=4 ;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`TestId`, `TestType`, `TestName`, `TestDesc`, `TestDate`, `StartTime`, `EndTime`, `TestDuration`, `InitialDifficulty`) VALUES
(1, 'Adaptive', 'ABC Company Test', 'This test is for ABC Company', '2013-01-10', '2013-01-10 05:00:00', '2013-01-10 23:00:00', 2, 3),
(2, 'Non-Adaptive', 'Infosys', 'This Test is for Infosys.', '2012-11-11', '2012-11-11 03:05:06', '2012-11-11 20:52:53', 3, 2),
(3, 'Adaptive', 'XYZ', 'This Test is for XYZ Company', '2012-10-02', '2012-10-02 02:08:08', '2012-10-02 20:52:51', 1, 2);

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
  `QuesitonId` int(5) NOT NULL COMMENT 'Stores question id',
  `NegMark` int(5) DEFAULT NULL COMMENT 'Stores Negative Mark For the Questions in  Test',
  PRIMARY KEY (`TestId`,`QuesitonId`),
  KEY `Quesitonid` (`QuesitonId`),
  KEY `Testid` (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the list of question (used only if non adaptive)';

--
-- Dumping data for table `testquestion`
--

INSERT INTO `testquestion` (`TestId`, `QuesitonId`, `NegMark`) VALUES
(1, 1, 2),
(1, 2, 1);

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
  ADD CONSTRAINT `levelmarks_ibfk_1` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`);

--
-- Constraints for table `questionbank`
--
ALTER TABLE `questionbank`
  ADD CONSTRAINT `questionbank_ibfk_1` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`),
  ADD CONSTRAINT `questionbank_ibfk_2` FOREIGN KEY (`LevelId`) REFERENCES `levelmarks` (`LevelId`);

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_1` FOREIGN KEY (`CUserName`) REFERENCES `candidate` (`CUserName`),
  ADD CONSTRAINT `result_ibfk_2` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`);

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
  ADD CONSTRAINT `testquestion_ibfk_1` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`),
  ADD CONSTRAINT `testquestion_ibfk_2` FOREIGN KEY (`QuesitonId`) REFERENCES `questionbank` (`QuestionId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
