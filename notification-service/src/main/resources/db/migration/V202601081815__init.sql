CREATE TABLE `notificationservice`.`notification`
(
    `notification_id`     INT         NOT NULL AUTO_INCREMENT,
    `user_id`             INT         NOT NULL,
    `order_id`            INT         NOT NULL,
    `notification_status` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`notification_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;
