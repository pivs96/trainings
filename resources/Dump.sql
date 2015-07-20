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
  PRIMARY KEY (`entry_id`,`user_id`),
  KEY `fk_AbcentUsers_Users1_idx` (`user_id`),
  CONSTRAINT `fk_AbcentUsers_Entries1` FOREIGN KEY (`entry_id`) REFERENCES `entries` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AbcentUsers_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `absentees`
--

LOCK TABLES `absentees` WRITE;
/*!40000 ALTER TABLE `absentees` DISABLE KEYS */;
INSERT INTO `absentees` VALUES (2,1),(1,2),(3,3),(6,3),(1,4),(4,4),(5,4);
/*!40000 ALTER TABLE `absentees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachments`
--

DROP TABLE IF EXISTS `attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachments` (
  `id` int(11) NOT NULL,
  `entry_id` int(11) NOT NULL,
  `type` varchar(30) DEFAULT NULL,
  `name` text,
  `link` text,
  PRIMARY KEY (`id`),
  KEY `fk_Attachments_Entries1_idx` (`entry_id`),
  CONSTRAINT `fk_attachments_entries1` FOREIGN KEY (`entry_id`) REFERENCES `entries` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachments`
--

LOCK TABLES `attachments` WRITE;
/*!40000 ALTER TABLE `attachments` DISABLE KEYS */;
INSERT INTO `attachments` VALUES (0,1,'presentation','best resource','https://sites.google.com/site/famcsbsu/labs/up/task2'),(1,2,'document','it will help you','https://github.com'),(2,3,'presentation','use it in hometask','http://habrahabr.ru/post/77982/'),(3,1,'presentation','read attentivly','http://spring.io/'),(4,3,'document','read it','http://ithappens.me/'),(5,2,'presentation','use it','http://bash.im/'),(6,1,'document','for homework','http://www.adme.ru/');
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
  `password` varchar(45) NOT NULL,
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
INSERT INTO `authentification` VALUES (0,'pass0','test'),(1,'pass1','Nikolay'),(2,'pass2','user2'),(3,'pass3','user3'),(4,'pass4','user4'),(5,'pass5','user5'),(6,'pass6','user6');
/*!40000 ALTER TABLE `authentification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entries`
--

DROP TABLE IF EXISTS `entries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `entries` (
  `id` int(11) NOT NULL,
  `training_id` int(11) NOT NULL,
  `place` varchar(100) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Entries_Trainings1_idx` (`training_id`),
  CONSTRAINT `fk_Entries_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entries`
--

LOCK TABLES `entries` WRITE;
/*!40000 ALTER TABLE `entries` DISABLE KEYS */;
INSERT INTO `entries` VALUES (1,1,'Minsk, 204','2015-05-25 10:00:00','2015-05-25 12:00:00'),(2,1,'Minsk, 205','2015-05-26 11:00:00','2015-05-26 13:00:00'),(3,2,'Minsk, 243','2015-05-28 11:00:00','2015-05-28 12:00:00'),(4,3,'Minsk, 243','2015-05-25 15:00:00','2015-05-25 16:30:00'),(5,3,'Minsk, 243','2015-05-29 15:00:00','2015-05-29 16:30:00'),(6,3,'Minsk, 243','2015-06-02 15:00:00','2015-06-02 16:30:00');
/*!40000 ALTER TABLE `entries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ratings` (
  `id` int(11) NOT NULL,
  `training_id` int(11) NOT NULL,
  `rating` tinyint(6) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Attachments_Trainings1_idx` (`training_id`),
  KEY `fk_Attachments_Trainings2_idx` (`user_id`),
  CONSTRAINT `fk_Attachments_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Attachments_Trainings2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
INSERT INTO `ratings` VALUES (1,1,4,1),(2,1,5,2),(3,2,4,3),(4,2,4,1),(5,2,3,2),(6,2,4,3),(7,1,5,4);
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_feedbacks`
--

DROP TABLE IF EXISTS `training_feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `training_feedbacks` (
  `id` int(11) NOT NULL,
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
  KEY `fk_TrainingFeedbacks_Trainings1_idx` (`training_id`),
  KEY `fk_TrainingFeedbacks_Users1_idx` (`user_id`),
  CONSTRAINT `fk_TrainingFeedbacks_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TrainingFeedbacks_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_feedbacks`
--

LOCK TABLES `training_feedbacks` WRITE;
/*!40000 ALTER TABLE `training_feedbacks` DISABLE KEYS */;
INSERT INTO `training_feedbacks` VALUES (1,1,1,1,1,1,4,1,1,'everything is excekkent','2015-05-25 12:00:00'),(2,2,5,0,0,1,1,0,0,'awful!','2015-05-26 13:00:00'),(3,3,2,1,0,1,3,0,0,'very boring','2014-05-26 13:00:00'),(4,2,3,1,1,1,1,1,1,'very interesting training. i was impressed','2015-03-26 17:00:00'),(5,3,5,1,0,0,0,1,1,'nothing new, but i can say that trainer is rather good.','2013-05-26 13:00:00'),(6,2,6,1,1,1,2,1,1,'winderful training','2015-11-22 19:00:00');
/*!40000 ALTER TABLE `training_feedbacks` ENABLE KEYS */;
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
  `deleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`training_id`,`user_id`),
  KEY `fk_TrainingUsers_Users1_idx` (`user_id`),
  CONSTRAINT `fk_TrainingUsers_Trainings1` FOREIGN KEY (`training_id`) REFERENCES `trainings` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TrainingUsers_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_users`
