CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  `cpf` varchar(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `user_type` enum('ADMIN','BUYER') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK2qv8vmk5wxu215bevli5derq` (`cpf`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

