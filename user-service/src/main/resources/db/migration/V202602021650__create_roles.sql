CREATE TABLE `userservice`.`roles`
(
    `role_id` INT         NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(45) NOT NULL,
    PRIMARY KEY (`role_id`),
    UNIQUE INDEX `role_name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;

INSERT INTO `userservice`.`roles` VALUES (1, 'ROLE_ADMIN');
INSERT INTO `userservice`.`roles` VALUES (2, 'ROLE_USER');
