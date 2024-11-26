package ru.goth.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.goth.entity.Author;
import ru.goth.entity.BuyBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BuyBookDAOTest {

    private PostgreSQLContainer<?> postgreSQLContainer;
    private Connection connection;
    private BuyBookDAO buyBookDAO;
    private AuthorDAO authorDAO;
    private BookDAO bookDAO;
    private BuyDAO buyDAO;

    @Before
    public void setUp() throws Exception {
        postgreSQLContainer = new PostgreSQLContainer<>("postgres:15.0")
                .withDatabaseName("book_store")
                .withUsername("postgres")
                .withPassword("postgres");
        postgreSQLContainer.start();

        connection = DriverManager.getConnection(
                postgreSQLContainer.getJdbcUrl(),
                postgreSQLContainer.getUsername(),
                postgreSQLContainer.getPassword()
        );

        authorDAO = new AuthorDAO();
        bookDAO = new BookDAO(connection, authorDAO);
        buyDAO = new BuyDAO(connection);
        buyBookDAO = new BuyBookDAO(connection, bookDAO, buyDAO);

        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE author (\n" +
                        "    author_id SERIAL PRIMARY KEY,\n" +
                        "    name_author VARCHAR(50)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE book (\n" +
                        "    book_id SERIAL PRIMARY KEY,\n" +
                        "    title VARCHAR(50),\n" +
                        "    author_id INT NOT NULL,\n" +
                        "    genre VARCHAR(50),\n" +
                        "    price DECIMAL(8, 2),\n" +
                        "    amount INT,\n" +
                        "    FOREIGN KEY (author_id)\n" +
                        "        REFERENCES author (author_id)\n" +
                        "        ON DELETE CASCADE\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE buy(\n" +
                        "    buy_id SERIAL PRIMARY KEY,\n" +
                        "    buy_description VARCHAR(100),\n" +
                        "    client VARCHAR(50)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE buy_book (\n" +
                        "    buy_book_id SERIAL PRIMARY KEY,\n" +
                        "    buy_id INT,\n" +
                        "    book_id INT,\n" +
                        "    amount INT,\n" +
                        "    FOREIGN KEY (buy_id) REFERENCES buy (buy_id),\n" +
                        "    FOREIGN KEY (book_id) REFERENCES book (book_id)\n" +
                        ");")) {
            statement.execute();
        }
    }

    @After
    public void tearDown() throws Exception {
        connection.close();
        postgreSQLContainer.stop();
    }

    @Test
    public void testGetBuyBook() {
        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";
        final int id = 1;

        final String title = "Occult encyclopedia";
        final Author author = new Author();
        final long authorId = 1L;
        final String authorName = "Test";
        author.setId(authorId);
        author.setName(authorName);
        final String genre = "Occult";
        final float price = 228.00F;
        final int amount = 1;

        authorDAO.setAuthor(client);
        int bookId = bookDAO.setBook(title, author, genre, price, amount);
        int buyId = buyDAO.setBuy(description, client);

        buyBookDAO.setBuyBook(buyId, bookId, amount);

        BuyBook buyBook = buyBookDAO.getBuyBook(id);
        assertEquals(amount, buyBook.getAmount());
        assertEquals(description, buyBook.getBuy().getDescription());
        assertEquals(title, buyBook.getBook().getTitle());
    }
    @Test
    public void testUpdateBuyBook() {
        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";
        final long id = 1;

        final String title = "Occult encyclopedia";
        final Author author = new Author();
        final long authorId = 1L;
        final String authorName = "Test";
        author.setId(authorId);
        author.setName(authorName);
        final String genre = "Occult";
        final float price = 228.00F;
        final int amount = 1;

        authorDAO.setAuthor(client);
        int bookId = bookDAO.setBook(title, author, genre, price, amount);
        int buyId = buyDAO.setBuy(description, client);
        buyBookDAO.setBuyBook(buyId, bookId, amount);

        final int newAmount = 2;
        buyBookDAO.updateBuyBook(id, buyId, bookId, newAmount);

        assertEquals(newAmount, buyBookDAO.getBuyBook(id).getAmount());
    }
    @Test
    public void testDeleteBuyBook() throws Exception {
        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";
        final int id = 1;

        final String title = "Occult encyclopedia";
        final Author author = new Author();
        final long authorId = 1L;
        final String authorName = "Test";
        author.setId(authorId);
        author.setName(authorName);
        final String genre = "Occult";
        final float price = 228.00F;
        final int amount = 1;

        authorDAO.setAuthor(client);
        int bookId = bookDAO.setBook(title, author, genre, price, amount);
        int buyId = buyDAO.setBuy(description, client);

        buyBookDAO.setBuyBook(buyId, bookId, amount);

        BuyBook buyBook = buyBookDAO.getBuyBook(id);
        assertEquals(amount, buyBook.getAmount());
        assertEquals(description, buyBook.getBuy().getDescription());
        assertEquals(title, buyBook.getBook().getTitle());

        buyBookDAO.deleteBuyBook(buyBook.getId());

        try (PreparedStatement statement = connection.prepareStatement("\n" +
                "SELECT buy_id, book_id, amount\n" +
                "FROM public.buy_book\n" +
                "WHERE buy_book_id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            assertFalse(resultSet.next());
        }
    }
}