-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 25, 2012 at 07:11 AM
-- Server version: 5.5.20
-- PHP Version: 5.3.10

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
  `Username` varchar(25) NOT NULL COMMENT 'Stores Username Of Admin',
  `Password` varchar(250) NOT NULL COMMENT 'Stores The Password of Admin',
  `Name` varchar(30) NOT NULL COMMENT 'Stores Name of Admin',
  `Contact` int(15) NOT NULL COMMENT 'Stores Contact of Admin',
  `Verified` varchar(1) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Admin Table';

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `Categoryname` varchar(15) NOT NULL COMMENT 'Stores Name of Category',
  `Description` varchar(50) NOT NULL COMMENT 'Stores Description of Category',
  `Ausername` varchar(25) NOT NULL COMMENT 'Stores the name of admin who added the Category',
  PRIMARY KEY (`Categoryname`),
  KEY `Ausername` (`Ausername`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores Details of Category';

-- --------------------------------------------------------

--
-- Table structure for table `level_marks`
--

CREATE TABLE IF NOT EXISTS `level_marks` (
  `Lid` int(5) NOT NULL COMMENT 'Stores the level of the question',
  `Testid` int(5) NOT NULL COMMENT 'Stores ID of the Test',
  `Marks` int(5) NOT NULL COMMENT 'Stores the marks of a particular level',
  PRIMARY KEY (`Lid`,`Testid`),
  KEY `Testid` (`Testid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores what level has how many marks';

-- --------------------------------------------------------

--
-- Table structure for table `question_bank`
--

CREATE TABLE IF NOT EXISTS `question_bank` (
  `QID` int(5) NOT NULL AUTO_INCREMENT COMMENT 'Stores Question''s ID',
  `CName` varchar(15) NOT NULL COMMENT 'Stores Category of the Question',
  `Question` text NOT NULL COMMENT 'Stores Question',
  `Option A` text NOT NULL COMMENT 'Stores Option A of the Question',
  `Option B` text NOT NULL COMMENT 'Stores Option B of the Question',
  `Option C` text NOT NULL COMMENT 'Stores Option C of the Question',
  `Option D` text NOT NULL COMMENT 'Stores Option D of the Question',
  `Correct Option` text NOT NULL COMMENT 'Stores Correct Option of the Question',
  `Lid` int(5) NOT NULL COMMENT 'Stores Level of the Question',
  PRIMARY KEY (`QID`),
  KEY `CName` (`CName`),
  KEY `Lid` (`Lid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the details of the all the Question' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `result`
--

CREATE TABLE IF NOT EXISTS `result` (
  `SUsername` varchar(25) NOT NULL COMMENT 'Stores the Name of the user who appeared for the Test',
  `Testid` int(5) NOT NULL COMMENT 'Stores the Test ID for which the user appeared for the Test',
  `Marks` int(5) NOT NULL COMMENT 'Stores Marks of the User',
  `Attempted` int(5) NOT NULL COMMENT 'Stores the number of attempted question',
  `Correct` int(5) NOT NULL COMMENT 'Stores number of correct question',
  PRIMARY KEY (`SUsername`,`Testid`),
  KEY `Testid` (`Testid`),
  KEY `SUsername` (`SUsername`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the Result of the User';

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE IF NOT EXISTS `test` (
  `Testid` int(5) NOT NULL AUTO_INCREMENT COMMENT 'Stores the test ID',
  `Type` varchar(15) NOT NULL COMMENT 'Stores the type of the Test',
  `Testname` varchar(30) NOT NULL,
  `Tdate` date NOT NULL COMMENT 'Stores the date of the test',
  `StartTime` datetime NOT NULL COMMENT 'Stores start time of the test',
  `EndTime` datetime NOT NULL COMMENT 'Stores end time of the test',
  `Tduration` int(5) NOT NULL COMMENT 'Stores the duration of the test',
  `Initialdifficulty` int(5) NOT NULL COMMENT 'Stores the initial difficulty of the Test',
  `Testdesc` varchar(250) NOT NULL COMMENT 'Stores Description of the test',
  PRIMARY KEY (`Testid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the Details of the Test' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `test_category`
--

CREATE TABLE IF NOT EXISTS `test_category` (
  `TestID` int(5) NOT NULL,
  `Categoryname` varchar(15) NOT NULL,
  `Per_Category_Time` int(3) NOT NULL,
  `Question_Per_Category` int(3) NOT NULL,
  PRIMARY KEY (`TestID`,`Categoryname`),
  KEY `Categoryname` (`Categoryname`),
  KEY `TestID` (`TestID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `test_question`
--

CREATE TABLE IF NOT EXISTS `test_question` (
  `Testid` int(5) NOT NULL COMMENT 'Stores the testid',
  `Quesitonid` int(5) NOT NULL COMMENT 'Stores question id',
  `Negativemark` int(5) NOT NULL COMMENT 'Stores Negative Mark For the Questions in  Test',
  PRIMARY KEY (`Testid`,`Quesitonid`),
  KEY `Quesitonid` (`Quesitonid`),
  KEY `Testid` (`Testid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the list of question (used only if non adaptive)';

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `Username` varchar(25) NOT NULL COMMENT 'Stores Username of User',
  `Password` varchar(250) NOT NULL COMMENT 'Stores Password of User',
  `Name` varchar(30) NOT NULL COMMENT 'Stores Name of User',
  `Contact` int(15) NOT NULL COMMENT 'Stores Contact of User',
  `UserCategoryName` varchar(25) NOT NULL COMMENT 'Stores User_Category_Name',
  `Verified` varchar(1) NOT NULL,
  PRIMARY KEY (`Username`),
  KEY `UserCategoryName` (`UserCategoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores Details of User';

-- --------------------------------------------------------

--
-- Table structure for table `user_category`
--

CREATE TABLE IF NOT EXISTS `user_category` (
  `UserCategoryName` varchar(25) NOT NULL COMMENT 'Stores User Category Name',
  PRIMARY KEY (`UserCategoryName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores User Category Details';

--
-- Constraints for dumped tables
--

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `category_ibfk_1` FOREIGN KEY (`Ausername`) REFERENCES `admin` (`Username`);

--
-- Constraints for table `level_marks`
--
ALTER TABLE `level_marks`
  ADD CONSTRAINT `level_marks_ibfk_1` FOREIGN KEY (`Testid`) REFERENCES `test` (`Testid`);

--
-- Constraints for table `question_bank`
--
ALTER TABLE `question_bank`
  ADD CONSTRAINT `question_bank_ibfk_1` FOREIGN KEY (`CName`) REFERENCES `category` (`Categoryname`),
  ADD CONSTRAINT `question_bank_ibfk_2` FOREIGN KEY (`Lid`) REFERENCES `level_marks` (`Lid`);

--
-- Constraints for table `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_1` FOREIGN KEY (`SUsername`) REFERENCES `user` (`Username`),
  ADD CONSTRAINT `result_ibfk_2` FOREIGN KEY (`Testid`) REFERENCES `test` (`Testid`);

--
-- Constraints for table `test_category`
--
ALTER TABLE `test_category`
  ADD CONSTRAINT `test_category_ibfk_1` FOREIGN KEY (`TestID`) REFERENCES `test` (`Testid`),
  ADD CONSTRAINT `test_category_ibfk_2` FOREIGN KEY (`Categoryname`) REFERENCES `category` (`Categoryname`);

--
-- Constraints for table `test_question`
--
ALTER TABLE `test_question`
  ADD CONSTRAINT `test_question_ibfk_1` FOREIGN KEY (`Testid`) REFERENCES `test` (`Testid`),
  ADD CONSTRAINT `test_question_ibfk_2` FOREIGN KEY (`Quesitonid`) REFERENCES `question_bank` (`QID`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`UserCategoryName`) REFERENCES `user_category` (`UserCategoryName`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
