CREATE TABLE `Course` (
  `courseId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `department` varchar(30) NOT NULL,
  `number` varchar(3) NOT NULL,
  `grade` decimal(5,2) NOT NULL,
  `id` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`courseId`),
  KEY `id` (`id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`id`) REFERENCES `Student` (`id`)
)