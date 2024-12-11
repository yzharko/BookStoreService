CREATE TABLE author (
    author_id SERIAL PRIMARY KEY,
    name_author VARCHAR(50)
);

CREATE TABLE book (
    book_id SERIAL PRIMARY KEY,
    title VARCHAR(50),
    author_id INT NOT NULL,
    genre VARCHAR(50),
    price DECIMAL(8, 2),
    amount INT,
    FOREIGN KEY (author_id)
        REFERENCES author (author_id)
        ON DELETE CASCADE
);

CREATE TABLE buy(
    buy_id SERIAL PRIMARY KEY,
    buy_description VARCHAR(100),
    client VARCHAR(50)
);

CREATE TABLE buy_book (
    buy_book_id SERIAL PRIMARY KEY,
    buy_id INT,
    book_id INT,
    amount INT,
    FOREIGN KEY (buy_id) REFERENCES buy (buy_id),
    FOREIGN KEY (book_id) REFERENCES book (book_id)
);