--

LOCK TABLES `training_users` WRITE;
/*!40000 ALTER TABLE `training_users` DISABLE KEYS */;
INSERT INTO `training_users` VALUES (1,1,0),(1,3,1),(2,3,1),(2,4,1),(2,6,1),(3,2,0),(3,5,0);
/*!40000 ALTER TABLE `training_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainings`
--

DROP TABLE IF EXISTS `trainings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainings` (
  `id` int(11) NOT NULL,
  `trainer_id` int(11) NOT NULL,
  `name` text,
  `target_audience` varchar(45) DEFAULT NULL,
  `language` varchar(20) DEFAULT NULL,
  `is_external` bit(1) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '0',
  `description` text,
  `members_count_max` int(11) DEFAULT NULL,
  `members_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Trainings_Users1_idx` (`trainer_id`),
  CONSTRAINT `fk_trainings_users1` FOREIGN KEY (`trainer_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainings`
--

LOCK TABLES `trainings` WRITE;
/*!40000 ALTER TABLE `trainings` DISABLE KEYS */;
INSERT INTO `trainings` VALUES (1,1,'Java','back-end developers','english','',3,'Very good training',20,18),(2,2,'Angular','front-end developers','russian','\0',0,'Interesting course',30,25),(3,3,'Programming for beginners','Beginners','belarusian','',1,'Useful trainings for pupils',50,10);
/*!40000 ALTER TABLE `trainings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_feedbacks`
--

DROP TABLE IF EXISTS `user_feedbacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_feedbacks` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `trainer_id` int(11) NOT NULL,
  `attendance` text,
  `attitude` text,
  `communication_skills` text,
  `questions` tinyint(1) DEFAULT NULL,
  `interested` tinyint(1) DEFAULT NULL,
  `focus_on_result` tinyint(1) DEFAULT NULL,
  `other_info` text,
  `level` varchar(45) DEFAULT NULL,
  `grade` int(5) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_UserFeedbacks_Users1_idx` (`user_id`),
  KEY `fk_UserFeedbacks_Users2_idx` (`trainer_id`),
  CONSTRAINT `fk_UserFeedbacks_Users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_UserFeedbacks_Users2` FOREIGN KEY (`trainer_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_feedbacks`
--

LOCK TABLES `user_feedbacks` WRITE;
/*!40000 ALTER TABLE `user_feedbacks` DISABLE KEYS */;
INSERT INTO `user_feedbacks` VALUES (1,2,1,'excelent','responsible','rather good',1,1,1,'Good student. Very responsible and active.','intermediate',8,'2015-06-26 11:24:32'),(2,1,3,'good','disorganized a little','good',1,1,1,'Need to work hard','upper-intermediate',7,'2015-06-26 15:17:02'),(3,5,2,'bad','responsible','excellent',0,1,1,'Works very hard, everything is ok.','intermediate',9,'2015-06-26 11:24:32'),(4,3,2,'normal','not very responsible','not so good',0,0,0,'Need more practise','intermediate',6,'2015-06-26 11:24:32'),(5,4,4,'god','very responsible','good',1,0,1,'Good student','pre-intermediate',8,'2015-06-26 11:24:32'),(6,6,3,'bd','awful','awful',0,0,0,'Terrible studnt','elementary',2,'2015-06-26 11:24:32'),(7,1,4,'nrml','very responsible','good',1,1,1,'Try to do his best','intermediate',9,'2015-06-26 11:24:32');
/*!40000 ALTER TABLE `user_feedbacks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `role` int(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Users_userRoles1_idx` (`role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (0,1,'test@test.com','Test','Testing','+123456789012'),(1,0,'ncherepkin@live.com','Nikolay','Cherepkin','+375292127973'),(2,2,'yan.palaznik@yandex.by','Jan','Palaznik','+375293368803'),(3,1,'vika95vika@gmail.com','Victoria','Khlystun','+375293760300'),(4,3,'Mihail.Kukuev@yandex.by','Mihail','Kukuev','+375291817718'),(5,2,'rock.ivan.poyda@gmail.com','Ivan','Poyda','+375291302232'),(6,0,'pevegeniy@exadel.com','Evgeniy','Popov','+375447254222');
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

-- Dump completed on 2015-07-20 16:02:39
