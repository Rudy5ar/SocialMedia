INSERT INTO `User` (`username`, `email`, `password`)
VALUES
    ('alice', 'alice@example.com', 'password123'),
    ('bob', 'bob@example.com', 'password123'),
    ('carol', 'carol@example.com', 'password123'),
    ('dave', 'dave@example.com', 'password123'),
    ('eve', 'eve@example.com', 'password123'),
    ('frank', 'frank@example.com', 'password123'),
    ('grace', 'grace@example.com', 'password123'),
    ('heidi', 'heidi@example.com', 'password123'),
    ('ivan', 'ivan@example.com', 'password123'),
    ('judy', 'judy@example.com', 'password123');

INSERT INTO `Post` (`totalLikes`, `description`, `dateCreated`, `image`, `idUser`)
VALUES
    (10, 'Great sunset at the beach!', '2024-07-01', NULL, 1),
    (5, 'Had a fantastic dinner at a new restaurant.', '2024-07-02', NULL, 2),
    (20, 'Just finished reading a new book. Highly recommend it!', '2024-07-03', NULL, 3),
    (15, 'Went hiking in the mountains today.', '2024-07-04', NULL, 4),
    (8, 'New recipe for homemade pizza turned out amazing!', '2024-07-05', NULL, 5),
    (12, 'Visited a new museum exhibit.', '2024-07-06', NULL, 6),
    (25, 'Running a marathon this weekend!', '2024-07-07', NULL, 7),
    (30, 'Celebrated a friend’s birthday with a surprise party.', '2024-07-08', NULL, 8),
    (7, 'Caught a rare bird on camera during my morning walk.', '2024-07-09', NULL, 9),
    (22, 'Explored a new city over the weekend.', '2024-07-10', NULL, 10);

INSERT INTO `Comment` (`text`, `numOfLikes`, `idPost`, `idUser`)
VALUES
    ('Amazing view!', 3, 1, 2),
    ('I need to try that place!', 1, 2, 3),
    ('What book did you read?', 4, 3, 4),
    ('Looks like a great hike!', 2, 4, 5),
    ('I’m definitely making this pizza!', 6, 5, 6),
    ('Which museum did you visit?', 2, 6, 7),
    ('Good luck with the marathon!', 8, 7, 8),
    ('Happy birthday to your friend!', 5, 8, 9),
    ('Incredible shot!', 7, 9, 10),
    ('Sounds like an adventure!', 3, 10, 1);

INSERT INTO `Like` (`dateLiked`, `idUser`, `idPost`)
VALUES
    ('2024-07-01', 1, 1),
    ('2024-07-01', 2, 1),
    ('2024-07-01', 3, 1),
    ('2024-07-02', 4, 2),
    ('2024-07-02', 5, 2),
    ('2024-07-03', 6, 3),
    ('2024-07-04', 7, 4),
    ('2024-07-05', 8, 5),
    ('2024-07-06', 9, 6),
    ('2024-07-07', 10, 7),
    ('2024-07-08', 1, 8),
    ('2024-07-09', 2, 9),
    ('2024-07-10', 3, 10),
    ('2024-07-01', 4, 1),
    ('2024-07-02', 5, 2),
    ('2024-07-03', 6, 3),
    ('2024-07-04', 7, 4),
    ('2024-07-05', 8, 5),
    ('2024-07-06', 9, 6),
    ('2024-07-07', 10, 7);
