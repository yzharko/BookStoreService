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

public class AuthorDAOTest {
    private PostgreSQLContainer<?> postgreSQLContainer;
    private Connection connection;
    private AuthorDAO authorDAO;

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

        authorDAO = new AuthorDAO(connection);

        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE author (\n" +
                        "    author_id SERIAL PRIMARY KEY,\n" +
                        "    name_author VARCHAR(50)\n" +
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
    public void testGetAuthor() {
        final long id = 1L;
        final String name = "G.O.M.";
        authorDAO.setAuthor(name);

        Author expected = new Author(id, name);
        Author actual = authorDAO.getAuthor(id);
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void testUpdateAuthor() {
        final long id = 1L;
        final String name = "G.O.M.";
        authorDAO.setAuthor(name);

        final String newName = "B.O.M.";

        authorDAO.updateAuthor(id, newName);
        assertEquals(newName, authorDAO.getAuthor(id).getName());
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        final int id = 1;
        final String name = "G.O.M.";
        authorDAO.setAuthor(name);

        authorDAO.deleteAuthor(name);

        try (PreparedStatement statement = connection.prepareStatement("\n" +
                "SELECT *\n" +
                "FROM public.author\n" +
                "WHERE author_id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            assertFalse(resultSet.next());
        }
    }
}
