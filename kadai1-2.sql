CREATE DATABASE  IF NOT EXISTS `kadai1_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `kadai1_db`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: kadai1_db
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `meats`
--

DROP TABLE IF EXISTS `meats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meats`
--

LOCK TABLES `meats` WRITE;
/*!40000 ALTER TABLE `meats` DISABLE KEYS */;
INSERT INTO `meats` VALUES (1,'チキン',NULL),(2,'ポーク','豚肉の塊肉を煮込み裂いたもの'),(3,'ビーフ','牛肉をグリルしてスライスしたもの'),(4,'ベジタリアン',NULL);
/*!40000 ALTER TABLE `meats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'ブリトーラップ',1600),(2,'ブリトーボウル',1650);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `id` int NOT NULL AUTO_INCREMENT,
  `users_id` int DEFAULT NULL,
  `products_id` int DEFAULT NULL,
  `meats_id` int DEFAULT NULL,
  `pickup_time` datetime DEFAULT NULL,
  `purchased_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `users_id` (`users_id`),
  KEY `products_id` (`products_id`),
  KEY `meats_id` (`meats_id`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`),
  CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`products_id`) REFERENCES `products` (`id`),
  CONSTRAINT `purchase_ibfk_3` FOREIGN KEY (`meats_id`) REFERENCES `meats` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,9,2,1,'2024-10-18 10:00:00','2024-10-18 14:08:19'),(2,9,1,3,'2024-10-22 10:00:00','2024-10-22 08:44:48'),(3,9,1,3,'2024-10-23 17:30:00','2024-10-23 14:39:59'),(4,9,2,3,'2024-10-23 19:00:00','2024-10-23 14:48:46'),(5,9,1,3,'2024-10-23 13:00:00','2024-10-23 15:02:49'),(6,9,2,3,'2024-10-24 19:00:00','2024-10-24 09:51:14'),(7,9,2,3,'2024-10-24 14:00:00','2024-10-24 13:06:55'),(8,9,2,3,'2024-10-24 14:00:00','2024-10-24 13:10:24'),(9,9,2,3,'2024-10-24 14:00:00','2024-10-24 13:13:13'),(10,9,2,3,'2024-10-24 14:00:00','2024-10-24 13:13:20'),(11,9,2,3,'2024-10-24 14:00:00','2024-10-24 13:13:26'),(12,9,2,3,'2024-10-24 14:00:00','2024-10-24 13:13:29'),(13,9,2,3,'2024-10-24 14:00:00','2024-10-24 13:16:09'),(14,9,2,3,'2024-10-24 14:00:00','2024-10-24 13:16:14'),(15,9,2,3,'2024-10-24 18:00:00','2024-10-24 13:22:56'),(16,9,1,3,'2024-10-24 18:30:00','2024-10-24 13:50:15'),(17,9,1,1,'2024-10-25 15:30:00','2024-10-25 09:03:13'),(18,9,1,2,'2024-10-29 13:00:00','2024-10-29 08:44:19'),(19,9,1,3,'2024-10-30 14:00:00','2024-10-30 12:54:09');
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_sauces`
--

DROP TABLE IF EXISTS `purchase_sauces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_sauces` (
  `purchase_id` int DEFAULT NULL,
  `sauce_id` int DEFAULT NULL,
  KEY `purchase_id` (`purchase_id`),
  KEY `sauce_id` (`sauce_id`),
  CONSTRAINT `purchase_sauces_ibfk_1` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`),
  CONSTRAINT `purchase_sauces_ibfk_2` FOREIGN KEY (`sauce_id`) REFERENCES `sauce` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_sauces`
--

LOCK TABLES `purchase_sauces` WRITE;
/*!40000 ALTER TABLE `purchase_sauces` DISABLE KEYS */;
INSERT INTO `purchase_sauces` VALUES (1,3),(1,4),(1,5),(2,1),(2,3),(2,4),(3,1),(3,3),(3,4),(4,4),(4,5),(5,1),(5,3),(5,4),(6,4),(6,5),(7,3),(7,4),(7,5),(8,3),(8,4),(8,5),(9,3),(9,4),(9,5),(10,3),(10,4),(10,5),(11,3),(11,4),(11,5),(12,3),(12,4),(12,5),(13,3),(13,4),(13,5),(14,3),(14,4),(14,5),(15,3),(15,4),(15,5),(16,1),(16,2),(16,3),(17,1),(17,2),(17,3),(18,1),(18,2),(18,3),(19,1),(19,2),(19,3);
/*!40000 ALTER TABLE `purchase_sauces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_vegetables`
--

DROP TABLE IF EXISTS `purchase_vegetables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_vegetables` (
  `purchase_id` int DEFAULT NULL,
  `vegetables_id` int DEFAULT NULL,
  KEY `purchase_id` (`purchase_id`),
  KEY `vegetables_id` (`vegetables_id`),
  CONSTRAINT `purchase_vegetables_ibfk_1` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`),
  CONSTRAINT `purchase_vegetables_ibfk_2` FOREIGN KEY (`vegetables_id`) REFERENCES `vegetables` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_vegetables`
--

LOCK TABLES `purchase_vegetables` WRITE;
/*!40000 ALTER TABLE `purchase_vegetables` DISABLE KEYS */;
INSERT INTO `purchase_vegetables` VALUES (1,3),(1,4),(1,5),(2,1),(2,2),(2,3),(3,1),(3,2),(3,3),(4,3),(4,4),(4,5),(5,1),(5,2),(5,3),(6,3),(6,4),(6,5),(7,2),(7,3),(7,4),(8,2),(8,3),(8,4),(9,2),(9,3),(9,4),(10,2),(10,3),(10,4),(11,2),(11,3),(11,4),(12,2),(12,3),(12,4),(13,2),(13,3),(13,4),(14,2),(14,3),(14,4),(15,2),(15,3),(15,4),(16,2),(16,3),(16,4),(17,3),(17,4),(17,5),(18,3),(18,4),(18,5),(19,3),(19,4),(19,5);
/*!40000 ALTER TABLE `purchase_vegetables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sauce`
--

DROP TABLE IF EXISTS `sauce`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sauce` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sauce`
--

LOCK TABLES `sauce` WRITE;
/*!40000 ALTER TABLE `sauce` DISABLE KEYS */;
INSERT INTO `sauce` VALUES (1,'サルサ・ロハ','トマトを主体に、唐辛子、 コリアンダーから作る'),(2,'チーズ',NULL),(3,'サワークリーム',NULL),(4,'マヨネーズ',NULL),(5,'ワカモレ','アボカドをつぶしてピューレ状にし、スパイシーに味つけ');
/*!40000 ALTER TABLE `sauce` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `tel` varchar(12) NOT NULL,
  `email` varchar(45) NOT NULL,
  `login_id` varchar(10) NOT NULL,
  `login_pass` char(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tel_UNIQUE` (`tel`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'鈴木','090-98-7654','suzuki@example.com','suzuki','$2a$08$kmfxIv29s46IvKC5zQY1vuQAWK7XtxuhPrTVs9pLVq5bn757eV2Q6'),(2,'山田 太郎','090-123-5678','taro.yamada@example.com','yamada','$2a$08$PFaR4tgy17L/6nA/JRP4EOBL01uKC.3hk21XMK5g.xdDIbmkaC6zK'),(3,'佐藤 花子','080-234-6789','hanako.sato@example.com','',''),(4,'鈴木 一郎','070-345-7890','ichiro.suzuki@example.com','',''),(5,'田中 美咲','090-456-8901','misaki.tanaka@example.com','',''),(6,'高橋 健二','080-567-9012','kenji.takahashi@example.com','',''),(7,'鹿島','070223356','sknfwibnv@gmail.com','kashima','$2a$08$qxvinZ6W5B8C8FWvSnHN/eL7CIx5Y/9DSi8AnIuF.hDpwy9aseBpK'),(8,'å¯æ¨«','0903333333','togashi@gmail.com','togashi','$2a$10$.5E2n7/lwsdG2E50YzlS4OUh5nZaRnHtXtqNM3dcZuOtubOKqm4ei'),(9,'aaa','090000000','aaa@gmail.com','aaa','$2a$10$xG5PQjLvyUqjmr1i.2vMKeJQz3iM5OSUJV05sSjSTk5ma0Ctp09Xu'),(10,'bbb','080000000','bbb@gmail.com','bbb','$2a$10$W/FbxNjFpJMhSmWIRyQpO.FNaCCUQyba/ceftz7Oz5HhhIDipu8Pa'),(11,'','','','','$2a$10$xQ3DrE7u1He17MWvYDvhY.ksZJKhDNMcu8p61toGqElLfS4Gr4156'),(16,'ccc ccc','0501112222','ccc@gmail.com','ccc ccc','$2a$10$S8Gu/ah.xv7fhCmqPTN9mOlKKeKKyRDC5JTPSG./Eh.jtg2oIgmY.'),(19,'小倉　ねね','0503334444','nene@gmail.com','nene1','$2a$10$i5toUhHkRd.CBQo3hXRTxei.WRnfaJPbvELuSn/geomVF7p55xUbG'),(20,'一ツ柳','0709998888','hitotsuyanagi@mail.com','1yanagi','$2a$10$VqCb8IDUmM2lKExBCaEhaeKqY2zeqJJKHRGu1C185UxOp3qAF9r4.'),(21,'田沼','0809998888','tanuma@gmail.com','tanuma1','$2a$10$re58DeYcw31g3ZOE0.bzNu/BXr7VpNlGN5taO.yMq5AZsG6DBPf3e'),(22,'江戸川','0509998888','edogawa@gmail.com','edogawa','$2a$10$dit6xDI2m3w5jz0aXKON/OxlvY3Qk4GlH9g2NK4P8VY.M.o1M2xKi'),(27,'柏木','0808887777','kashiwagi@gmail.com','kashiwagi','$2a$10$rtGr5bl//3WOoszIb525ye2KRHaC8oSDIVzm9gEenu352x8nlx/aO'),(28,'rose','0509874561','rose@gmail.com','roserose','$2a$10$Pkrb8E2EilsEpgD9dB35y.myqL55gfaLZgmCTolxDIM/vmMr1krzW');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vegetables`
--

DROP TABLE IF EXISTS `vegetables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vegetables` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vegetables`
--

LOCK TABLES `vegetables` WRITE;
/*!40000 ALTER TABLE `vegetables` DISABLE KEYS */;
INSERT INTO `vegetables` VALUES (1,'レタス',NULL),(2,'紫キャベツ',NULL),(3,'赤インゲン',NULL),(4,'ひよこ豆',NULL),(5,'アボカド',NULL);
/*!40000 ALTER TABLE `vegetables` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-06 15:37:42
