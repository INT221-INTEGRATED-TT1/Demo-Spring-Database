CREATE USER 'dev1'@'%' IDENTIFIED BY 'dev@sit';
GRANT ALL privileges ON *.* TO 'dev1'@'%' ;
CREATE SCHEMA  IF NOT EXISTS `task_base` ;
USE task_base;
SET character_set_results = 'utf8mb4';
CREATE TABLE `task_base`.`tasks` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `assignees` VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `status` ENUM('NO_STATUS', 'TO_DO', 'DOING', 'DONE') NOT NULL DEFAULT 'NO_STATUS',
  `createdOn` DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  `updatedOn` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

USE task_base;