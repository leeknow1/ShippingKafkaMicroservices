ALTER TABLE `paymentservice`.`payment`
    ADD UNIQUE INDEX `order_id_UNIQUE` (`order_id` ASC) VISIBLE;
