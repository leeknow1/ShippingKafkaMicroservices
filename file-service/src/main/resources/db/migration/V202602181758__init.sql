CREATE TABLE `fileservice`.`files`
(
    `file_id`      INT          NOT NULL AUTO_INCREMENT,
    `filename`     VARCHAR(255) NOT NULL,
    `content_type` VARCHAR(255) NOT NULL,
    `size`         BIGINT       NOT NULL,
    `object_id`     INT          NOT NULL,
    `type`         VARCHAR(255) NOT NULL,
    PRIMARY KEY (`file_id`),
    CONSTRAINT uk_files_filename_object_type UNIQUE (filename, object_id, type)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;
