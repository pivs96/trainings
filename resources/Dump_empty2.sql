-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: trainings
-- ------------------------------------------------------
-- Server version	5.6.24

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachments`
--

LOCK TABLES `attachments` WRITE;
/*!40000 ALTER TABLE `attachments` DISABLE KEYS */;
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
INSERT INTO `authentification` VALUES (1,'$2a$08$m6iowWPSdC4RryxctfLrbe4l3gO7BOvgZl/vK8./kzeZPi81U7ED6','Nikolay'),(2,'$2a$08$yln7Y.Q9tViPMoMeFB1qPenvNSFYBabizHywH7wAOG89k.aPr/Tb2','user2'),(3,'$2a$08$E3WtIrjjHJAKUm0QLM3X/.Vl2Q0MumxI8HrExe5SdrZmgl.SE.k96','user3'),(4,'$2a$08$BrkZG2q2GxQLISvdKmJ/GeKY.zTSJHWXfPrbAYzzsw6AsRq6CIM2S','user4'),(5,'$2a$08$4pUJZ33zhuy.Ni0PMARFOOs4iJluoxhoHA1sM0wmJGbPnpoyxsCkG','user5'),(6,'$2a$08$jxWjA47dCXJex18aoIKpGeLauQeuD6CAVikdTDKgyrFpZIRc73SJa','user6');
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
  `place` varchar(100) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_Entries_Trainings1_idx` (`training_id`),
  CONSTRAINT `fk_Entries_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entries`
--

LOCK TABLES `entries` WRITE;
/*!40000 ALTER TABLE `entries` DISABLE KEYS */;
INSERT INTO `entries` VALUES (1,1,'Minsk, 204','2015-08-07 13:00:00','2015-08-07 14:00:00'),(2,2,'Minsk, 205','2015-08-10 15:00:00','2015-08-10 16:00:00'),(3,3,'Minsk, 306','2015-08-11 13:00:00','2015-08-11 14:30:00'),(4,4,'Minsk, 208','2015-08-09 16:00:00','2015-08-09 18:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_users`
--

LOCK TABLES `training_users` WRITE;
/*!40000 ALTER TABLE `training_users` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainings`
--

LOCK TABLES `trainings` WRITE;
/*!40000 ALTER TABLE `trainings` DISABLE KEYS */;
INSERT INTO `trainings` VALUES (1,1,'Java','back-end developers','english','',3,'Very good training',4,0,0,0,''),(2,2,'Angular','front-end developers','russian','\0',1,'Interesting course',4,0,0,0,''),(3,3,'Programming for beginners','Beginners','belarusian','',0,'Useful training for beginners',4,0,0,0,''),(4,6,'PHP','back-end developers','english','',0,'Best practices on php',4,0,0,0,'\0');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_feedback_events`
--

LOCK TABLES `user_feedback_events` WRITE;
/*!40000 ALTER TABLE `user_feedback_events` DISABLE KEYS */;
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
INSERT INTO `users` VALUES (1,0,'ncherepkin@gmail.com','Nikolay','Cherepkin','+375292127973'),(2,2,'yan.palaznik@yandex.by','Jan','Palaznik','+375293368803'),(3,1,'vika95vika@gmail.com','Victoria','Khlystun','+375293760300'),(4,3,'Mihail.Kukuev@yandex.by','Mihail','Kukuev','+375291817718'),(5,2,'rock.ivan.poyda@gmail.com','Ivan','Poyda','+375291302232'),(6,0,'exadelgroup2@gmail.com','Evgeniy','Popov','+375447254222');
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

-- Dump completed on 2015-08-07 14:05:06
