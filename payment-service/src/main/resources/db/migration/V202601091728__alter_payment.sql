ALTER TABLE `paymentservice`.`payment`
    ADD COLUMN `user_id` INT NOT NULL DEFAULT 0 AFTER `order_id`;
