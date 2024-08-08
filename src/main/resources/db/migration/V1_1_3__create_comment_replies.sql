CREATE TABLE IF NOT EXISTS `CommentReply`
(
    `idCommentReply`    INT             NOT NULL AUTO_INCREMENT,
    `text`              VARCHAR(45)     NOT NULL,
    `numOfLikes`        INT             NOT NULL,
    `idComment`         INT             NOT NULL,
    `idUser`            INT             NOT NULL,
    PRIMARY KEY (`idCommentReply`),
    FOREIGN KEY (`idComment`) REFERENCES `Comment` (`idComment`),
    FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`)
    );

CREATE TABLE IF NOT EXISTS `LikedReply`
(
    `idLikedReply`     INT             NOT NULL AUTO_INCREMENT,
    `idUser`           INT             NOT NULL,
    `idCommentReply`   INT             NOT NULL,
    PRIMARY KEY (`idLikedReply`),
    FOREIGN KEY (`idUser`) REFERENCES `User` (`idUser`),
    FOREIGN KEY (`idCommentReply`) REFERENCES `CommentReply` (`idCommentReply`),
    UNIQUE KEY `unique_liked_reply` (`idUser`, `idCommentReply`)
    );
