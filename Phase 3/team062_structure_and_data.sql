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
-- Table structure for table `categorize`
--

DROP TABLE IF EXISTS `categorize`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categorize` (
  `corkboard_id` int(16) unsigned NOT NULL,
  `cat_name` varchar(20) NOT NULL,
  PRIMARY KEY (`corkboard_id`,`cat_name`),
  KEY `cat_name` (`cat_name`),
  CONSTRAINT `fk_Categorize_corkboard_id_Corkboard_corkboard_id` FOREIGN KEY (`corkboard_id`) REFERENCES `corkboard` (`corkboard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorize`
--

LOCK TABLES `categorize` WRITE;
/*!40000 ALTER TABLE `categorize` DISABLE KEYS */;
INSERT INTO `categorize` VALUES (1,'Architecture'),(2,'Home & Garden');
/*!40000 ALTER TABLE `categorize` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `cat_name` varchar(20) NOT NULL,
  PRIMARY KEY (`cat_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('Architecture'),('Art'),('Education'),('Food & Drink'),('Home & Garden'),('Other'),('People'),('Pets'),('Photography'),('Sports'),('Technology'),('Travel');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment` (
  `user_id` int(16) unsigned NOT NULL,
  `pushpin_id` int(16) unsigned NOT NULL,
  `c_dtime` datetime NOT NULL,
  `content` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`pushpin_id`,`c_dtime`),
  KEY `fk_Comment_pushpin_id_Pushpin_pushpin_id` (`pushpin_id`),
  CONSTRAINT `fk_Comment_pushpin_id_Pushpin_pushpin_id` FOREIGN KEY (`pushpin_id`) REFERENCES `pushpin` (`pushpin_id`),
  CONSTRAINT `fk_Comment_user_id_RegularUser_user_id` FOREIGN KEY (`user_id`) REFERENCES `regularuser` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (3,1,'2012-01-30 09:50:32','Is that a hot tub in the corner?'),(5,1,'2012-03-20 10:50:32','Why not!?');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corkboard`
--

DROP TABLE IF EXISTS `corkboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `corkboard` (
  `corkboard_id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(16) unsigned NOT NULL,
  `creation_dtime` datetime NOT NULL,
  `last_dtime` datetime NOT NULL,
  `visibility` varchar(8) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `title` varchar(250) NOT NULL,
  PRIMARY KEY (`corkboard_id`),
  KEY `fk_CorkBoard_user_id_RegularUser_user_id` (`user_id`),
  CONSTRAINT `fk_CorkBoard_user_id_RegularUser_user_id` FOREIGN KEY (`user_id`) REFERENCES `regularuser` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corkboard`
--

LOCK TABLES `corkboard` WRITE;
/*!40000 ALTER TABLE `corkboard` DISABLE KEYS */;
INSERT INTO `corkboard` VALUES (1,7,'2012-01-17 01:43:32','2012-01-18 13:49:32','public',NULL,'Pools'),(2,1,'2012-01-17 02:43:32','2012-01-19 01:55:32','public',NULL,'Gardens I love'),(3,9,'2012-01-16 01:43:32','2012-01-20 03:23:32','private','fd5456','Birthday Ideas'),(4,4,'2012-01-14 01:43:32','2012-01-21 06:43:32','public',NULL,'Vacation Spots'),(5,1,'2012-01-17 03:43:32','2012-01-22 07:55:32','public',NULL,'Cats and their Antics'),(6,1,'2012-01-17 04:43:32','2012-01-30 09:50:32','private','1234','Vacation Ideas');
/*!40000 ALTER TABLE `corkboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `follow` (
  `user_id` int(16) unsigned NOT NULL,
  `followed_user_id` int(16) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`followed_user_id`),
  KEY `followed_user_id` (`followed_user_id`),
  CONSTRAINT `fk_Follow_followed_user_id_RegularUser_user_id` FOREIGN KEY (`followed_user_id`) REFERENCES `regularuser` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Follow_user_id_RegularUser_user_id` FOREIGN KEY (`user_id`) REFERENCES `regularuser` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (1,2),(1,3),(1,4);
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ppin_tag`
--

