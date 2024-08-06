CREATE SCHEMA IF NOT EXISTS `social_media` DEFAULT CHARACTER SET utf8;
USE `social_media`;

CREATE TABLE IF NOT EXISTS `User`
(
    `idUser`   INT          NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(45)  NOT NULL UNIQUE,
    `email`    VARCHAR(45)  NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`idUser`)
);

CREATE TABLE IF NOT EXISTS `Following`
(
    `idFollowing` INT        NOT NULL AUTO_INCREMENT,
    `idUser1`     INT        NOT NULL,
    `idUser2`     INT        NOT NULL,
    `pending`     TINYINT(1) NOT NULL DEFAULT 1,
    PRIMARY KEY (`idFollowing`),
    FOREIGN KEY (`idUser1`) REFERENCES `User` (`idUser`),
    FOREIGN KEY (`idUser2`) REFERENCES `User` (`idUser`),
    UNIQUE KEY `unique_follow` (`idUser1`, `idUser2`)
);

CREATE TABLE IF NOT EXISTS `Post`
(
    `idPost`      INT          NOT NULL AUTO_INCREMENT,
    `totalLikes`  INT          NOT NULL DEFAULT 0,
    `description` VARCHAR(150) NULL,
    `dateCreated` DATE         NOT NULL,
    `image`       BLOB         NULL,
    `idUser`      INT          NOT NULL,
    PRIMARY KEY (`idPost`),
    FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`)
);

CREATE TABLE IF NOT EXISTS `Comment`
(
    `idComment`  INT         NOT NULL AUTO_INCREMENT,
    `text`       VARCHAR(45) NOT NULL,
    `numOfLikes` INT         NOT NULL,
    `idPost`     INT         NOT NULL,
    `idUser`     INT         NOT NULL,
    PRIMARY KEY (`idComment`),
    FOREIGN KEY (`idPost`) REFERENCES `Post` (`idPost`),
    FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`)
);

CREATE TABLE IF NOT EXISTS `LikedComment`
(
    `idLikedComment` INT NOT NULL AUTO_INCREMENT,
    `idUser`         INT NOT NULL,
    `idComment`      INT NOT NULL,
    PRIMARY KEY (`idLikedComment`),
    FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`),
    FOREIGN KEY (`idComment`) REFERENCES `Comment` (`idComment`),
    UNIQUE KEY `unique_liked_comment` (`idUser`, `idComment`)
);

CREATE TABLE IF NOT EXISTS `Like`
(
    `dateLiked` DATE NOT NULL,
    `idLike`    INT  NOT NULL AUTO_INCREMENT,
    `idUser`    INT  NOT NULL,
    `idPost`    INT  NOT NULL,
    PRIMARY KEY (`idLike`),
    FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`),
    FOREIGN KEY (`idPost`) REFERENCES `Post` (`idPost`),
    UNIQUE KEY `unique_liked_post` (`idUser`, `idPost`)
);
