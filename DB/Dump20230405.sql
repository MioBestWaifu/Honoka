CREATE DATABASE  IF NOT EXISTS `aluguel` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `aluguel`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: aluguel
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `area` (
  `idArea` int NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  PRIMARY KEY (`idArea`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'Salvador'),(2,'Sto Amaro'),(3,'Fra de Santana'),(4,'Cabuçu');
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviceavailability`
--

DROP TABLE IF EXISTS `serviceavailability`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serviceavailability` (
  `serviceAvailabilityID` int NOT NULL AUTO_INCREMENT,
  `templateID` int NOT NULL,
  `weekday` int NOT NULL,
  `startHour` time NOT NULL,
  `endHour` time NOT NULL,
  PRIMARY KEY (`serviceAvailabilityID`),
  KEY `templateInAvailability_idx` (`templateID`),
  CONSTRAINT `templateInAvailability` FOREIGN KEY (`templateID`) REFERENCES `servicetemplates` (`idServiceTemplates`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceavailability`
--

LOCK TABLES `serviceavailability` WRITE;
/*!40000 ALTER TABLE `serviceavailability` DISABLE KEYS */;
INSERT INTO `serviceavailability` VALUES (1,1,0,'11:00:00','13:30:00'),(2,1,4,'16:15:00','19:20:00');
/*!40000 ALTER TABLE `serviceavailability` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicecategory`
--

DROP TABLE IF EXISTS `servicecategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicecategory` (
  `idServiceCategory` int NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(45) NOT NULL,
  PRIMARY KEY (`idServiceCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicecategory`
--

LOCK TABLES `servicecategory` WRITE;
/*!40000 ALTER TABLE `servicecategory` DISABLE KEYS */;
INSERT INTO `servicecategory` VALUES (1,'Companionship'),(2,'Direct Action'),(3,'Freelancing'),(4,'Gaming'),(5,'Others');
/*!40000 ALTER TABLE `servicecategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviceinstances`
--

DROP TABLE IF EXISTS `serviceinstances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serviceinstances` (
  `idServiceInstances` int NOT NULL AUTO_INCREMENT,
  `templateID` int NOT NULL,
  `clientID` int NOT NULL,
  `finished` tinyint DEFAULT '0',
  `cost` float(7,2) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  PRIMARY KEY (`idServiceInstances`),
  KEY `template_idx` (`templateID`),
  KEY `client_idx` (`clientID`),
  CONSTRAINT `client` FOREIGN KEY (`clientID`) REFERENCES `user` (`idUser`),
  CONSTRAINT `template` FOREIGN KEY (`templateID`) REFERENCES `servicetemplates` (`idServiceTemplates`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceinstances`
--

LOCK TABLES `serviceinstances` WRITE;
/*!40000 ALTER TABLE `serviceinstances` DISABLE KEYS */;
INSERT INTO `serviceinstances` VALUES (1,1,1,1,400.00,'2023-07-04','2023-07-04','11:15:00','14:00:00');
/*!40000 ALTER TABLE `serviceinstances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicemodality`
--

DROP TABLE IF EXISTS `servicemodality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicemodality` (
  `idServiceModality` int NOT NULL AUTO_INCREMENT,
  `modalityName` varchar(45) NOT NULL,
  PRIMARY KEY (`idServiceModality`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicemodality`
--

LOCK TABLES `servicemodality` WRITE;
/*!40000 ALTER TABLE `servicemodality` DISABLE KEYS */;
INSERT INTO `servicemodality` VALUES (1,'In-person'),(2,'Virtual');
/*!40000 ALTER TABLE `servicemodality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicerequests`
--

DROP TABLE IF EXISTS `servicerequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicerequests` (
  `serviceRequestID` int NOT NULL AUTO_INCREMENT,
  `templateID` int NOT NULL,
  `clientID` int NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `cost` float(7,2) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  PRIMARY KEY (`serviceRequestID`),
  KEY `template_idx` (`templateID`),
  KEY `client_idx` (`clientID`),
  CONSTRAINT `clientInRequests` FOREIGN KEY (`clientID`) REFERENCES `user` (`idUser`),
  CONSTRAINT `templateInRequests` FOREIGN KEY (`templateID`) REFERENCES `servicetemplates` (`idServiceTemplates`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicerequests`
--

LOCK TABLES `servicerequests` WRITE;
/*!40000 ALTER TABLE `servicerequests` DISABLE KEYS */;
INSERT INTO `servicerequests` VALUES (1,1,1,'2023-07-23','2023-07-23',150.00,'11:23:00','13:23:00'),(2,2,7,'2023-08-11','2023-08-11',350.15,'11:00:00','13:30:00');
/*!40000 ALTER TABLE `servicerequests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicereviews`
--

DROP TABLE IF EXISTS `servicereviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicereviews` (
  `idclient` int NOT NULL,
  `idtemplate` int NOT NULL,
  `score` int NOT NULL,
  `comment` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`idclient`,`idtemplate`),
  KEY `clientInServReview_idx` (`idclient`),
  KEY `templateInServiceReview_idx` (`idtemplate`),
  CONSTRAINT `clientInServReview` FOREIGN KEY (`idclient`) REFERENCES `user` (`idUser`),
  CONSTRAINT `templateInServiceReview` FOREIGN KEY (`idtemplate`) REFERENCES `servicetemplates` (`idServiceTemplates`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicereviews`
--

LOCK TABLES `servicereviews` WRITE;
/*!40000 ALTER TABLE `servicereviews` DISABLE KEYS */;
INSERT INTO `servicereviews` VALUES (11,1,1,'Waste of time and money'),(11,2,1,'Best purchase of my life!'),(11,3,4,'Best purchase of my life!'),(11,4,1,'It\'s OK'),(11,5,1,'It\'s OK'),(11,6,1,'Best purchase of my life!'),(11,7,2,'Waste of time and money'),(11,8,1,'Waste of time and money'),(11,9,1,'It\'s OK'),(11,10,1,'Best purchase of my life!'),(11,11,2,'Best purchase of my life!'),(11,12,2,'It\'s OK'),(11,13,1,'It\'s OK'),(11,14,3,'Best purchase of my life!'),(12,1,3,'Waste of time and money'),(12,2,1,'Best purchase of my life!'),(12,3,1,'Waste of time and money'),(12,4,1,'It\'s OK'),(12,5,4,'It\'s OK'),(12,6,4,'Waste of time and money'),(12,7,3,'Waste of time and money'),(12,8,2,'Waste of time and money'),(12,9,2,'It\'s OK'),(12,10,1,'Waste of time and money'),(12,11,3,'Waste of time and money'),(12,12,4,'Best purchase of my life!'),(12,13,4,'Waste of time and money'),(12,14,1,'Waste of time and money'),(13,1,2,'Best purchase of my life!'),(13,2,5,'It\'s OK'),(13,3,4,'Best purchase of my life!'),(13,4,1,'Best purchase of my life!'),(13,5,1,'Waste of time and money'),(13,6,2,'Waste of time and money'),(13,7,1,'Best purchase of my life!'),(13,8,3,'Best purchase of my life!'),(13,9,4,'Waste of time and money'),(13,10,2,'Waste of time and money'),(13,11,3,'Best purchase of my life!'),(13,12,4,'It\'s OK'),(13,13,2,'It\'s OK'),(13,14,3,'Waste of time and money'),(14,1,4,'It\'s OK'),(14,2,3,'Best purchase of my life!'),(14,3,3,'It\'s OK'),(14,4,1,'Best purchase of my life!'),(14,5,3,'Best purchase of my life!'),(14,6,2,'It\'s OK'),(14,7,1,'Best purchase of my life!'),(14,8,5,'Waste of time and money'),(14,9,1,'It\'s OK'),(14,10,3,'Best purchase of my life!'),(14,11,2,'Best purchase of my life!'),(14,12,1,'Waste of time and money'),(14,13,2,'Waste of time and money'),(14,14,1,'Best purchase of my life!'),(15,1,4,'Best purchase of my life!'),(15,2,2,'Waste of time and money'),(15,3,4,'It\'s OK'),(15,4,1,'Waste of time and money'),(15,5,3,'Best purchase of my life!'),(15,6,1,'It\'s OK'),(15,7,2,'Waste of time and money'),(15,8,1,'It\'s OK'),(15,9,5,'Best purchase of my life!'),(15,10,5,'Best purchase of my life!'),(15,11,3,'Best purchase of my life!'),(15,12,5,'It\'s OK'),(15,13,2,'Waste of time and money'),(15,14,2,'Best purchase of my life!'),(16,1,4,'Best purchase of my life!'),(16,2,4,'Best purchase of my life!'),(16,3,5,'It\'s OK'),(16,4,4,'Best purchase of my life!'),(16,5,1,'Best purchase of my life!'),(16,6,1,'Waste of time and money'),(16,7,2,'Waste of time and money'),(16,8,1,'Waste of time and money'),(16,9,1,'Best purchase of my life!'),(16,10,2,'Waste of time and money'),(16,11,5,'It\'s OK'),(16,12,5,'Waste of time and money'),(16,13,1,'Waste of time and money'),(16,14,5,'Best purchase of my life!'),(17,1,1,'It\'s OK'),(17,2,3,'Waste of time and money'),(17,3,1,'Best purchase of my life!'),(17,4,5,'It\'s OK'),(17,5,3,'Waste of time and money'),(17,6,5,'Waste of time and money'),(17,7,1,'It\'s OK'),(17,8,4,'Waste of time and money'),(17,9,5,'Best purchase of my life!'),(17,10,1,'It\'s OK'),(17,11,4,'It\'s OK'),(17,12,1,'It\'s OK'),(17,13,3,'It\'s OK'),(17,14,1,'Waste of time and money'),(18,1,1,'It\'s OK'),(18,2,1,'It\'s OK'),(18,3,3,'It\'s OK'),(18,4,1,'It\'s OK'),(18,5,1,'It\'s OK'),(18,6,2,'Best purchase of my life!'),(18,7,2,'Best purchase of my life!'),(18,8,1,'Best purchase of my life!'),(18,9,3,'Waste of time and money'),(18,10,1,'Best purchase of my life!'),(18,11,2,'It\'s OK'),(18,12,3,'Best purchase of my life!'),(18,13,4,'Waste of time and money'),(18,14,4,'It\'s OK'),(19,1,2,'Waste of time and money'),(19,2,1,'Waste of time and money'),(19,3,1,'Best purchase of my life!'),(19,4,1,'Waste of time and money'),(19,5,1,'Waste of time and money'),(19,6,3,'It\'s OK'),(19,7,4,'Waste of time and money'),(19,8,3,'Best purchase of my life!'),(19,9,1,'Best purchase of my life!'),(19,10,5,'Waste of time and money'),(19,11,1,'Waste of time and money'),(19,12,4,'Waste of time and money'),(19,13,2,'Waste of time and money'),(19,14,2,'It\'s OK'),(20,1,4,'Best purchase of my life!'),(20,2,4,'Waste of time and money'),(20,3,4,'Best purchase of my life!'),(20,4,5,'It\'s OK'),(20,5,2,'Best purchase of my life!'),(20,6,3,'Best purchase of my life!'),(20,7,4,'It\'s OK'),(20,8,1,'Waste of time and money'),(20,9,5,'Best purchase of my life!'),(20,10,3,'It\'s OK'),(20,11,2,'Waste of time and money'),(20,12,2,'It\'s OK'),(20,13,3,'It\'s OK'),(20,14,3,'Best purchase of my life!');
/*!40000 ALTER TABLE `servicereviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicetemplates`
--

DROP TABLE IF EXISTS `servicetemplates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicetemplates` (
  `idServiceTemplates` int NOT NULL AUTO_INCREMENT,
  `idProvider` int NOT NULL,
  `serviceModality` int DEFAULT NULL,
  `serviceCategory` int DEFAULT NULL,
  `costPerHour` float NOT NULL,
  `description` varchar(320) DEFAULT NULL,
  `serviceName` varchar(45) NOT NULL,
  `templateImageUrl` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`idServiceTemplates`),
  KEY `idProvider_idx` (`idProvider`),
  KEY `category_idx` (`serviceCategory`),
  KEY `modality_idx` (`serviceModality`),
  CONSTRAINT `category` FOREIGN KEY (`serviceCategory`) REFERENCES `servicecategory` (`idServiceCategory`),
  CONSTRAINT `idProvider` FOREIGN KEY (`idProvider`) REFERENCES `user` (`idUser`),
  CONSTRAINT `modality` FOREIGN KEY (`serviceModality`) REFERENCES `servicemodality` (`idServiceModality`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicetemplates`
--

LOCK TABLES `servicetemplates` WRITE;
/*!40000 ALTER TABLE `servicetemplates` DISABLE KEYS */;
INSERT INTO `servicetemplates` VALUES (1,2,2,3,75,'Faço cover com vocal e baixo de qualquer música','Cover','1.png'),(2,2,2,1,35,'Podemos fizer jogando conversa fora sobre qualquer assunto, principalmente música','Conversinha','2.png'),(3,3,2,1,60,'Debater sobre as questões modernas','Discussão sobre a sociedade','3.png'),(4,3,1,5,500,'Não importa o assunto, eu sei tudo','Aula particular','4.png'),(5,3,2,5,2000,'Meu pai é rico, logo eu sei administrar dinheiro','Conselho financeiro','5.png'),(6,4,2,4,40,'','Jogar ARAM','6.png'),(7,4,2,4,70,'','Jogar Ranked','7.png'),(8,4,2,4,90,'','Elojob','8.png'),(9,8,1,2,85,'Vou atrás do alvo e te conto tudo','Stalking','9.png'),(10,8,1,1,55,'A gente enche a cara e xinga minorias','Bebedeira','10.png'),(11,9,1,3,110,'Cuido de tudo, pessoal, limpeza, produtos, o caralho a quatro','Gerenciamento','11.png'),(12,9,1,3,72.99,'Entrevisto seus candidatos de acordo com os critérios estabelecidos','Entrevista de emprego','12.png'),(13,10,1,2,430,'Por choque. Ninguem vai nem ver.','Assassinato','13.png'),(14,10,1,2,300,'Invado ou destruo qualquer sistema','Hacking','14.png');
/*!40000 ALTER TABLE `servicetemplates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `name` varchar(48) DEFAULT NULL,
  `email` varchar(320) NOT NULL,
  `password` varchar(32) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `providingService` tinyint DEFAULT NULL,
  `area` int DEFAULT NULL,
  `profileUrl` varchar(64) DEFAULT NULL,
  `credits` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUser`),
  KEY `area_idx` (`area`),
  CONSTRAINT `area` FOREIGN KEY (`area`) REFERENCES `area` (`idArea`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'MioBestWaifu','Teste@hotmail.com','sexo','M','2005-03-16',NULL,4,'1.png',0),(2,'Akiyama Mio','Mio@hotmail.com','sexo','F','2004-03-16',NULL,1,'2.png',0),(3,'Shinomiya Kaguya','Kaguya@hotmail.com','sexo','F','2003-03-16',NULL,1,'3.png',0),(4,'Morgana','Morgana@hotmail.com','sexo','F','2002-03-16',NULL,3,'4.png',0),(5,'thwumeimba','aaa','babaritibaba','M','2005-03-01',NULL,2,NULL,0),(6,'Momo-chan','joca@gmail.com','joca','F','2005-03-02',NULL,3,'61683240187054.png',0),(7,'jose','jose@yahoo','jose','M','2005-03-04',NULL,4,'71683240306400.png',0),(8,'Loid Forger','Loid@hotmail.com','sexo','M','2023-05-05',NULL,2,'8.png',0),(9,'Amagi','Amagi@hotmail.com','sexo','M','2023-05-05',NULL,1,'9.png',0),(10,'Misaka Mikoto','Misaka@hotmail.com','sexo','F','2023-05-05',NULL,1,'10.png',0),(11,'Albert','Albert@hotmail.com','Albert','M','2023-05-05',NULL,NULL,'11.png',0),(12,'Berotorto','Berotorto@hotmail.com','Berotorto','M','2023-05-05',NULL,NULL,'12.png',0),(13,'Claudio','Claudio@hotmail.com','Claudio','M','2023-05-05',NULL,NULL,'13.png',0),(14,'Douglas','Douglas@hotmail.com','Douglas','M','2023-05-05',NULL,NULL,'14.png',0),(15,'Eduardo','Eduardo@hotmail.com','Eduardo','M','2023-05-05',NULL,NULL,'15.png',0),(16,'Fernando','Fernando@hotmail.com','Fernando','M','2023-05-05',NULL,NULL,'16.png',0),(17,'Gustavo','Gustavo@hotmail.com','Gustavo','M','2023-05-05',NULL,NULL,'17.png',0),(18,'Hilquias','Hilquias@hotmail.com','Hilquias','M','2023-05-05',NULL,NULL,'18.png',0),(19,'Italo','Italo@hotmail.com','Italo','M','2023-05-05',NULL,NULL,'19.png',0),(20,'Jonas','Jonas@hotmail.com','Jonas','M','2023-05-05',NULL,NULL,'20.png',0),(21,'Zacarias','Zacarias@homail.com','Zacarias',NULL,'2005-03-02',NULL,NULL,'0.png',0),(22,'Yan2','2020170020004@ifba.edu.br','Yan2','M','2005-02-17',NULL,2,'0.png',0),(23,'aa','balbla','ss','M','2005-02-03',NULL,3,'0.png',0),(24,'dd','mm','a','F','2005-03-02',NULL,1,'0.png',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userreviews`
--

DROP TABLE IF EXISTS `userreviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userreviews` (
  `idreviewer` int NOT NULL,
  `idtarget` int NOT NULL,
  `score` int NOT NULL,
  `comment` varchar(240) DEFAULT NULL,
  PRIMARY KEY (`idreviewer`,`idtarget`),
  KEY `targetInUserReview_idx` (`idtarget`),
  CONSTRAINT `clientInUserReview` FOREIGN KEY (`idreviewer`) REFERENCES `user` (`idUser`),
  CONSTRAINT `targetInUserReview` FOREIGN KEY (`idtarget`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userreviews`
--

LOCK TABLES `userreviews` WRITE;
/*!40000 ALTER TABLE `userreviews` DISABLE KEYS */;
INSERT INTO `userreviews` VALUES (11,1,5,'Very cool dude, recommended!'),(11,2,2,'Kinda cold, still enjoyable'),(11,3,3,'Kinda cold, still enjoyable'),(11,4,1,'Kinda cold, still enjoyable'),(11,5,4,'Disgusting bitch'),(11,6,1,'Disgusting bitch'),(11,7,3,'Kinda cold, still enjoyable'),(11,8,5,'Very cool dude, recommended!'),(11,9,1,'Kinda cold, still enjoyable'),(11,10,5,'Kinda cold, still enjoyable'),(12,1,3,'Very cool dude, recommended!'),(12,2,3,'Very cool dude, recommended!'),(12,3,2,'Kinda cold, still enjoyable'),(12,4,4,'Kinda cold, still enjoyable'),(12,5,4,'Kinda cold, still enjoyable'),(12,6,2,'Kinda cold, still enjoyable'),(12,7,4,'Kinda cold, still enjoyable'),(12,8,3,'Very cool dude, recommended!'),(12,9,1,'Very cool dude, recommended!'),(12,10,2,'Kinda cold, still enjoyable'),(13,1,5,'Very cool dude, recommended!'),(13,2,2,'Kinda cold, still enjoyable'),(13,3,5,'Disgusting bitch'),(13,4,4,'Very cool dude, recommended!'),(13,5,5,'Kinda cold, still enjoyable'),(13,6,2,'Very cool dude, recommended!'),(13,7,3,'Disgusting bitch'),(13,8,1,'Very cool dude, recommended!'),(13,9,2,'Very cool dude, recommended!'),(13,10,3,'Kinda cold, still enjoyable'),(14,1,4,'Very cool dude, recommended!'),(14,2,4,'Kinda cold, still enjoyable'),(14,3,1,'Disgusting bitch'),(14,4,1,'Disgusting bitch'),(14,5,1,'Kinda cold, still enjoyable'),(14,6,5,'Kinda cold, still enjoyable'),(14,7,2,'Kinda cold, still enjoyable'),(14,8,1,'Disgusting bitch'),(14,9,2,'Disgusting bitch'),(14,10,1,'Disgusting bitch'),(15,1,2,'Very cool dude, recommended!'),(15,2,1,'Kinda cold, still enjoyable'),(15,3,3,'Kinda cold, still enjoyable'),(15,4,4,'Disgusting bitch'),(15,5,2,'Very cool dude, recommended!'),(15,6,2,'Disgusting bitch'),(15,7,1,'Very cool dude, recommended!'),(15,8,1,'Disgusting bitch'),(15,9,2,'Kinda cold, still enjoyable'),(15,10,3,'Very cool dude, recommended!'),(16,1,2,'Very cool dude, recommended!'),(16,2,1,'Very cool dude, recommended!'),(16,3,2,'Kinda cold, still enjoyable'),(16,4,1,'Kinda cold, still enjoyable'),(16,5,1,'Kinda cold, still enjoyable'),(16,6,1,'Disgusting bitch'),(16,7,2,'Kinda cold, still enjoyable'),(16,8,2,'Kinda cold, still enjoyable'),(16,9,1,'Kinda cold, still enjoyable'),(16,10,1,'Very cool dude, recommended!'),(17,1,5,'Kinda cold, still enjoyable'),(17,2,5,'Kinda cold, still enjoyable'),(17,3,2,'Disgusting bitch'),(17,4,4,'Kinda cold, still enjoyable'),(17,5,1,'Disgusting bitch'),(17,6,2,'Disgusting bitch'),(17,7,2,'Kinda cold, still enjoyable'),(17,8,4,'Very cool dude, recommended!'),(17,9,3,'Kinda cold, still enjoyable'),(17,10,1,'Kinda cold, still enjoyable'),(18,1,2,'Very cool dude, recommended!'),(18,2,5,'Very cool dude, recommended!'),(18,3,1,'Disgusting bitch'),(18,4,1,'Very cool dude, recommended!'),(18,5,5,'Kinda cold, still enjoyable'),(18,6,1,'Very cool dude, recommended!'),(18,7,5,'Disgusting bitch'),(18,8,1,'Very cool dude, recommended!'),(18,9,1,'Kinda cold, still enjoyable'),(18,10,1,'Kinda cold, still enjoyable'),(19,1,3,'Disgusting bitch'),(19,2,5,'Kinda cold, still enjoyable'),(19,3,3,'Very cool dude, recommended!'),(19,4,1,'Very cool dude, recommended!'),(19,5,1,'Disgusting bitch'),(19,6,1,'Kinda cold, still enjoyable'),(19,7,5,'Very cool dude, recommended!'),(19,8,1,'Kinda cold, still enjoyable'),(19,9,1,'Very cool dude, recommended!'),(19,10,3,'Disgusting bitch'),(20,1,5,'Disgusting bitch'),(20,2,1,'Disgusting bitch'),(20,3,4,'Very cool dude, recommended!'),(20,4,1,'Disgusting bitch'),(20,5,1,'Disgusting bitch'),(20,6,4,'Kinda cold, still enjoyable'),(20,7,4,'Kinda cold, still enjoyable'),(20,8,4,'Very cool dude, recommended!'),(20,9,2,'Disgusting bitch'),(20,10,2,'Kinda cold, still enjoyable');
/*!40000 ALTER TABLE `userreviews` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-11 21:50:37
