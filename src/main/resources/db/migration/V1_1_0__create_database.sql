CREATE SCHEMA IF NOT EXISTS `social_media` DEFAULT CHARACTER SET utf8;
USE `social_media`;

CREATE TABLE IF NOT EXISTS `User`
(
    `idUser`   INT         NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45) NOT NULL UNIQUE,
    `email`    VARCHAR(45) NOT NULL UNIQUE,
    `password` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`idUser`)
);

CREATE TABLE IF NOT EXISTS `Post`
(
    `idPost`      INT          NOT NULL AUTO_INCREMENT,
    `totalLikes`  INT          NOT NULL,
    `description` VARCHAR(150) NULL,
    `image`       BLOB         NULL,
    `idUser`      INT          NOT NULL,
    PRIMARY KEY (`idPost`)
);

CREATE TABLE IF NOT EXISTS `Comment`
(
    `idComment`  INT         NOT NULL AUTO_INCREMENT,
    `text`       VARCHAR(45) NOT NULL,
    `numOfLikes` INT         NOT NULL,
    `idPost`     INT         NOT NULL,
    `idUser`     INT         NOT NULL,
    PRIMARY KEY (`idComment`)
);

CREATE TABLE IF NOT EXISTS `Like`
(
    `idLike` INT NOT NULL AUTO_INCREMENT,
    `idUser` INT NOT NULL,
    `idPost` INT NOT NULL,
    PRIMARY KEY (`idLike`)
);

ALTER TABLE `Post`
    ADD CONSTRAINT `fk_Post_User`
        FOREIGN KEY (`idUser`)
            REFERENCES `User` (`idUser`);

ALTER TABLE `Comment`
    ADD CONSTRAINT `fk_Comment_Post1`
        FOREIGN KEY (`idPost`)
            REFERENCES `Post` (`idPost`);

ALTER TABLE `Comment`
    ADD CONSTRAINT `fk_Comment_User1`
        FOREIGN KEY (`idUser`)
            REFERENCES `User` (`idUser`);

ALTER TABLE `Like`
    ADD CONSTRAINT `fk_Like_User1`
        FOREIGN KEY (`idUser`)
            REFERENCES `User` (`idUser`),
    ADD CONSTRAINT `fk_Like_Post1`
        FOREIGN KEY (`idPost`)
            REFERENCES `Post` (`idPost`);