DROP TABLE IF EXISTS `ppin_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `ppin_tag` (
  `pushpin_id` int(16) unsigned NOT NULL,
  `pp_dtime` datetime NOT NULL,
  `tag` varchar(50) NOT NULL,
  PRIMARY KEY (`pushpin_id`,`tag`),
  CONSTRAINT `fk_PPin_Tag_pushpin_id_Pushpin_pushpin_id` FOREIGN KEY (`pushpin_id`) REFERENCES `pushpin` (`pushpin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ppin_tag`
--

LOCK TABLES `ppin_tag` WRITE;
/*!40000 ALTER TABLE `ppin_tag` DISABLE KEYS */;
INSERT INTO `ppin_tag` VALUES (1,'2012-01-30 09:50:32','lantern, light, garden, stylish');
/*!40000 ALTER TABLE `ppin_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pushpin`
--

DROP TABLE IF EXISTS `pushpin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pushpin` (
  `pushpin_id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `corkboard_id` int(16) unsigned NOT NULL,
  `pp_dtime` datetime NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `url_image` varchar(500) NOT NULL,
  PRIMARY KEY (`pushpin_id`),
  KEY `fk_Pushpin_corkboard_id_Corkboard_corkboard_id` (`corkboard_id`),
  CONSTRAINT `fk_Pushpin_corkboard_id_Corkboard_corkboard_id` FOREIGN KEY (`corkboard_id`) REFERENCES `corkboard` (`corkboard_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pushpin`
--

LOCK TABLES `pushpin` WRITE;
/*!40000 ALTER TABLE `pushpin` DISABLE KEYS */;
INSERT INTO `pushpin` VALUES (1,2,'2012-01-30 09:50:32','Great looking lantern','http://inmyownstyle.com/images/2011/06/Candle-La');
/*!40000 ALTER TABLE `pushpin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regularuser`
--

DROP TABLE IF EXISTS `regularuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `regularuser` (
  `user_id` int(16) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(250) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `pin` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regularuser`
--

LOCK TABLES `regularuser` WRITE;
/*!40000 ALTER TABLE `regularuser` DISABLE KEYS */;
INSERT INTO `regularuser` VALUES (1,'michael@bluthco.com','Michael','Bluth','michael123'),(2,'rtaylor@gatech.edu','Robert','Taylor','password123'),(3,'dwilliamson@gatech.edu','Daniel','Williamson','password123'),(4,'llopez@gatech.edu','Luis','Lopez','password123'),(5,'pestrada@gatech.edu','Patrick','Estrada','password123'),(6,'rwalker@gatech.edu','Rodney','Walker','password123'),(7,'shenry@gatech.edu','Sean','Henry','password123'),(8,'tfox@gatech.edu','Thomas','Fox','password123'),(9,'jflowers@gatech.edu','Jacob','Flowers','password123');
/*!40000 ALTER TABLE `regularuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_like`
--

DROP TABLE IF EXISTS `user_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_like` (
  `user_id` int(16) unsigned NOT NULL,
  `pushpin_id` int(16) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`pushpin_id`),
  KEY `fk_Like_pushpin_id_Pushpin_pushpin_id` (`pushpin_id`),
  CONSTRAINT `fk_Like_pushpin_id_Pushpin_pushpin_id` FOREIGN KEY (`pushpin_id`) REFERENCES `pushpin` (`pushpin_id`),
  CONSTRAINT `fk_Like_user_id_RegularUser_user_id` FOREIGN KEY (`user_id`) REFERENCES `regularuser` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_like`
--

LOCK TABLES `user_like` WRITE;
/*!40000 ALTER TABLE `user_like` DISABLE KEYS */;
INSERT INTO `user_like` VALUES (2,1),(4,1),(8,1);
/*!40000 ALTER TABLE `user_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watch`
--

DROP TABLE IF EXISTS `watch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `watch` (
  `user_id` int(16) unsigned NOT NULL,
  `corkboard_id` int(16) unsigned NOT NULL,
  PRIMARY KEY (`user_id`,`corkboard_id`),
  KEY `fk_Watch_corkboard_id_Corkboard_corkboard_id` (`corkboard_id`),
  CONSTRAINT `fk_Watch_corkboard_id_Corkboard_corkboard_id` FOREIGN KEY (`corkboard_id`) REFERENCES `corkboard` (`corkboard_id`),
  CONSTRAINT `fk_Watch_user_id_RegularUser_user_id` FOREIGN KEY (`user_id`) REFERENCES `regularuser` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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

-- Dump completed on 2018-11-13 11:03:32
