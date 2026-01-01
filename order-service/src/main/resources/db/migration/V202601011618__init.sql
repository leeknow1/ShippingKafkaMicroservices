CREATE TABLE `orderservice`.`orders`
(
    `order_id`     INT         NOT NULL AUTO_INCREMENT,
    `user_id`      INT         NOT NULL,
    `order_status` VARCHAR(45) NOT NULL,
    `item_id`      INT         NOT NULL,
    `amount`       INT         NOT NULL,
    `created`      DATETIME    NOT NULL,
    PRIMARY KEY (`order_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;
