-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: qrcheck
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_num` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `sem_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_admin_semester1_idx` (`sem_id`),
  CONSTRAINT `fk_admin_semester1` FOREIGN KEY (`sem_id`) REFERENCES `semester` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `regi_id` int(11) NOT NULL,
  `num` int(11) NOT NULL COMMENT '회차',
  `state` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_check_registration2` (`regi_id`),
  CONSTRAINT `fk_check_registration2` FOREIGN KEY (`regi_id`) REFERENCES `registration` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3196 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sem_id` int(11) NOT NULL,
  `prof_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `split` tinyint(1) NOT NULL DEFAULT '0' COMMENT '분할수업 여부 \n0 분할x\n1 분할',
  `joint` int(1) NOT NULL DEFAULT '1' COMMENT '공동 개설 강의 수',
  `see` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK_Course_Professor` (`prof_id`),
  KEY `fk_course_semester1_idx` (`sem_id`),
  KEY `fk_course_room1` (`room_id`),
  CONSTRAINT `fk_course_professor1` FOREIGN KEY (`prof_id`) REFERENCES `professor` (`id`),
  CONSTRAINT `fk_course_room1` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`),
  CONSTRAINT `fk_course_semester1` FOREIGN KEY (`sem_id`) REFERENCES `semester` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `division`
--

DROP TABLE IF EXISTS `division`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `division` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cour_id` int(11) NOT NULL,
  `code` varchar(50) NOT NULL COMMENT '과목코드',
  `no` int(1) NOT NULL DEFAULT '1',
  `dept_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_division_course1_idx` (`cour_id`),
  CONSTRAINT `fk_division_course1` FOREIGN KEY (`cour_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `professor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prof_num` varchar(50) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(500) NOT NULL DEFAULT '1111',
  `dept_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_id` int(11) NOT NULL,
  `cour_id` int(11) NOT NULL,
  `div_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_registration_course1_idx` (`cour_id`),
  KEY `fk_registration_division1_idx` (`div_id`),
  KEY `fk_registration_student1` (`stu_id`),
  CONSTRAINT `fk_registration_course1` FOREIGN KEY (`cour_id`) REFERENCES `course` (`id`),
  CONSTRAINT `fk_registration_division1` FOREIGN KEY (`div_id`) REFERENCES `division` (`id`),
  CONSTRAINT `fk_registration_student1` FOREIGN KEY (`stu_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=214 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) NOT NULL,
  `sem` int(1) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `week_num` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stu_num` varchar(50) NOT NULL COMMENT '학번',
  `password` varchar(500) NOT NULL DEFAULT '1111' COMMENT '비밀번호',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '이름',
  `dept_name` varchar(50) DEFAULT NULL COMMENT '학과이름',
  `year` int(11) NOT NULL COMMENT '학년',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=258 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `time`
--

DROP TABLE IF EXISTS `time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cour_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `start_time` time NOT NULL,
  `end_time` time NOT NULL,
  `day_of_week` int(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_time_course1_idx` (`cour_id`),
  CONSTRAINT `fk_time_course1` FOREIGN KEY (`cour_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-15 19:38:00
