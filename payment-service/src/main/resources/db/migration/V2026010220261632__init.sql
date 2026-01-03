CREATE TABLE `paymentservice`.`payment`
(
    `payment_id` INT         NOT NULL AUTO_INCREMENT,
    `status`     VARCHAR(45) NOT NULL,
    `created`    DATETIME    NOT NULL,
    `completed`  DATETIME    NOT NULL,
    `order_id`   INT         NOT NULL,
    PRIMARY KEY (`payment_id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;