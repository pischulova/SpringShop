CREATE DATABASE  IF NOT EXISTS `spring_shop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `spring_shop`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: spring_shop
-- ------------------------------------------------------
-- Server version	5.6.22-log

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
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `countries` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nameEn` varchar(20) NOT NULL,
  `nameRu` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'Sweden','Швеция'),(2,'Great Britain','Великобритания'),(3,'Sri Lanka','Шри-Ланка'),(4,'Greece','Греция'),(5,'Germany','Германия'),(6,'France','Франция'),(7,'United States','США'),(8,'Italy','Италия'),(9,'Netherlands','Нидерланды'),(10,'Norway','Норвегия');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flights`
--

DROP TABLE IF EXISTS `flights`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flights` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `isDisabled` bit(1) DEFAULT NULL,
  `nameEn` varchar(255) DEFAULT NULL,
  `nameRu` varchar(255) DEFAULT NULL,
  `price` bigint(20) DEFAULT NULL,
  `country_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flights`
--

LOCK TABLES `flights` WRITE;
/*!40000 ALTER TABLE `flights` DISABLE KEYS */;
INSERT INTO `flights` VALUES (1,'\0','flight0','авиабилет0',7412,4),(2,'\0','flight1','авиабилет1',5177,5),(3,'\0','flight2','авиабилет2',5820,2),(4,'\0','flight3','авиабилет3',5058,2),(5,'\0','flight4','авиабилет4',7424,8),(6,'\0','flight5','авиабилет5',9841,7),(7,'\0','flight6','авиабилет6',5719,4),(8,'\0','flight7','авиабилет7',3124,9),(9,'\0','flight8','авиабилет8',9860,6),(10,'','flight9','авиабилет9',7405,8),(11,'\0','flight10','авиабилет10',2942,1),(12,'\0','flight11','авиабилет11',8527,10),(13,'\0','flight12','авиабилет12',3332,7),(14,'\0','flight13','авиабилет13',5440,1),(15,'\0','flight14','авиабилет14',2650,3),(16,'\0','flight15','авиабилет15',6091,3),(17,'\0','flight16','авиабилет16',7031,10),(18,'\0','flight17','авиабилет17',5429,9),(19,'\0','flight18','авиабилет18',7541,6),(20,'\0','flight19','авиабилет19',6853,6),(21,'\0','flight20','авиабилет20',7126,3),(22,'\0','flight21','авиабилет21',8108,6),(23,'\0','flight22','авиабилет22',3256,4),(24,'\0','flight23','авиабилет23',6474,1),(25,'\0','flight24','авиабилет24',3382,10),(26,'\0','flight25','авиабилет25',6266,10),(27,'\0','flight26','авиабилет26',6651,5),(28,'\0','flight27','авиабилет27',6009,6),(29,'\0','flight28','авиабилет28',3766,8),(30,'\0','flight29','авиабилет29',3307,6),(31,'\0','flight30','авиабилет30',3686,10),(32,'\0','flight31','авиабилет31',6318,2),(33,'\0','flight32','авиабилет32',2829,2),(34,'\0','flight33','авиабилет33',9832,6),(35,'\0','flight34','авиабилет34',5739,10),(36,'\0','flight35','авиабилет35',9401,7),(37,'\0','flight36','авиабилет36',5641,10),(38,'\0','flight37','авиабилет37',4539,6),(39,'\0','flight38','авиабилет38',7112,7),(40,'\0','flight39','авиабилет39',5933,4),(41,'\0','flight40','авиабилет40',9064,1),(42,'\0','flight41','авиабилет41',7723,3),(43,'\0','flight42','авиабилет42',4517,4),(44,'\0','flight43','авиабилет43',2290,5),(45,'\0','flight44','авиабилет44',6144,8),(46,'\0','flight45','авиабилет45',9642,7),(47,'\0','flight46','авиабилет46',9316,8),(48,'\0','flight47','авиабилет47',6333,3),(49,'\0','flight48','авиабилет48',9400,2),(50,'\0','flight49','авиабилет49',2041,7),(51,'\0','flight50','авиабилет50',9304,7),(52,'\0','flight51','авиабилет51',5245,10),(53,'\0','flight52','авиабилет52',2789,9),(54,'\0','flight53','авиабилет53',3127,1),(55,'\0','flight54','авиабилет54',6810,6),(56,'\0','flight55','авиабилет55',2697,10),(57,'\0','flight56','авиабилет56',6148,2),(58,'\0','flight57','авиабилет57',8242,7),(59,'\0','flight58','авиабилет58',5904,1),(60,'\0','flight59','авиабилет59',8623,6),(61,'\0','flight60','авиабилет60',5853,5),(62,'\0','flight61','авиабилет61',8552,3),(63,'\0','flight62','авиабилет62',7485,2),(64,'\0','flight63','авиабилет63',4876,2),(65,'\0','flight64','авиабилет64',3528,8),(66,'\0','flight65','авиабилет65',6012,9),(67,'\0','flight66','авиабилет66',9030,9),(68,'\0','flight67','авиабилет67',8081,3),(69,'\0','flight68','авиабилет68',4545,5),(70,'\0','flight69','авиабилет69',2901,8),(71,'\0','flight70','авиабилет70',7795,4),(72,'\0','flight71','авиабилет71',8912,7),(73,'\0','flight72','авиабилет72',2107,4),(74,'\0','flight73','авиабилет73',2186,3),(75,'\0','flight74','авиабилет74',8243,1),(76,'\0','flight75','авиабилет75',9127,3),(77,'\0','flight76','авиабилет76',6717,3),(78,'\0','flight77','авиабилет77',9030,5),(79,'\0','flight78','авиабилет78',4471,2),(80,'\0','flight79','авиабилет79',3606,8),(81,'\0','flight80','авиабилет80',4769,8),(82,'\0','flight81','авиабилет81',9689,7),(83,'\0','flight82','авиабилет82',6439,7),(84,'\0','flight83','авиабилет83',4693,10),(85,'\0','flight84','авиабилет84',4097,3),(86,'\0','flight85','авиабилет85',2112,9),(87,'\0','flight86','авиабилет86',4760,6),(88,'\0','flight87','авиабилет87',8112,3),(89,'\0','flight88','авиабилет88',6217,5),(90,'\0','flight89','авиабилет89',2506,4),(91,'\0','flight90','авиабилет90',2851,8),(92,'\0','flight91','авиабилет91',9744,6),(93,'\0','flight92','авиабилет92',3062,7),(94,'\0','flight93','авиабилет93',2141,10),(95,'\0','flight94','авиабилет94',7208,8),(96,'\0','flight95','авиабилет95',8551,10),(97,'\0','flight96','авиабилет96',2007,2),(98,'\0','flight97','авиабилет97',2701,5),(99,'\0','flight98','авиабилет98',4599,8),(100,'\0','flight99','авиабилет99',4912,6);
/*!40000 ALTER TABLE `flights` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_items` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `flight_id` bigint(20) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_qk00gwm93wm2xkrqfinbi8y03` (`flight_id`),
  KEY `FK_9gap2fmw66v092ntb58rtohwh` (`order_id`),
  CONSTRAINT `FK_9gap2fmw66v092ntb58rtohwh` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `FK_qk00gwm93wm2xkrqfinbi8y03` FOREIGN KEY (`flight_id`) REFERENCES `flights` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (7,9860,1,9,5),(8,7405,1,10,6),(9,9642,1,46,6),(10,9841,1,6,7),(11,6474,1,24,7),(12,9841,1,6,7),(13,5058,1,4,8),(14,3124,1,8,8),(15,2942,1,11,8),(16,3382,1,25,9),(17,9860,1,9,9),(18,6144,1,45,9),(19,8551,1,96,10),(20,5058,1,4,10),(21,5933,1,40,10),(22,5719,1,7,11),(23,6266,1,26,11),(24,6266,1,26,12),(25,9401,1,36,13),(26,4539,1,38,13),(27,5820,1,3,14),(28,3124,1,8,15),(29,7112,1,39,16),(30,9860,1,9,16),(31,9304,1,51,17),(32,4517,1,43,17),(33,9860,1,9,18),(34,7424,1,5,19),(35,9304,1,51,20),(36,9064,1,41,20),(37,5429,1,18,21),(38,9841,1,6,21),(39,2901,1,70,21),(40,9642,1,46,21),(41,4769,1,81,22),(42,3127,1,54,22),(43,9127,1,76,22),(44,6318,1,32,23),(45,5719,1,7,24),(46,9316,1,47,24),(47,2942,1,11,25),(48,5177,1,2,26),(49,5177,1,2,26),(50,7412,2,1,27);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `isApproved` bit(1) DEFAULT NULL,
  `sum` bigint(20) DEFAULT NULL,
  `client_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ktwyfbqs32h2qw22odq9pqmex` (`client_id`),
  CONSTRAINT `FK_ktwyfbqs32h2qw22odq9pqmex` FOREIGN KEY (`client_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (5,'2015-05-18','\0',9860,125),(6,'2015-05-18','',17047,125),(7,'2015-05-18','\0',26156,250),(8,'2015-05-19','\0',11124,252),(9,'2015-05-20','\0',19386,125),(10,'2015-05-20','\0',19542,125),(11,'2015-05-20','\0',11985,125),(12,'2015-05-20','\0',6266,125),(13,'2015-05-20','\0',13940,125),(14,'2015-05-20','\0',5820,127),(15,'2015-05-20','\0',3124,127),(16,'2015-05-20','\0',16972,127),(17,'2015-05-20','\0',13821,127),(18,'2015-05-20','\0',9860,127),(19,'2015-05-20','\0',7424,126),(20,'2015-05-20','\0',18368,126),(21,'2015-05-20','\0',27813,126),(22,'2015-05-20','\0',17023,126),(23,'2015-05-20','\0',6318,126),(24,'2015-05-20','\0',15035,130),(25,'2015-05-20','\0',2942,130),(26,'2015-05-20','\0',10354,125),(27,'2015-05-20','\0',14824,125);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `isBad` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `userRole` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (125,'us0@c.com','\0','name0','f0dbfa8b071a78f4fc74a528db0f88fb465b908f','0111111111','CLIENT','client0'),(126,'us1@c.com','\0','name1','35a190dd651284cc87350f990ec37bab9f2fc177','0111111111','CLIENT','client1'),(127,'us2@c.com','\0','name2','4f545b53b129777900f96826a5f8d390202d2601','0111111111','CLIENT','client2'),(128,'us3@c.com','','name3','4884f3f2975d24156fc997e0763bca3d4b71055b','0111111111','CLIENT','client3'),(129,'us4@c.com','\0','name4','673074f5a221a5174003d9d72a1926ca398bd9b1','0111111111','CLIENT','client4'),(130,'us5@c.com','\0','name5','818ea4360588f2c0ad843b5fbd718706d83f3424','0111111111','CLIENT','client5'),(131,'us6@c.com','\0','name6','90804d3d1f8fb6a7df3cdf77dbef13e147e2aeb6','0111111111','CLIENT','client6'),(132,'us7@c.com','\0','name7','e944ba63956806aa5c06f2f41be2f28aba7e2b37','0111111111','CLIENT','client7'),(133,'us8@c.com','','name8','443aeb2d857b7e90d374d955b48d430215da1b3f','0111111111','CLIENT','client8'),(134,'us9@c.com','\0','name9','9c36628df354d7ed4a05c90c2be2ba63c98f1347','0111111111','CLIENT','client9'),(135,'us10@c.com','\0','name10','1c0ef2bcbc844929170442dd4dc97f203d88e509','0111111111','CLIENT','client10'),(136,'us11@c.com','\0','name11','09491fced692a4d9c5dd034234a08324dad6c7b4','0111111111','CLIENT','client11'),(137,'us12@c.com','\0','name12','5992eca46f4e6ee24a75200f84e7e62d8c13dcbe','0111111111','CLIENT','client12'),(138,'us13@c.com','\0','name13','791ba0d8e2746e4a7a6f93184b21869e27fdd54f','0111111111','CLIENT','client13'),(139,'us14@c.com','\0','name14','7985d802f0e907223ad875c5ee1d132b06df832d','0111111111','CLIENT','client14'),(140,'us15@c.com','\0','name15','cf63800b375c91499e11168093522252ab705055','0111111111','CLIENT','client15'),(141,'us16@c.com','\0','name16','2bb610468165d3f7b60d464c0a784aff85ddcbad','0111111111','CLIENT','client16'),(142,'us17@c.com','\0','name17','c4ec1fcb768315b2286cd554c0b45b9d2f2f62d8','0111111111','CLIENT','client17'),(143,'us18@c.com','\0','name18','2821ec280546012d70f92de609da0a7b0f7df843','0111111111','CLIENT','client18'),(144,'us19@c.com','\0','name19','fa33ac5f02c8e81ca95d5115618d45ecc43533a4','0111111111','CLIENT','client19'),(145,'us20@c.com','\0','name20','f62db10b4fd3f92e90764e8df5a388999f9d6a91','0111111111','CLIENT','client20'),(146,'us21@c.com','\0','name21','c38a82d098139947839b80a1d75d32fd18830794','0111111111','CLIENT','client21'),(147,'us22@c.com','\0','name22','78b3cb6631f816e6db9125a063a48815392bdcf2','0111111111','CLIENT','client22'),(148,'us23@c.com','\0','name23','9065e322f06ee164fae688dc94834e7751715a28','0111111111','CLIENT','client23'),(149,'us24@c.com','\0','name24','9e0b8fc51c9edb0baedd3c1371d94af4eddee9a1','0111111111','CLIENT','client24'),(150,'us25@c.com','\0','name25','4084b36c3df20776abafe99245fd15db5446c74c','0111111111','CLIENT','client25'),(151,'us26@c.com','\0','name26','d8e2d8e39f224d6d3b8be8b1c5964274fe331f24','0111111111','CLIENT','client26'),(152,'us27@c.com','\0','name27','e072acaf0240bd4933c430cff8b930d04378efa6','0111111111','CLIENT','client27'),(153,'us28@c.com','\0','name28','e798ecb297b66fadfb23bef3b17613515063427d','0111111111','CLIENT','client28'),(154,'us29@c.com','\0','name29','ec4b781cfb85e3726117ee0e21511fd801057608','0111111111','CLIENT','client29'),(155,'us30@c.com','\0','name30','4130930e944e52a5a7a7b12626212fd6dfc9e128','0111111111','CLIENT','client30'),(156,'us31@c.com','\0','name31','94f14b8302a417f9c16d504f19f69ff50dd848c4','0111111111','CLIENT','client31'),(157,'us32@c.com','\0','name32','5859a477b6a6248a7176999dc5988c4f8d515c76','0111111111','CLIENT','client32'),(158,'us33@c.com','\0','name33','0453a0c87035a21e2cd751bb9ad93aa853ab4be7','0111111111','CLIENT','client33'),(159,'us34@c.com','\0','name34','6fc9a999ed5d65752cc36748589608457efabf14','0111111111','CLIENT','client34'),(160,'us35@c.com','\0','name35','576a2c325b6ddaa3e73fc90a6cfff7b1506d84bc','0111111111','CLIENT','client35'),(161,'us36@c.com','\0','name36','396286c47a55dd5cde14992e190eae6e84f52976','0111111111','CLIENT','client36'),(162,'us37@c.com','\0','name37','bc1d10cab666ca1d0a8350716a67a6e4892b0e2a','0111111111','CLIENT','client37'),(163,'us38@c.com','\0','name38','82ab8d0332657a8dac3e5f9f56859683e84741d3','0111111111','CLIENT','client38'),(164,'us39@c.com','\0','name39','21a3c3c54676d373ea6858b154154f60e657d2e5','0111111111','CLIENT','client39'),(165,'us40@c.com','\0','name40','a17b5edf83ad5ad3c12910342f2c49659f9941bd','0111111111','CLIENT','client40'),(166,'us41@c.com','\0','name41','3c9dea277df79c2d94beb2a1e5fac3ca50255301','0111111111','CLIENT','client41'),(167,'us42@c.com','\0','name42','4cf10e5e82a9f16adb134975bb258498aa72ca30','0111111111','CLIENT','client42'),(168,'us43@c.com','\0','name43','088d1a5e11f7e04c0e65a2cb1c1645eb92ce86c8','0111111111','CLIENT','client43'),(169,'us44@c.com','\0','name44','e3b19edeacfaa3b10e0672708eb85b56af361c33','0111111111','CLIENT','client44'),(170,'us45@c.com','\0','name45','9b773258e97f72423dc7520e3cf52e891d4a74a7','0111111111','CLIENT','client45'),(171,'us46@c.com','\0','name46','7d5c302e97103dbae632f695dc4f80bc15a5e24e','0111111111','CLIENT','client46'),(172,'us47@c.com','\0','name47','1798ba8a31f018e350a52a46e49aa8f25ae29e73','0111111111','CLIENT','client47'),(173,'us48@c.com','\0','name48','7c88c5b4176da5087a30da933f79fc0569087467','0111111111','CLIENT','client48'),(174,'us49@c.com','\0','name49','7abbf4f9b7a09650d8694f94b997e69e3fb06fec','0111111111','CLIENT','client49'),(175,'us50@c.com','\0','name50','58acebb10750887bbbf071f30b38c01f0c1b5cff','0111111111','CLIENT','client50'),(176,'us51@c.com','\0','name51','c887e293816c8c9bb2ebb5e65899f34b588d7147','0111111111','CLIENT','client51'),(177,'us52@c.com','\0','name52','87c484ac23d80d2111770460b3673bbba7c248ba','0111111111','CLIENT','client52'),(178,'us53@c.com','\0','name53','82c40aa3207f8133877d0339c45061bcee3dad92','0111111111','CLIENT','client53'),(179,'us54@c.com','\0','name54','a7feb1e1bf1f3e55828c1cab614473142a8361eb','0111111111','CLIENT','client54'),(180,'us55@c.com','\0','name55','23e9cf9bf95a8bf21300820bcabb96737362d851','0111111111','CLIENT','client55'),(181,'us56@c.com','\0','name56','43219d096d9c386993fd50d6d105a8b3532a19b2','0111111111','CLIENT','client56'),(182,'us57@c.com','\0','name57','7698b36f6b343178061dae5b9433a168a0af9250','0111111111','CLIENT','client57'),(183,'us58@c.com','\0','name58','03369ba993b8bc7c6a873cf1087f6a1bb57354d1','0111111111','CLIENT','client58'),(184,'us59@c.com','\0','name59','78da02770dec59c447ce43ee7c14d84a2df7bce1','0111111111','CLIENT','client59'),(185,'us60@c.com','\0','name60','ffc42f41826e04bd5b93883b98bb370a34669c71','0111111111','CLIENT','client60'),(186,'us61@c.com','\0','name61','837337b97a51494d566fa10104ca1d5a12112ab1','0111111111','CLIENT','client61'),(187,'us62@c.com','\0','name62','405d4111e2ce60ebdc6b0796ac85a43a33a3fb28','0111111111','CLIENT','client62'),(188,'us63@c.com','\0','name63','528ff531f7c2f204fd006f0bbe68d3084e1b56b7','0111111111','CLIENT','client63'),(189,'us64@c.com','\0','name64','eed9890f6e01814a3ccf60937a0dd28dcdf717dc','0111111111','CLIENT','client64'),(190,'us65@c.com','\0','name65','aaf94db987a413a567f767b3fb1d2dffebeed201','0111111111','CLIENT','client65'),(191,'us66@c.com','\0','name66','62743fc66ad4e045521501995ae429c09c10eade','0111111111','CLIENT','client66'),(192,'us67@c.com','\0','name67','5f6e222540284379455618effb4f85e5063083cd','0111111111','CLIENT','client67'),(193,'us68@c.com','\0','name68','5d3ebf937767d32fbbae575d98f3c674ad19ef0d','0111111111','CLIENT','client68'),(194,'us69@c.com','\0','name69','b95502c1a26ed4d90ba2bbcb89a1c9c61917dac9','0111111111','CLIENT','client69'),(195,'us70@c.com','\0','name70','9f65775508b77eb4cca7d270792c24649508e9da','0111111111','CLIENT','client70'),(196,'us71@c.com','\0','name71','7a89ea5daee68d62950d55c7864c91bb16d474e0','0111111111','CLIENT','client71'),(197,'us72@c.com','\0','name72','432216cbe703bfdb1745c01afefdd30df5094f4f','0111111111','CLIENT','client72'),(198,'us73@c.com','\0','name73','d4837e4e58122bf9387ac5be1645e9bc5619da11','0111111111','CLIENT','client73'),(199,'us74@c.com','\0','name74','af8820b1243e6bc3d551927a6f0174e77fd23ac7','0111111111','CLIENT','client74'),(200,'us75@c.com','\0','name75','1e8cada26349c2a3b28fa80e12b57c825dfa1c49','0111111111','CLIENT','client75'),(201,'us76@c.com','\0','name76','46a29542bc12a22857af30226aa3e5ca32baee95','0111111111','CLIENT','client76'),(202,'us77@c.com','\0','name77','186a770d6b79e6ac5167e3df9cc32f522facaca8','0111111111','CLIENT','client77'),(203,'us78@c.com','\0','name78','fbdaffaa52f360b419fc17787ece89ad2975bab5','0111111111','CLIENT','client78'),(204,'us79@c.com','\0','name79','e841d9d14e48dd56f58afd6901da19532d44f43e','0111111111','CLIENT','client79'),(205,'us80@c.com','\0','name80','815f7712c8639a58d7f0db78bac4456d64b299d8','0111111111','CLIENT','client80'),(206,'us81@c.com','\0','name81','897eea52f7a56567ad298925abda210aab6e7556','0111111111','CLIENT','client81'),(207,'us82@c.com','\0','name82','d023748637444f317d3749dbde30206c7b1737a0','0111111111','CLIENT','client82'),(208,'us83@c.com','\0','name83','e438d8d9406ebdc692a72ac5d7d9dc5c7630559f','0111111111','CLIENT','client83'),(209,'us84@c.com','\0','name84','865639ae817b58551fd591dbf961e629cf424245','0111111111','CLIENT','client84'),(210,'us85@c.com','\0','name85','9b29b649ed4031dee202fc7a888baa040871c1f7','0111111111','CLIENT','client85'),(211,'us86@c.com','\0','name86','ae3bb187a00b271aeef18e0033d92d3cb26ffab5','0111111111','CLIENT','client86'),(212,'us87@c.com','\0','name87','0b89c8b4a66344ee3e5e2192b620d1bac8456a40','0111111111','CLIENT','client87'),(213,'us88@c.com','\0','name88','f6be85d9cb492c2dd79b3099db06f128fe8878b7','0111111111','CLIENT','client88'),(214,'us89@c.com','\0','name89','88ea2cce950941342c223c7af562e7a9771d1d11','0111111111','CLIENT','client89'),(215,'us90@c.com','\0','name90','be832e39fc2750679627e68e21d4f32ef708ec9b','0111111111','CLIENT','client90'),(216,'us91@c.com','\0','name91','0c2d651f3a1c857aec2003562e6414e7078f0e80','0111111111','CLIENT','client91'),(217,'us92@c.com','\0','name92','1a43d4cd13979f27298f39f35df4a1d71bdb5362','0111111111','CLIENT','client92'),(218,'us93@c.com','\0','name93','01fce934a78bd0b9a6b5879e13aef63494bf2e71','0111111111','CLIENT','client93'),(219,'us94@c.com','\0','name94','03badde2eba0f01b2955900699bf62e9cca32aa5','0111111111','CLIENT','client94'),(220,'us95@c.com','\0','name95','6fa67d15fcc8a546cdbc8288df6b763ebb9c358a','0111111111','CLIENT','client95'),(221,'us96@c.com','\0','name96','16154ef09ebf482ee5b1b9166acaef755fee2189','0111111111','CLIENT','client96'),(222,'us97@c.com','\0','name97','6edb8185fc9ff889f397a8fa909ed9b2c68b2bd8','0111111111','CLIENT','client97'),(223,'us98@c.com','\0','name98','6abb99d17a6620f3422aec6321fc4460bf3025b2','0111111111','CLIENT','client98'),(224,'us99@c.com','\0','name99','fa0e379c1f6ffd201b1fd45f58c8fb8bfe4414c0','0111111111','CLIENT','client99'),(225,'adm0@a.com','\0','name0','fd3cd19b823ba14f32e4c89c5bd1ab38f0528cd2','0999999999','ADMIN','admin0'),(226,'adm1@a.com','\0','name1','447e7b13a6d7fc6823821db2da4544a439edaa39','0999999999','ADMIN','admin1'),(227,'adm2@a.com','\0','name2','47a78ed3492d3132def5c15f4ba4ac4eb9f7964e','0999999999','ADMIN','admin2'),(228,'adm3@a.com','\0','name3','871452876c56853a245bbd509b0ffe09f3fe39bf','0999999999','ADMIN','admin3'),(229,'adm4@a.com','\0','name4','161389147dadac1874bf89c9caf4c4f047c83604','0999999999','ADMIN','admin4'),(230,'adm5@a.com','\0','name5','c46b40ae896384b1223446b02ad1ee5cf7408cfa','0999999999','ADMIN','admin5'),(231,'adm6@a.com','\0','name6','45a7c9c0585fb77c254a42f2ffa4206d7f97fe29','0999999999','ADMIN','admin6'),(232,'adm7@a.com','\0','name7','415563d9acebe376e186d9e1b34c80419e1b756f','0999999999','ADMIN','admin7'),(233,'adm8@a.com','\0','name8','eed4ea9d8e03d405256421d4e037371ac281e29e','0999999999','ADMIN','admin8'),(234,'adm9@a.com','\0','name9','02a0022a196e23d3b73a9b27233ca64cc6845932','0999999999','ADMIN','admin9'),(235,'adm10@a.com','\0','name10','c8af3529d3d9d14a7da0742c2ccb763f6f58820a','0999999999','ADMIN','admin10'),(236,'adm11@a.com','\0','name11','4f51f9d46d7b1b6c07218f6d9ccfd090f98fcd93','0999999999','ADMIN','admin11'),(237,'adm12@a.com','\0','name12','83a2a4cadba02f0cbee14a3ba6fa1a87a6566b2c','0999999999','ADMIN','admin12'),(238,'adm13@a.com','\0','name13','dd9151e066ae8c2975b3db44caff52cc5577e23f','0999999999','ADMIN','admin13'),(239,'adm14@a.com','\0','name14','8ce562dfb80404c82c4f3faf3955452146424e82','0999999999','ADMIN','admin14'),(240,'adm15@a.com','\0','name15','48cea325a4917d8abacb2ab0b491cf7afb0624a1','0999999999','ADMIN','admin15'),(241,'adm16@a.com','\0','name16','b806277d09fa1238b6156cd33ae13ba05d48ddfa','0999999999','ADMIN','admin16'),(242,'adm17@a.com','\0','name17','b826a18fb0bf6e1b223a585a6589a3c831a7895a','0999999999','ADMIN','admin17'),(243,'adm18@a.com','\0','name18','6a911a5b2765588e8785fa8f1ad82309658d9b9e','0999999999','ADMIN','admin18'),(244,'adm19@a.com','\0','name19','cb8a357f0f2c7c74f086ed6fb51ca517d6b99612','0999999999','ADMIN','admin19'),(245,'a@k.d','\0','ннн','52f158dc8175192387cacc4ef93a153f57695e33','0111111111','CLIENT','ннн'),(246,'f@r.r','\0','ййй','de7f0af28970b2dcc6c06b91f237e5faa3c1bd5f','0999999999','CLIENT','ййй'),(247,'a@gmail.com','\0','qqq','cf0ab1c89df348461c524216b96233885b87ef77','0999999999','CLIENT','qqq'),(248,'a@gmail.com','\0','rrr','1530af74f80f00734a729d6f3771cd5edccf38d8','0999999999','CLIENT','rrr'),(249,'a@gmail.com','\0','yyy','ced86d954e33611f9d9f7a00497d13e862971c9b','0999999999','CLIENT','yyy'),(250,'a@gmail.com','\0','uuu','382b11291c106a19fe9f1101d0c09dc4719cff9c','0999999999','CLIENT','uuu'),(251,'a@gmail.com','\0','ooo','5d3f84149e6b84a19e5ebb963f84cb112fa45f90','0999999999','CLIENT','ooo'),(252,'a@gmail.com','\0','sss','04ea4aa6ba6971df55a7e241a808b67dfcd77d9f','0999999999','CLIENT','SSS');
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

-- Dump completed on 2015-05-20 18:22:40
