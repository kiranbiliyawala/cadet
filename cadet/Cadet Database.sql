-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 30, 2012 at 12:36 PM
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
  `Password` varchar(250) NOT NULL COMMENT 'Stores The Password of Admin',
  `Name` varchar(30) NOT NULL COMMENT 'Stores Name of Admin',
  `Contact` int(15) NOT NULL COMMENT 'Stores Contact of Admin',
  `Verified` varchar(1) NOT NULL COMMENT 'Stores that User is verified or not',
  PRIMARY KEY (`AUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Admin Table';

-- --------------------------------------------------------

--
-- Table structure for table `candidate`
--

CREATE TABLE IF NOT EXISTS `candidate` (
  `CUserName` varchar(25) NOT NULL COMMENT 'Stores Username of User',
  `Password` varchar(250) NOT NULL COMMENT 'Stores Password of User',
  `Name` varchar(30) NOT NULL COMMENT 'Stores Name of User',
  `Contact` int(15) NOT NULL COMMENT 'Stores Contact of User',
  `CandidateCategoryName` varchar(25) NOT NULL COMMENT 'Stores User_Category_Name',
  `Verified` varchar(1) NOT NULL COMMENT 'Stores whether user is Verified or not',
  PRIMARY KEY (`CUserName`),
  KEY `UserCategoryName` (`CandidateCategoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores Details of User';

-- --------------------------------------------------------

--
-- Table structure for table `candidatecategory`
--

CREATE TABLE IF NOT EXISTS `candidatecategory` (
  `CandidateCategoryName` varchar(25) NOT NULL COMMENT 'Stores User Category Name',
  PRIMARY KEY (`CandidateCategoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores User Category Details';

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `CategoryId` int(3) NOT NULL COMMENT 'Stores the Category ID',
  `CategoryName` varchar(15) NOT NULL COMMENT 'Stores Name of Category',
  `Description` varchar(50) NOT NULL COMMENT 'Stores Description of Category',
  `Ausername` varchar(25) NOT NULL COMMENT 'Stores the name of admin who added the Category',
  PRIMARY KEY (`CategoryId`),
  KEY `Ausername` (`Ausername`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores Details of Category';

-- --------------------------------------------------------

--
-- Table structure for table `levelmarks`
--

CREATE TABLE IF NOT EXISTS `levelmarks` (
  `LevelId` int(5) NOT NULL COMMENT 'Stores the level of the question',
  `TestId` int(5) NOT NULL COMMENT 'Stores ID of the Test',
  `Marks` int(5) NOT NULL COMMENT 'Stores the marks of a particular level',
  PRIMARY KEY (`LevelId`,`TestId`),
  KEY `Testid` (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores what level has how many marks';

-- --------------------------------------------------------

--
-- Table structure for table `questionbank`
--

CREATE TABLE IF NOT EXISTS `questionbank` (
  `QuestionId` int(6) NOT NULL AUTO_INCREMENT COMMENT 'Stores Question''s ID',
  `CategoryId` int(3) NOT NULL COMMENT 'Stores the Id of Category',
  `LevelId` int(5) NOT NULL COMMENT 'Stores the Level Id',
  `Question` text NOT NULL COMMENT 'Stores Question',
  `OptA` text NOT NULL COMMENT 'Stores Option A of the Question',
  `OptB` text NOT NULL COMMENT 'Stores Option B of the Question',
  `OptC` text NOT NULL COMMENT 'Stores Option C of the Question',
  `OptD` text NOT NULL COMMENT 'Stores Option D of the Question',
  `CorrectAnswer` text NOT NULL COMMENT 'Stores Correct Option of the Question',
  PRIMARY KEY (`QuestionId`),
  KEY `CategoryId` (`CategoryId`,`LevelId`),
  KEY `LevelId` (`LevelId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the details of the all the Question' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE IF NOT EXISTS `result` (
  `CUserName` varchar(25) NOT NULL COMMENT 'Stores the Name of the user who appeared for the Test',
  `TestId` int(5) NOT NULL COMMENT 'Stores the Test ID for which the user appeared for the Test',
  `Marks` int(5) NOT NULL COMMENT 'Stores Marks of the User',
  `Attempted` int(5) NOT NULL COMMENT 'Stores the number of attempted question',
  `Correct` int(5) NOT NULL COMMENT 'Stores number of correct question',
  PRIMARY KEY (`CUserName`,`TestId`),
  KEY `Testid` (`TestId`),
  KEY `SUsername` (`CUserName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the Result of the User';

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `TestId` int(5) NOT NULL AUTO_INCREMENT COMMENT 'Stores the test ID',
  `TestType` varchar(15) NOT NULL COMMENT 'Stores the type of the Test',
  `TestName` varchar(30) NOT NULL COMMENT 'Stores the Name of the Test',
  `TestDesc` varchar(255) NOT NULL COMMENT 'Stores the Description of the test',
  `TestDate` date NOT NULL COMMENT 'Stores the date of the test',
  `StartTime` datetime NOT NULL COMMENT 'Stores start time of the test',
  `EndTime` datetime NOT NULL COMMENT 'Stores end time of the test',
  `TestDuration` int(5) NOT NULL COMMENT 'Stores the duration of the test',
  `InitialDifficulty` int(5) NOT NULL COMMENT 'Stores the initial difficulty of the Test',
  PRIMARY KEY (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the Details of the Test' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `testcategory`
--

CREATE TABLE IF NOT EXISTS `testcategory` (
  `TestId` int(5) NOT NULL,
  `CategoryId` int(5) NOT NULL,
  `TimePerCategory` int(3) NOT NULL COMMENT 'Stores the time per category in MINUTES',
  `QuestionPerCategory` int(3) NOT NULL COMMENT 'Stores the number of Question per Category',
  PRIMARY KEY (`TestId`,`CategoryId`),
  KEY `Categoryname` (`CategoryId`),
  KEY `TestID` (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `testquestion`
--

CREATE TABLE IF NOT EXISTS `testquestion` (
  `TestId` int(5) NOT NULL COMMENT 'Stores the testid',
  `QuesitonId` int(5) NOT NULL COMMENT 'Stores question id',
  `NegMark` int(5) NOT NULL COMMENT 'Stores Negative Mark For the Questions in  Test',
  PRIMARY KEY (`TestId`,`QuesitonId`),
  KEY `Quesitonid` (`QuesitonId`),
  KEY `Testid` (`TestId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the list of question (used only if non adaptive)';

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
  ADD CONSTRAINT `questionbank_ibfk_2` FOREIGN KEY (`LevelId`) REFERENCES `levelmarks` (`LevelId`),
  ADD CONSTRAINT `questionbank_ibfk_1` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`);

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_2` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`),
  ADD CONSTRAINT `result_ibfk_1` FOREIGN KEY (`CUserName`) REFERENCES `candidate` (`CUserName`);

--
-- Constraints for table `testcategory`
--
ALTER TABLE `testcategory`
  ADD CONSTRAINT `testcategory_ibfk_2` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`),
  ADD CONSTRAINT `testcategory_ibfk_1` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`);

--
-- Constraints for table `testquestion`
--
ALTER TABLE `testquestion`
  ADD CONSTRAINT `testquestion_ibfk_2` FOREIGN KEY (`QuesitonId`) REFERENCES `questionbank` (`QuestionId`),
  ADD CONSTRAINT `testquestion_ibfk_1` FOREIGN KEY (`TestId`) REFERENCES `test` (`TestId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
