CREATE TABLE IF NOT EXISTS `users_storage` (
                                               `product_code` varchar(255) DEFAULT NULL,
    `storage_fk` bigint NOT NULL,
    `users_fk` bigint NOT NULL,
    PRIMARY KEY (`storage_fk`,`users_fk`),
    KEY `FKp5s04jolybp6g96f8i7jks304` (`users_fk`),
    CONSTRAINT `FKp5s04jolybp6g96f8i7jks304` FOREIGN KEY (`users_fk`) REFERENCES `user` (`id`),
    CONSTRAINT `FKqhh07jnn0y06ixdbqsp1hwk0u` FOREIGN KEY (`storage_fk`) REFERENCES `storage` (`product_id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;