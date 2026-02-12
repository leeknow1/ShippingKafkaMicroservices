CREATE TABLE `userservice`.`users`
(
    `user_id`  INT          NOT NULL AUTO_INCREMENT,
    `email`    VARCHAR(350) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `enabled`  TINYINT      NOT NULL DEFAULT 1,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;
