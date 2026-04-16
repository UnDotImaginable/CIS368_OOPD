CREATE TABLE `Student` (
  `id` varchar(7) NOT NULL,
  `first` varchar(30) NOT NULL,
  `middle` varchar(3) DEFAULT NULL,
  `last` varchar(30) NOT NULL,
  `streetNumber` varchar(20) NOT NULL,
  `streetName` varchar(30) NOT NULL,
  `theRest` varchar(75) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(10) NOT NULL,
  `zip` varchar(5) NOT NULL,
  `major` varchar(35) DEFAULT NULL,
  `minor` varchar(35) DEFAULT NULL,
  `undergradInst` varchar(75) DEFAULT NULL,
  `stipend` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
)