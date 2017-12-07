CREATE TABLE `employee` (
`id` int(11) NOT NULL AUTO_INCREMENT,
`first_name` varchar(45) NOT NULL,
`last_name` varchar(45) NOT NULL,
`dob` datetime NOT NULL,
`salary` double NOT NULL,
`gender` varchar(45) NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

INSERT INTO `employee` (`id`,`first_name`,`last_name`,`dob`,`salary`,`gender`) VALUES (1,'John','Doe','1990-07-03 00:00:00',123123,'M');
INSERT INTO `employee` (`id`,`first_name`,`last_name`,`dob`,`salary`,`gender`) VALUES (2,'Jane','Doe','1991-02-03 00:00:00',130000,'F');
INSERT INTO `employee` (`id`,`first_name`,`last_name`,`dob`,`salary`,`gender`) VALUES (3,'Nathan','Drake','1985-02-03 00:00:00',150000,'M');