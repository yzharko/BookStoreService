package ru.goth.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.goth.entity.Author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BookDAOTest {

    private PostgreSQLContainer<?> postgreSQLContainer;
    private Connection connection;
    private AuthorDAO authorDAO;
    private BookDAO bookDAO;

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
    public void testGetBook() {
        final String title = "Occult encyclopedia";
        final Author author = new Author();
        final long authorId = 1L;
        final String authorName = "Test";
        author.setId(authorId);
        author.setName(authorName);
        final String genre = "Occult";
        final float price = 228.00F;
        final int amount = 1;
        final int id = 1;

        authorDAO.setAuthor(authorName);
        bookDAO.setBook(title, author, genre, price, amount);

        assertEquals(title, bookDAO.getBook(id).getTitle());
    }

    @Test
    public void testUpdateBook() {
        final String title = "Occult encyclopedia";
        final Author author = new Author();
        final long authorId = 1L;
        final String authorName = "Test";
        author.setId(authorId);
        author.setName(authorName);
        final String genre = "Occult";
        final float price = 228.00F;
        final int amount = 1;
        final int id = 1;

        authorDAO.setAuthor(authorName);
        bookDAO.setBook(title, author, genre, price, amount);

        final String newTitle = "Deploy the gates of hell";

        bookDAO.updateBook(id, newTitle, author, genre, price, amount);
        assertEquals(newTitle, bookDAO.getBook(id).getTitle());
    }

    @Test
    public void testDeleteBook() throws Exception {
        final String title = "Occult encyclopedia";
        final Author author = new Author();
        final long authorId = 1L;
        final String authorName = "Test";
        author.setId(authorId);
        author.setName(authorName);
        final String genre = "Occult";
        final float price = 228.00F;
        final int amount = 1;

        authorDAO.setAuthor(authorName);
        int bookId = bookDAO.setBook(title, author, genre, price, amount);

        bookDAO.deleteBook(title);

        try (PreparedStatement statement = connection.prepareStatement("\n" +
                "SELECT *\n" +
                "FROM public.book\n" +
                "WHERE book_id = ?")) {
            statement.setLong(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            assertFalse(resultSet.next());
        }
    }
}