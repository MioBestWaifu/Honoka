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
  `idArea` int NOT NULL,
  PRIMARY KEY (`idArea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicecategory`
--

DROP TABLE IF EXISTS `servicecategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicecategory` (
  `idServiceCategory` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idServiceCategory`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicecategory`
--

LOCK TABLES `servicecategory` WRITE;
/*!40000 ALTER TABLE `servicecategory` DISABLE KEYS */;
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
  `template` int DEFAULT NULL,
  `idClient` int DEFAULT NULL,
  `start` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  PRIMARY KEY (`idServiceInstances`),
  KEY `template_idx` (`template`),
  KEY `client_idx` (`idClient`),
  CONSTRAINT `client` FOREIGN KEY (`idClient`) REFERENCES `user` (`idUser`),
  CONSTRAINT `template` FOREIGN KEY (`template`) REFERENCES `servicetemplates` (`idServiceTemplates`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceinstances`
--

LOCK TABLES `serviceinstances` WRITE;
/*!40000 ALTER TABLE `serviceinstances` DISABLE KEYS */;
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
  PRIMARY KEY (`idServiceModality`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicemodality`
--

LOCK TABLES `servicemodality` WRITE;
/*!40000 ALTER TABLE `servicemodality` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicemodality` ENABLE KEYS */;
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
  PRIMARY KEY (`idServiceTemplates`),
  KEY `idProvider_idx` (`idProvider`),
  KEY `category_idx` (`serviceCategory`),
  KEY `modality_idx` (`serviceModality`),
  CONSTRAINT `category` FOREIGN KEY (`serviceCategory`) REFERENCES `servicecategory` (`idServiceCategory`),
  CONSTRAINT `idProvider` FOREIGN KEY (`idProvider`) REFERENCES `user` (`idUser`),
  CONSTRAINT `modality` FOREIGN KEY (`serviceModality`) REFERENCES `servicemodality` (`idServiceModality`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicetemplates`
--

LOCK TABLES `servicetemplates` WRITE;
/*!40000 ALTER TABLE `servicetemplates` DISABLE KEYS */;
INSERT INTO `servicetemplates` VALUES (1,2,NULL,NULL,75,'Faço cover com vocal e baixo de qualquer música','Cover'),(2,2,NULL,NULL,35,'Podemos fizer jogando conversa fora sobre qualquer assunto, principalmente música','Conversinha'),(3,3,NULL,NULL,60,'Debater sobre as questões modernas','Discussão sobre a sociedade'),(4,3,NULL,NULL,500,'Não importa o assunto, eu sei tudo','Aula particular'),(5,3,NULL,NULL,2000,'Meu pai é rico, logo eu sei administrar dinheiro','Conselho financeiro'),(6,4,NULL,NULL,40,'','Jogar ARAM'),(7,4,NULL,NULL,70,'','Jogar Ranked'),(8,4,NULL,NULL,90,'','Elojob');
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
  `genre` varchar(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `providingService` tinyint DEFAULT NULL,
  `area` int DEFAULT NULL,
  `profileUrl` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  KEY `area_idx` (`area`),
  CONSTRAINT `area` FOREIGN KEY (`area`) REFERENCES `area` (`idArea`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Ninjagermanico','Teste@hotmail.com','sexo','M','2005-03-16',NULL,NULL,'11683147135786.png'),(2,'Akiyama Mio','Mio@hotmail.com','sexo','F','2004-03-16',NULL,NULL,'2.png'),(3,'Shinomiya Kaguya','Kaguya@hotmail.com','sexo','F','2003-03-16',NULL,NULL,'3.png'),(4,'Morgana','Morgana@hotmail.com','sexo','F','2002-03-16',NULL,NULL,'4.png'),(5,'thwumeimba','aaa','babaritibaba',NULL,'2005-03-01',NULL,NULL,NULL),(6,'Momo-chan','joca@gmail.com','joca',NULL,'2005-03-02',NULL,NULL,'61683143039020.png');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-03 21:44:14
