
INSERT INTO `CommentReply` (`idCommentReply`, `text`, `numOfLikes`, `idComment`, `idUser`)
VALUES (1, 'This is a reply to comment 1', 10, 1, 11),
       (2, 'Another reply to comment 1', 5, 2, 11),
       (3, 'Reply to comment 2', 3, 3, 11);

INSERT INTO `LikedReply` (`idLikedReply`, `idUser`, `idCommentReply`)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 1, 2);
