CREATE TABLE IF NOT EXISTS `storage` (
                                         `product_id` bigint NOT NULL AUTO_INCREMENT,
                                         `creation_timestamp` datetime(6) DEFAULT NULL,
    `image_directory` varchar(255) DEFAULT NULL,
    `price` double NOT NULL,
    `product_description` varchar(255) NOT NULL,
    `product_name` varchar(255) NOT NULL,
    `product_type` enum('JAQUET','PANTS','SHIRTS','SHORTS','SLIPPER','TENNIS') NOT NULL,
    `quantity` int NOT NULL,
    PRIMARY KEY (`product_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

