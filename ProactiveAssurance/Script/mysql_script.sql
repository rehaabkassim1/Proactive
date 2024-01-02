CREATE TABLE `proactive_assurance` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `device_Id` varchar(200) DEFAULT NULL,
  `line_Id` varchar(200) DEFAULT NULL,
  `date_From_Assia` timestamp NULL DEFAULT NULL,
  `event` varchar(200) DEFAULT NULL,
  `last_contact` datetime(6) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `record_count` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `start_Date` datetime(6) DEFAULT NULL,
  `count` int DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`Id`)
) 
