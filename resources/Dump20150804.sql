-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: trainings
-- ------------------------------------------------------
-- Server version	5.6.25-log

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
-- Table structure for table `absentees`
--

DROP TABLE IF EXISTS `absentees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `absentees` (
  `entry_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reason` text,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_Absentees_Entries1_idx` (`entry_id`),
  KEY `fk_Absentees_Users1_idx` (`user_id`),
  CONSTRAINT `fk_Absentees_Entries1` FOREIGN KEY (`entry_id`) REFERENCES `entries` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Absentees_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `absentees`
--

LOCK TABLES `absentees` WRITE;
/*!40000 ALTER TABLE `absentees` DISABLE KEYS */;
INSERT INTO `absentees` VALUES (1,4,NULL,1),(2,1,NULL,2),(3,3,NULL,3),(4,4,NULL,4),(5,4,NULL,5),(6,3,NULL,6),(1,2,NULL,7);
/*!40000 ALTER TABLE `absentees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachments`
--

DROP TABLE IF EXISTS `attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) NOT NULL,
  `name` text,
  `link` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_Attachments_Trainings1_idx` (`training_id`),
  KEY `fk_trainings_1_idx` (`training_id`),
  CONSTRAINT `fk_trainings_1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachments`
--

LOCK TABLES `attachments` WRITE;
/*!40000 ALTER TABLE `attachments` DISABLE KEYS */;
INSERT INTO `attachments` VALUES (1,2,'it will help you','https://github.com'),(2,3,'use it in hometask','http://habrahabr.ru/post/77982/'),(3,1,'read attentivly','http://spring.io/'),(4,3,'read it','http://ithappens.me/'),(5,2,'use it','http://bash.im/'),(6,1,'for homework','http://www.adme.ru/'),(7,1,'best resource','https://sites.google.com/site/famcsbsu/labs/up/task2'),(8,2,'it will help you','https://github.com'),(9,2,'it will help you','https://github.com');
/*!40000 ALTER TABLE `attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authentification`
--

DROP TABLE IF EXISTS `authentification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authentification` (
  `user_id` int(11) NOT NULL,
  `password` varchar(100) NOT NULL,
  `login` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `fk_Passwords_Users1_idx` (`user_id`),
  CONSTRAINT `fk_Passwords_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authentification`
--

LOCK TABLES `authentification` WRITE;
/*!40000 ALTER TABLE `authentification` DISABLE KEYS */;
INSERT INTO `authentification` VALUES (1,'$2a$08$m6iowWPSdC4RryxctfLrbe4l3gO7BOvgZl/vK8./kzeZPi81U7ED6','Nikolay'),(2,'$2a$08$yln7Y.Q9tViPMoMeFB1qPenvNSFYBabizHywH7wAOG89k.aPr/Tb2','user2'),(3,'$2a$08$E3WtIrjjHJAKUm0QLM3X/.Vl2Q0MumxI8HrExe5SdrZmgl.SE.k96','user3'),(4,'$2a$08$BrkZG2q2GxQLISvdKmJ/GeKY.zTSJHWXfPrbAYzzsw6AsRq6CIM2S','user4'),(5,'$2a$08$4pUJZ33zhuy.Ni0PMARFOOs4iJluoxhoHA1sM0wmJGbPnpoyxsCkG','user5'),(6,'$2a$08$jxWjA47dCXJex18aoIKpGeLauQeuD6CAVikdTDKgyrFpZIRc73SJa','user6'),(7,'$2a$08$544Um6lV2J5cY9hY/B6uCu8EjL.tKdlOdNWRTyGQixEGOp.yhr8Fe','test');
/*!40000 ALTER TABLE `authentification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entries`
--

DROP TABLE IF EXISTS `entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) NOT NULL,
  `city` varchar(100) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `unit` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_Entries_Trainings1_idx` (`training_id`),
  CONSTRAINT `fk_Entries_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entries`
--

LOCK TABLES `entries` WRITE;
/*!40000 ALTER TABLE `entries` DISABLE KEYS */;
INSERT INTO `entries` VALUES (1,22,'Minsk, 205','2015-04-26 11:00:00','2015-04-26 13:00:00',NULL),(2,22,'Minsk, 243','2015-04-28 11:00:00','2015-04-28 12:00:00',NULL),(3,22,'Minsk, 204','2015-05-01 10:00:00','2015-05-01 12:00:00',NULL),(4,22,'Minsk, 205','2015-05-03 11:00:00','2015-05-03 13:00:00',NULL),(5,22,'Minsk, 243','2015-05-05 11:00:00','2015-05-05 12:00:00',NULL),(6,22,'Minsk, 204','2015-05-08 10:00:00','2015-05-08 12:00:00',NULL),(7,22,'Minsk, 205','2015-05-10 11:00:00','2015-05-10 13:00:00',NULL),(8,22,'Minsk, 243','2015-05-12 11:00:00','2015-05-12 12:00:00',NULL),(9,22,'Minsk, 204','2015-05-15 10:00:00','2015-05-15 12:00:00',NULL),(10,22,'Minsk, 205','2015-05-17 11:00:00','2015-05-17 13:00:00',NULL),(11,22,'Minsk, 243','2015-05-19 11:00:00','2015-05-19 12:00:00',NULL),(12,22,'Minsk, 204','2015-05-22 10:00:00','2015-05-22 12:00:00',NULL),(13,22,'Minsk, 205','2015-05-24 11:00:00','2015-05-24 13:00:00',NULL),(14,22,'Minsk, 243','2015-05-26 11:00:00','2015-05-26 12:00:00',NULL),(15,22,'Minsk, 204','2015-05-29 10:00:00','2015-05-29 12:00:00',NULL),(16,22,'Minsk, 205','2015-05-31 11:00:00','2015-05-31 13:00:00',NULL),(17,22,'Minsk, 243','2015-06-02 11:00:00','2015-06-02 12:00:00',NULL),(18,22,'Minsk, 204','2015-06-05 10:00:00','2015-06-05 12:00:00',NULL),(19,22,'Minsk, 205','2015-06-07 11:00:00','2015-06-07 13:00:00',NULL),(20,22,'Minsk, 243','2015-06-09 11:00:00','2015-06-09 12:00:00',NULL),(21,22,'Minsk, 204','2015-06-12 10:00:00','2015-06-12 12:00:00',NULL),(22,22,'Minsk, 205','2015-06-14 11:00:00','2015-06-14 13:00:00',NULL),(23,22,'Minsk, 243','2015-06-16 11:00:00','2015-06-16 12:00:00',NULL),(24,22,'Minsk, 204','2015-06-19 10:00:00','2015-06-19 12:00:00',NULL),(25,22,'Minsk, 205','2015-06-21 11:00:00','2015-06-21 13:00:00',NULL),(26,22,'Minsk, 243','2015-06-23 11:00:00','2015-06-23 12:00:00',NULL),(27,22,'Minsk, 204','2015-06-26 10:00:00','2015-06-26 12:00:00',NULL),(28,22,'Minsk, 205','2015-06-28 11:00:00','2015-06-28 13:00:00',NULL),(29,22,'Minsk, 243','2015-06-30 11:00:00','2015-06-30 12:00:00',NULL),(30,22,'Minsk, 204','2015-07-03 10:00:00','2015-07-03 12:00:00',NULL),(31,22,'Minsk, 205','2015-07-05 11:00:00','2015-07-05 13:00:00',NULL),(32,22,'Minsk, 243','2015-07-07 11:00:00','2015-07-07 12:00:00',NULL),(33,22,'Minsk, 204','2015-07-10 10:00:00','2015-07-10 12:00:00',NULL),(34,22,'Minsk, 205','2015-07-12 11:00:00','2015-07-12 13:00:00',NULL),(35,22,'Minsk, 243','2015-07-14 11:00:00','2015-07-14 12:00:00',NULL),(36,22,'Minsk, 204','2015-07-17 10:00:00','2015-07-17 12:00:00',NULL),(37,22,'Minsk, 205','2015-07-19 11:00:00','2015-07-19 13:00:00',NULL),(38,22,'Minsk, 243','2015-07-21 11:00:00','2015-07-21 12:00:00',NULL),(39,22,'Minsk, 204','2015-07-24 10:00:00','2015-07-24 12:00:00',NULL),(40,22,'Minsk, 205','2015-07-26 11:00:00','2015-07-26 13:00:00',NULL),(41,22,'Minsk, 243','2015-07-28 11:00:00','2015-07-28 12:00:00',NULL),(42,22,'Minsk, 204','2015-07-31 10:00:00','2015-07-31 12:00:00',NULL),(43,22,'Minsk, 205','2015-08-02 11:00:00','2015-08-02 13:00:00',NULL),(44,22,'Minsk, 243','2015-08-04 11:00:00','2015-08-04 12:00:00',NULL),(45,22,'Minsk, 204','2015-08-07 10:00:00','2015-08-07 12:00:00',NULL),(46,22,'Minsk, 205','2015-08-09 11:00:00','2015-08-09 13:00:00',NULL),(47,22,'Minsk, 243','2015-08-11 11:00:00','2015-08-11 12:00:00',NULL),(48,22,'Minsk, 204','2015-08-14 10:00:00','2015-08-14 12:00:00',NULL),(49,22,'Minsk, 205','2015-08-16 11:00:00','2015-08-16 13:00:00',NULL),(50,22,'Minsk, 243','2015-08-18 11:00:00','2015-08-18 12:00:00',NULL),(51,22,'Minsk, 204','2015-08-21 10:00:00','2015-08-21 12:00:00',NULL),(52,22,'Minsk, 205','2015-08-23 11:00:00','2015-08-23 13:00:00',NULL),(53,22,'Minsk, 243','2015-08-25 11:00:00','2015-08-25 12:00:00',NULL),(54,22,'Minsk, 204','2015-08-28 10:00:00','2015-08-28 12:00:00',NULL),(55,22,'Minsk, 205','2015-08-30 11:00:00','2015-08-30 13:00:00',NULL),(56,22,'Minsk, 243','2015-09-01 11:00:00','2015-09-01 12:00:00',NULL),(57,22,'Minsk, 204','2015-09-04 10:00:00','2015-09-04 12:00:00',NULL),(58,22,'Minsk, 205','2015-09-06 11:00:00','2015-09-06 13:00:00',NULL),(59,22,'Minsk, 243','2015-09-08 11:00:00','2015-09-08 12:00:00',NULL),(60,22,'Minsk, 204','2015-09-11 10:00:00','2015-09-11 12:00:00',NULL),(61,22,'Minsk, 205','2015-09-13 11:00:00','2015-09-13 13:00:00',NULL),(62,22,'Minsk, 243','2015-09-15 11:00:00','2015-09-15 12:00:00',NULL);
/*!40000 ALTER TABLE `entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_Attachments_Trainings1_idx` (`training_id`),
  KEY `fk_Attachments_Trainings2_idx` (`user_id`),
  CONSTRAINT `fk_Attachments_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Attachments_Trainings2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
INSERT INTO `ratings` VALUES (1,1,1),(2,1,2),(3,2,3),(4,2,1),(5,2,2),(6,2,3),(7,1,4),(8,3,3);
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_events`
--

DROP TABLE IF EXISTS `training_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) NOT NULL,
  `is_watched` tinyint(1) NOT NULL DEFAULT '0',
  `description` text,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`training_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_TrainingEvents_Trainings1_idx` (`training_id`),
  CONSTRAINT `fk_TrainingEvents_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_events`
--

LOCK TABLES `training_events` WRITE;
/*!40000 ALTER TABLE `training_events` DISABLE KEYS */;
INSERT INTO `training_events` VALUES (1,1,1,'changed date','2015-06-26 11:24:32'),(2,2,0,'changed time','2015-06-26 11:24:32');
/*!40000 ALTER TABLE `training_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_feedback_events`
--

DROP TABLE IF EXISTS `training_feedback_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_feedback_events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `training_feedback_id` int(11) NOT NULL,
  `is_watched` tinyint(1) NOT NULL,
  `description` text,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_TrainingFeedbackEvents_TrainingFeedbacks1_idx` (`training_feedback_id`),
  CONSTRAINT `fk_TrainingFeedbackEvents_TrainingFeedbacks1` FOREIGN KEY (`training_feedback_id`) REFERENCES `training_feedbacks` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_feedback_events`
--

LOCK TABLES `training_feedback_events` WRITE;
/*!40000 ALTER TABLE `training_feedback_events` DISABLE KEYS */;
INSERT INTO `training_feedback_events` VALUES (1,1,1,'blabla','2015-06-26 11:24:32'),(2,2,0,'blablabla','2015-06-26 11:24:32');
/*!40000 ALTER TABLE `training_feedback_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_feedbacks`
--

DROP TABLE IF EXISTS `training_feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_feedbacks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `training_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `understandable` tinyint(1) DEFAULT NULL,
  `interesting` tinyint(1) DEFAULT NULL,
  `new_knowledge` tinyint(1) DEFAULT NULL,
  `effectiveness` tinyint(4) DEFAULT NULL,
  `recommend` tinyint(1) DEFAULT NULL,
  `study_with_trainer` tinyint(1) DEFAULT NULL,
  `other_info` text,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_TrainingFeedbacks_Trainings1_idx` (`training_id`),
  KEY `fk_TrainingFeedbacks_Users1_idx` (`user_id`),
  CONSTRAINT `fk_TrainingFeedbacks_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TrainingFeedbacks_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_feedbacks`
--

LOCK TABLES `training_feedbacks` WRITE;
/*!40000 ALTER TABLE `training_feedbacks` DISABLE KEYS */;
INSERT INTO `training_feedbacks` VALUES (1,1,1,1,1,1,4,1,1,'everything is excekkent','2015-05-25 12:00:00'),(2,2,3,0,0,1,1,0,0,'awful!','2015-05-26 13:00:00'),(3,3,7,1,0,1,3,0,0,'very boring','2014-05-26 13:00:00'),(4,2,3,1,1,1,1,1,1,'very interesting training. i was impressed','2015-03-26 17:00:00'),(5,3,4,1,0,0,0,1,1,'nothing new, but i can say that trainer is rather good.','2013-05-26 13:00:00'),(6,2,6,1,1,1,2,1,1,'winderful training','2015-11-22 19:00:00');
/*!40000 ALTER TABLE `training_feedbacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_reserve`
--

DROP TABLE IF EXISTS `training_reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_reserve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reservist_id` int(11) NOT NULL,
  `training_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_TrainingReserve_Trainings1_idx` (`training_id`),
  KEY `fk_TrainingReserve_Users1_idx` (`reservist_id`),
  CONSTRAINT `fk_TrainingReserve_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TrainingReserve_Users1` FOREIGN KEY (`reservist_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_reserve`
--

LOCK TABLES `training_reserve` WRITE;
/*!40000 ALTER TABLE `training_reserve` DISABLE KEYS */;
/*!40000 ALTER TABLE `training_reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_users`
--

DROP TABLE IF EXISTS `training_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_users` (
  `training_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `begin_day` date DEFAULT NULL,
  `end_day` date DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_TrainingUsers_Trainings1_idx` (`training_id`),
  KEY `fk_TrainingUsers_Users1_idx` (`user_id`),
  CONSTRAINT `fk_TrainingUsers_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TrainingUsers_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_users`
--

LOCK TABLES `training_users` WRITE;
/*!40000 ALTER TABLE `training_users` DISABLE KEYS */;
INSERT INTO `training_users` VALUES (1,1,'2015-05-25','2015-05-29',1),(1,3,'2015-05-25',NULL,2),(2,3,'2015-05-25','2015-05-29',3),(2,4,NULL,NULL,4),(2,6,'2015-05-25',NULL,5),(3,2,NULL,'2015-05-29',6),(3,3,'2015-05-25',NULL,7);
/*!40000 ALTER TABLE `training_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainings`
--

DROP TABLE IF EXISTS `trainings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trainer_id` int(11) NOT NULL,
  `name` text,
  `target_audience` varchar(45) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  `is_external` bit(1) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  `description` text,
  `members_count_max` int(11) DEFAULT NULL,
  `members_count` int(11) DEFAULT NULL,
  `rating_sum` int(11) DEFAULT '0',
  `valuer_count` int(11) DEFAULT '0',
  `repeated` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_trainings_users1_idx` (`trainer_id`),
  CONSTRAINT `fk_trainings_users1` FOREIGN KEY (`trainer_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainings`
--

LOCK TABLES `trainings` WRITE;
/*!40000 ALTER TABLE `trainings` DISABLE KEYS */;
INSERT INTO `trainings` VALUES (1,1,'Java','back-end developers','english','',3,'Very good training',20,18,10,3,'\0'),(2,2,'Angular','front-end developers','russian','\0',0,'Interesting course',30,25,5,1,'\0'),(3,3,'Programming for beginners','Beginners','belarusian','',1,'Useful trainings for pupils',50,10,0,0,'\0'),(9,6,'aaa','aaa','aaa','\0',1,'aaa',5,0,0,0,'\0'),(10,6,'aaa','aaa','aaa','\0',1,'aaa',5,0,0,0,'\0'),(11,6,'aaa','aaa','aaa','\0',1,'aaa',5,0,0,0,'\0'),(12,6,'aaa','aaa','aaa','\0',1,'aaa',5,0,0,0,'\0'),(13,6,'bbb','bbb','bbb','\0',1,'aaa',5,0,0,0,''),(14,6,'ccc','bbb','bbb','\0',1,'aaa',5,0,0,0,''),(15,6,'ccc','bbb','bbb','\0',1,'aaa',5,0,0,0,''),(16,6,'ccc','bbb','bbb','\0',1,'aaa',5,0,0,0,''),(17,6,'ccc','bbb','bbb','\0',1,'aaa',5,0,0,0,''),(18,6,'ccc','bbb','bbb','\0',1,'aaa',5,0,0,0,''),(19,6,'ccc','bbb','bbb','\0',1,'aaa',5,0,0,0,''),(20,6,'ccc','bbb','bbb','\0',1,'aaa',5,0,0,0,''),(21,2,'aaa','aaa','aaa','\0',1,'aaa',5,0,0,0,''),(22,6,'ccc','bbb','bbb','\0',1,'aaa',5,0,0,0,'');
/*!40000 ALTER TABLE `trainings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_feedback_events`
--

DROP TABLE IF EXISTS `user_feedback_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_feedback_events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_feedback_id` int(11) NOT NULL,
  `is_watched` tinyint(1) NOT NULL,
  `description` text,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`user_feedback_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  CONSTRAINT `fk_UserFeedbackEvents_UserFeedbacks1` FOREIGN KEY (`user_feedback_id`) REFERENCES `user_feedbacks` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_feedback_events`
--

LOCK TABLES `user_feedback_events` WRITE;
/*!40000 ALTER TABLE `user_feedback_events` DISABLE KEYS */;
INSERT INTO `user_feedback_events` VALUES (1,1,1,'asdfasdf','2015-06-26 11:24:32'),(2,2,0,'qwerty','2015-06-26 11:24:32');
/*!40000 ALTER TABLE `user_feedback_events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_feedbacks`
--

DROP TABLE IF EXISTS `user_feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_feedbacks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `trainer_id` int(11) NOT NULL,
  `attendance` text,
  `attitude` text,
  `communication_skills` text,
  `questions` tinyint(1) DEFAULT NULL,
  `interested` tinyint(1) DEFAULT NULL,
  `focus_on_result` tinyint(1) DEFAULT NULL,
  `other_info` text,
  `level` tinyint(4) DEFAULT NULL,
  `grade` int(5) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_UserFeedbacks_Users1_idx` (`user_id`),
  KEY `fk_UserFeedbacks_Users2_idx` (`trainer_id`),
  CONSTRAINT `fk_UserFeedbacks_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserFeedbacks_Users2` FOREIGN KEY (`trainer_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_feedbacks`
--

LOCK TABLES `user_feedbacks` WRITE;
/*!40000 ALTER TABLE `user_feedbacks` DISABLE KEYS */;
INSERT INTO `user_feedbacks` VALUES (1,3,1,'excelent','responsible','rather good',1,1,1,'Good student. Very responsible and active.',0,3,'2015-06-26 11:24:32'),(2,1,3,'good','disorganized a little','good',1,1,1,'Need to work hard',1,2,'2015-06-26 15:17:02'),(3,6,2,'bad','responsible','excellent',0,1,1,'Works very hard, everything is ok.',2,3,'2015-06-26 11:24:32'),(4,7,2,'normal','not very responsible','not so good',0,0,0,'Need more practise',0,4,'2015-06-26 11:24:32'),(5,1,4,'god','very responsible','good',1,0,1,'Good student',3,4,'2015-06-26 11:24:32'),(6,3,3,'bd','awful','awful',0,0,0,'Terrible studnt',1,2,'2015-06-26 11:24:32'),(7,6,4,'nrml','very responsible','good',1,1,1,'Try to do his best',2,3,'2015-06-26 11:24:32');
/*!40000 ALTER TABLE `user_feedbacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` int(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_Users_userRoles1_idx` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,0,'ncherepkin@live.com','Nikolay','Cherepkin','+375292127973'),(2,2,'yan.palaznik@yandex.by','Jan','Palaznik','+375293368803'),(3,1,'vika95vika@gmail.com','Victoria','Khlystun','+375293760300'),(4,3,'Mihail.Kukuev@yandex.by','Mihail','Kukuev','+375291817718'),(5,2,'rock.ivan.poyda@gmail.com','Ivan','Poyda','+375291302232'),(6,0,'pevegeniy@exadel.com','Evgeniy','Popov','+375447254222'),(7,1,'test@test.com','Test','Testing','+123456789012');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-08-04 13:00:22
