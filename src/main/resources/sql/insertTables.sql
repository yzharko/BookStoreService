INSERT INTO author (name_author)
VALUES ('Bulgakov M.A.'),
       ('Dostoevsky F.M.'),
       ('Esenin S.A.'),
       ('Pasternak B.L.'),
       ('Lermontov M.Y.');

INSERT INTO book (title, author_id, genre, price, amount)
VALUES  ('Master and Margarita', 1, 'Novel', 670.99, 3),
        ('The White Guard ', 1, 'Novel', 540.50, 5),
        ('Idiot', 2, 'Novel', 460.00, 10),
        ('Karamazov Brothers', 2, 'Novel', 799.01, 2),
        ('Player', 2, 'Novel', 480.50, 10),
        ('Poems and Verses', 3, 'Poetry', 650.00, 15),
        ('Black man', 3, 'Poetry', 570.20, 6),
        ('Lirics', 4, 'Poetry', 518.99, 2);

INSERT INTO buy (buy_description, client)
VALUES ('Delivery only in the evening', 'Baranov P.'),
       (NULL, 'Semyonov I.'),
       ('Pack each book individually', 'Abramova K.'),
       (NULL, 'Baranov P.');

INSERT INTO buy_book(buy_id, book_id, amount)
VALUES (1, 1, 1),
       (1, 7, 2),
       (1, 3, 1),
       (2, 8, 2),
       (3, 3, 2),
       (3, 2, 1),
       (3, 1, 1),
       (4, 5, 1);
       