CREATE TABLE `PhoneNumber` (
  `phoneId` int NOT NULL AUTO_INCREMENT,
  `areaCode` varchar(3) NOT NULL,
  `exchange` varchar(3) NOT NULL,
  `extension` varchar(4) NOT NULL,
  `id` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`phoneId`),
  KEY `id` (`id`),
  CONSTRAINT `phonenumber_ibfk_1` FOREIGN KEY (`id`) REFERENCES `Student` (`id`)
)