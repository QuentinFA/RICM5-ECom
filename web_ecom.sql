# ************************************************************
# Sequel Pro SQL dump
# Version 4096
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.1.63)
# Database: web_ecom
# Generation Time: 2016-10-03 12:32:30 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table ADMIN
# ------------------------------------------------------------
USE web_ecom;
DROP TABLE IF EXISTS `ADMIN`;

CREATE TABLE `ADMIN` (
  `idAdmin` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `firstname` varchar(30) DEFAULT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idAdmin`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `ADMIN` WRITE;
/*!40000 ALTER TABLE `ADMIN` DISABLE KEYS */;

INSERT INTO `ADMIN` (`idAdmin`, `password`, `firstname`, `lastname`)
VALUES
	('zhaozilong','zhaozilong','zilong','zhao');

/*!40000 ALTER TABLE `ADMIN` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table CATEGORY
# ------------------------------------------------------------

DROP TABLE IF EXISTS `CATEGORY`;

CREATE TABLE `CATEGORY` (
  `idCommand` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  PRIMARY KEY (`idCommand`),
  UNIQUE KEY `uq_Type` (`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `CATEGORY` WRITE;
/*!40000 ALTER TABLE `CATEGORY` DISABLE KEYS */;

INSERT INTO `CATEGORY` (`idCommand`, `type`)
VALUES
	(1,'MULTIMEDIA'),
	(2,'LOCATION');

/*!40000 ALTER TABLE `CATEGORY` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table COMMAND
# ------------------------------------------------------------

DROP TABLE IF EXISTS `COMMAND`;

CREATE TABLE `COMMAND` (
  `idCommand` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `idProduct` int(12) unsigned NOT NULL,
  `idUser` varchar(30) NOT NULL,
  `State` varchar(30) NOT NULL,
  `Create_Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idCommand`),
  KEY `fk_idProduct` (`idProduct`),
  KEY `fk_idUser` (`idUser`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table IMAGE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `IMAGE`;

CREATE TABLE `IMAGE` (
  `idImage` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `idProduct` int(12) unsigned NOT NULL,
  `idUser` varchar(30) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  PRIMARY KEY (`idImage`),
  KEY `fk_idProduct` (`idProduct`),
  KEY `fk_idUser` (`idUser`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



# Dump of table PRODUCT
# ------------------------------------------------------------

DROP TABLE IF EXISTS `PRODUCT`;

CREATE TABLE `PRODUCT` (
  `idProduct` int(12) unsigned NOT NULL AUTO_INCREMENT,
  `idUser` varchar(30) NOT NULL,
  `price` int(12) unsigned NOT NULL,
  `type` varchar(50) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`idProduct`),
  KEY `fk_UserID` (`idUser`),
  KEY `fK_Type` (`type`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `PRODUCT` WRITE;
/*!40000 ALTER TABLE `PRODUCT` DISABLE KEYS */;

INSERT INTO `PRODUCT` (`idProduct`, `idUser`, `price`, `type`, `title`, `description`)
VALUES
	(1,'zhaozilong',50,'MULTIMEDIA','Fujinon xf 55-200 mm f/3,5-4,8 r lm ois','Suite à l\'achat du 50-140, je vends mon 55-200mm pour fuji X.\nC\'est une 2nde main, le 1er propriétaire l\'a acheté chez Photo Univers en juin 2014. Il n\'est plus sous garantie.\nExcellent état, pas de rayures, fourni dans sa boîte avec facture.\n\nExpédition possible, règlement par virement bancaire.'),
	(2,'zhaozilong',450,'LOCATION','Appartement les 2 alpes pied des pistes','placé à proximité du centre de la station, R6 sur le plan des 2 Alpes\nStudio 4-6 couchages: une chambre avec un lit double pour 2 personnes, 2 lits superposés pour enfant, un clic clac dans le séjour pour 2 personnes.\nKitchenette avec frigo, 2 plaques, un four double fonction (four et micro-onde), appareil à raclette, grille-pain et cafetière\nTélévision écran plat\nsalle de douche et toilette séparé\nbalcon avec vue sur les pistes\nimmeuble avec ascenseur\nune place de parking couvert\nAnimaux non acceptés');

/*!40000 ALTER TABLE `PRODUCT` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table USER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `idUser` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` int(13) unsigned DEFAULT NULL,
  `actived` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `uq_Email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;

INSERT INTO `USER` (`idUser`, `password`, `firstname`, `lastname`, `email`, `telephone`, `actived`)
VALUES
	('zhaozilong','zhaozilong','zilong','zhao','imzhaozilong@gmail.com',782656512,1);

/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
