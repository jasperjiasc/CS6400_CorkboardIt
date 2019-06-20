-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: corkboard
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `categorize`
--

LOCK TABLES `categorize` WRITE;
/*!40000 ALTER TABLE `categorize` DISABLE KEYS */;
INSERT INTO `categorize` VALUES (1,'Architecture'),(2,'Home & Garden');
/*!40000 ALTER TABLE `categorize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('Architecture'),('Art'),('Education'),('Food & Drink'),('Home & Garden'),('Other'),('People'),('Pets'),('Photography'),('Sports'),('Technology'),('Travel');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (3,1,'2012-01-30 09:50:32','Is that a hot tub in the corner?'),(5,1,'2012-03-20 10:50:32','Why not!?');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `corkboard`
--

LOCK TABLES `corkboard` WRITE;
/*!40000 ALTER TABLE `corkboard` DISABLE KEYS */;
INSERT INTO `corkboard` VALUES (1,7,'2012-01-17 01:43:32','2012-01-18 13:49:32','public',NULL,'Pools'),(2,1,'2012-01-17 02:43:32','2012-01-19 01:55:32','public',NULL,'Gardens I love'),(3,9,'2012-01-16 01:43:32','2012-01-20 03:23:32','private','fd5456','Birthday Ideas'),(4,4,'2012-01-14 01:43:32','2012-01-21 06:43:32','public',NULL,'Vacation Spots'),(5,1,'2012-01-17 03:43:32','2012-01-22 07:55:32','public',NULL,'Cats and their Antics'),(6,1,'2012-01-17 04:43:32','2012-01-30 09:50:32','private','1234','Vacation Ideas');
/*!40000 ALTER TABLE `corkboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,2),(1,3),(1,4);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ppin_tag`
--

LOCK TABLES `ppin_tag` WRITE;
/*!40000 ALTER TABLE `ppin_tag` DISABLE KEYS */;
INSERT INTO `ppin_tag` VALUES (1,'2012-01-30 09:50:32','lantern, light, garden, stylish');
/*!40000 ALTER TABLE `ppin_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pushpin`
--

LOCK TABLES `pushpin` WRITE;
/*!40000 ALTER TABLE `pushpin` DISABLE KEYS */;
INSERT INTO `pushpin` VALUES (1,2,'2012-01-30 09:50:32','Great looking lantern','http://inmyownstyle.com/images/2011/06/Candle-La');
/*!40000 ALTER TABLE `pushpin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `regularuser`
--

LOCK TABLES `regularuser` WRITE;
/*!40000 ALTER TABLE `regularuser` DISABLE KEYS */;
INSERT INTO `regularuser` VALUES (1,'michael@bluthco.com','Michael','Bluth','michael123'),(2,'rtaylor@gatech.edu','Robert','Taylor','password123'),(3,'dwilliamson@gatech.edu','Daniel','Williamson','password123'),(4,'llopez@gatech.edu','Luis','Lopez','password123'),(5,'pestrada@gatech.edu','Patrick','Estrada','password123'),(6,'rwalker@gatech.edu','Rodney','Walker','password123'),(7,'shenry@gatech.edu','Sean','Henry','password123'),(8,'tfox@gatech.edu','Thomas','Fox','password123'),(9,'jflowers@gatech.edu','Jacob','Flowers','password123');
/*!40000 ALTER TABLE `regularuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_like`
--

LOCK TABLES `user_like` WRITE;
/*!40000 ALTER TABLE `user_like` DISABLE KEYS */;
INSERT INTO `user_like` VALUES (2,1),(4,1),(8,1);
/*!40000 ALTER TABLE `user_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `watch`
--

LOCK TABLES `watch` WRITE;
/*!40000 ALTER TABLE `watch` DISABLE KEYS */;
INSERT INTO `watch` VALUES (1,1),(1,2),(1,3),(1,4),(2,4),(3,5);
/*!40000 ALTER TABLE `watch` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-13 11:00:33
