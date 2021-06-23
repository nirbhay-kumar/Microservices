DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  PRIMARY KEY (`id`)
) 

LOCK TABLES `login` WRITE;
INSERT INTO `login` VALUES (1,'name','Name kr');
UNLOCK TABLES;