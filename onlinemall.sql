-- MySQL dump 10.13  Distrib 5.7.27, for Win64 (x86_64)
--
-- Host: localhost    Database: onlinemall
-- ------------------------------------------------------
-- Server version	5.7.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `adminID` varchar(10) NOT NULL,
  `adminName` varchar(10) DEFAULT NULL,
  `adminPwd` varchar(10) DEFAULT NULL,
  `adminEmail` varchar(30) DEFAULT NULL,
  `adminTel` varchar(30) DEFAULT NULL,
  `supervisor` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('6109118194','yiii','Iverson&0','2388987360@qq.com','13026286771','is'),('6109118197','drew','Iverson&0','2521432213@qq.com','13487486818','is');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `cartID` varchar(10) NOT NULL,
  `cusID` varchar(10) DEFAULT NULL,
  `goodsID` varchar(10) DEFAULT NULL,
  `goodsName` varchar(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `statement` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`cartID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES ('1111','2200','1111','glass1',300,10,3000,'processed'),('124179615','5433','1111','glass1',300,1,300,'processed'),('159984680','7777','1111','glass1',300,1,300,'processed'),('2222','2222','2222','glass2',300,10,3000,'processed'),('3333','5433','3333','glass3',300,1,300,'processed'),('5555','5433','4444','glass4',300,1,300,'processed'),('918522883','5433','9999','glass9',600,1,600,'processed');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `cusID` varchar(10) NOT NULL,
  `cusName` varchar(10) DEFAULT NULL,
  `cusPwd` varchar(10) DEFAULT NULL,
  `cusEmail` varchar(30) DEFAULT NULL,
  `cusTel` varchar(30) DEFAULT NULL,
  `cusAddress` varchar(50) DEFAULT NULL,
  `balance` float DEFAULT '0',
  PRIMARY KEY (`cusID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('0606','wuzi','Iverson&0','1131666910@qq.com','13487486818','hunanchangsha',10000),('1111','shen','Iverson&0','1131666910@qq.com','13487486818','hunanchangsha',10000),('1616','h4cker','Iverson&0','1131666910@qq.com','13487486818','hunanchangsha',10000),('2200','jiege','Iverson&0','1131666910@qq.com','13487486818','hunanchangsha',10000),('2222','shyy','Iverson&0','1131666910@qq.com','13487486818','hunanchangsha',12000),('3333','youu','Iverson&0','1464496441@qq.com','13487486818','hunanchangsha',10000),('4369','kangya','Iverson&0','1131666910@qq.com','13487486818','hunanchangsha',10000),('4444','bbbbb','Iverson&0','1131666910@qq.com','13487486818','hunanchangsha',10000),('5433','weii','Iverson&0','2510964455@qq.com','13487486818','hunanchangsha',10000),('7777','clearlove','Iverson&0','1131666910@qq.com','13487486818','hunanchangsha',10000),('8888','miss','Iverson&0','1131666910@qq.com','13487486818','hunanchangsha',10000),('9999','qwer','Iverson&0','2521432213@qq.com','13487486818','hunanchangsha',10000);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `evaluation` (
  `evaluationID` varchar(10) NOT NULL,
  `cusID` varchar(10) DEFAULT NULL,
  `goodsID` varchar(10) DEFAULT NULL,
  `commentary` varchar(100) DEFAULT NULL,
  `evaluationDate` datetime DEFAULT NULL,
  PRIMARY KEY (`evaluationID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation`
--

LOCK TABLES `evaluation` WRITE;
/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
INSERT INTO `evaluation` VALUES ('1111','2200','5555','great','2020-12-11 20:20:20'),('2222','2200','5555','fantastic','2020-12-11 14:28:19'),('3333','2200','5555','awful','2020-12-11 14:29:33'),('4444','2200','5555','emoji','2020-12-11 14:29:56'),('5555','2200','5555','hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh','2020-12-11 14:30:44');
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `goods` (
  `goodsID` varchar(10) NOT NULL,
  `goodsName` varchar(20) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `category` varchar(10) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `image` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`goodsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` VALUES ('1111','glass1','none','无',300,999,'/img/glass1.png'),('2222','glass2','none','无',300,999,'/img/glass2.png'),('3333','glass3','none','无',300,999,'/img/glass3.png'),('4444','glass4','none','无',300,999,'/img/glass4.png'),('5555','glass5','none','无',300,999,'/img/glass5.png'),('6666','glass6','none','无',300,999,'/img/glass6.png'),('7777','glass7','none','无',300,999,'/img/glass7.png'),('8888','glass8','none','无',300,999,'/img/glass8.png'),('9999','glass9','none','无',600,1,'/img/glass9.png');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderform`
--

DROP TABLE IF EXISTS `orderform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderform` (
  `orderFormID` varchar(10) DEFAULT NULL,
  `cusID` varchar(10) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `orderFormDate` datetime DEFAULT NULL,
  `goodsID` varchar(10) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderform`
--

LOCK TABLES `orderform` WRITE;
/*!40000 ALTER TABLE `orderform` DISABLE KEYS */;
INSERT INTO `orderform` VALUES ('1111','5433','processed','2020-12-21 10:44:44','1111',7),('1111','5433','processed','2020-12-21 10:44:44','2222',7),('3333','2200','ignored','2020-12-28 10:39:13','3333',7),('4444','3333','processed','2021-01-01 16:28:08','4444',7),('5555','4444','processed','2020-12-31 16:28:21','1111',7),('6666','5433','processed','2020-12-30 16:28:36','3333',7),('6666','5433','processed','2020-12-29 16:28:36','1111',7),('7777','0606','processed','2020-12-29 14:35:17','3333',7),('8888','1616','processed','2021-01-02 14:37:08','2222',7),('9999','7777','processed','2021-01-03 14:37:52','2222',7),('1010','4369','processed','2021-01-04 14:38:38','4444',7),('0011','2222','processed','2020-12-31 14:40:24','4444',7),('0012','8888','processed','2020-12-31 14:40:50','3333',7),('35677949','5433','pending','2021-01-01 23:12:16','3333',10),('35677949','5433','pending','2021-01-01 23:12:16','4444',10),('57304493','2200','pending','2021-01-01 23:23:19','1111',10),('99869430','2200','pending','2021-01-01 23:23:53','1111',10),('618567468','5433','pending','2021-01-02 12:41:35','1111',1),('618567468','5433','pending','2021-01-02 12:41:35','3333',1),('618567468','5433','pending','2021-01-02 12:41:35','4444',1),('101822218','2222','pending','2021-01-02 13:14:57','2222',10),('255680450','5433','pending','2021-01-03 13:15:31','1111',1),('76884150','5433','pending','2021-01-03 14:43:24','9999',1),('30178830','7777','pending','2021-01-03 14:45:56','1111',1);
/*!40000 ALTER TABLE `orderform` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-03 15:11:01
