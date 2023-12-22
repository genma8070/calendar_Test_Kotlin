-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: holiday
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `holidays`
--

DROP TABLE IF EXISTS `holidays`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `holidays` (
  `id` int NOT NULL AUTO_INCREMENT,
  `year` varchar(4) NOT NULL,
  `month` varchar(2) NOT NULL,
  `day` varchar(2) NOT NULL,
  `holiday_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `holidays`
--

LOCK TABLES `holidays` WRITE;
/*!40000 ALTER TABLE `holidays` DISABLE KEYS */;
INSERT INTO `holidays` VALUES (1,'2022','01','01','元日'),(2,'2022','01','10','成人の日'),(3,'2022','02','11','建国記念の日'),(4,'2022','02','23','天皇誕生日'),(5,'2022','03','21','春分の日'),(6,'2022','04','29','昭和の日'),(7,'2022','05','03','憲法記念日'),(8,'2022','05','04','みどりの日'),(9,'2022','05','05','こどもの日'),(10,'2022','07','18','海の日'),(11,'2022','08','11','山の日'),(12,'2022','09','19','敬老の日'),(13,'2022','09','23','秋分の日'),(14,'2022','10','10','スポーツの日'),(15,'2022','11','03','文化の日'),(16,'2022','11','23','勤労感謝の日'),(17,'2023','01','01','元日'),(18,'2023','01','02','休日 元日'),(19,'2023','01','09','成人の日'),(20,'2023','02','11','建国記念の日'),(21,'2023','02','23','天皇誕生日'),(22,'2023','03','21','春分の日'),(23,'2023','04','29','昭和の日'),(24,'2023','05','03','憲法記念日'),(25,'2023','05','04','みどりの日'),(26,'2023','05','05','こどもの日'),(27,'2023','07','17','海の日'),(28,'2023','08','11','山の日'),(29,'2023','09','18','敬老の日'),(30,'2023','09','23','秋分の日'),(31,'2023','10','09','スポーツの日'),(32,'2023','11','03','文化の日'),(33,'2023','11','23','勤労感謝の日'),(34,'2024','01','01','元日'),(35,'2024','01','08','成人の日'),(36,'2024','02','11','建国記念の日'),(37,'2024','02','12','建国記念の日 振替休日'),(38,'2024','02','23','天皇誕生日'),(39,'2024','03','20','春分の日'),(40,'2024','04','29','昭和の日'),(41,'2024','05','03','憲法記念日'),(42,'2024','05','04','みどりの日'),(43,'2024','05','05','こどもの日'),(44,'2024','05','06','こどもの日 振替休日'),(45,'2024','07','15','海の日'),(46,'2024','08','11','山の日'),(47,'2024','08','12','休日 山の日'),(48,'2024','09','16','敬老の日'),(49,'2024','09','22','秋分の日'),(50,'2024','09','23','秋分の日 振替休日'),(51,'2024','10','14','スポーツの日'),(52,'2024','11','03','文化の日'),(53,'2024','11','04','文化の日 振替休日'),(54,'2024','11','23','勤労感謝の日');
/*!40000 ALTER TABLE `holidays` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-22 13:36:25
