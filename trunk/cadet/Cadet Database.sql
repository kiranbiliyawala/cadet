-- phpMyAdmin SQL Dump
-- version 3.4.10.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 15, 2012 at 06:33 AM
-- Server version: 5.5.20
-- PHP Version: 5.3.10

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cat`
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
  PRIMARY KEY (`Categoryname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores Details of Category';

-- --------------------------------------------------------

--
-- Table structure for table `level`
--

CREATE TABLE IF NOT EXISTS `level` (
  `Lid` int(5) NOT NULL AUTO_INCREMENT COMMENT 'Stores the level of the question',
  `Marks` int(5) NOT NULL COMMENT 'Stores the marks of a particular level',
  PRIMARY KEY (`Lid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores what level has how many marks' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `questionbank`
--

CREATE TABLE IF NOT EXISTS `questionbank` (
  `QID` int(5) NOT NULL AUTO_INCREMENT COMMENT 'Stores Question''s ID',
  `CName` varchar(15) NOT NULL COMMENT 'Stores Category of the Question',
  `Question` text NOT NULL COMMENT 'Stores Question',
  `Option A` text NOT NULL COMMENT 'Stores Option A of the Question',
  `Option B` text NOT NULL COMMENT 'Stores Option B of the Question',
  `Option C` text NOT NULL COMMENT 'Stores Option C of the Question',
  `Option D` text NOT NULL COMMENT 'Stores Option D of the Question',
  `Correct Option` text NOT NULL COMMENT 'Stores Correct Option of the Question',
  `Lid` int(5) NOT NULL COMMENT 'Stores Level of the Question',
  PRIMARY KEY (`QID`)
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
  PRIMARY KEY (`SUsername`,`Testid`)
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
  `Tduration` int(5) NOT NULL COMMENT 'Stores the duration of the test',
  `Initialdifficulty` int(5) NOT NULL COMMENT 'Stores the initial difficulty of the Test',
  PRIMARY KEY (`Testid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the Details of the Test' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `testcategory`
--

CREATE TABLE IF NOT EXISTS `testcategory` (
  `Testid` int(5) NOT NULL COMMENT 'Stores ID of the Test ',
  `Categoryid` int(5) NOT NULL COMMENT 'Stores ID of the Category'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores the list of Category (used only if adaptive)';

-- --------------------------------------------------------

--
-- Table structure for table `test question`
--

CREATE TABLE IF NOT EXISTS `test question` (
  `Testid` int(5) NOT NULL COMMENT 'Stores the testid',
  `Quesitonid` int(5) NOT NULL COMMENT 'Stores question id',
  `Negativemark` int(5) NOT NULL COMMENT 'Stores Negative Mark For the Questions in  Test',
  PRIMARY KEY (`Testid`,`Quesitonid`)
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
  `Verified` varchar(1) NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Stores Details of User';

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

