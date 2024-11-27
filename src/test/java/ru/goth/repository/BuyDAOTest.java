package ru.goth.repository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import static org.junit.Assert.assertEquals;

public class BuyDAOTest {

    private PostgreSQLContainer<?> postgreSQLContainer;
    private Connection connection;
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

        buyDAO = new BuyDAO(connection);

        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE buy(\n" +
                        "    buy_id SERIAL PRIMARY KEY,\n" +
                        "    buy_description VARCHAR(100),\n" +
                        "    client VARCHAR(50)\n" +
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
    public void testGetBuy() {
        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";

        final long id = 1L;

        buyDAO.setBuy(description, client);

        assertEquals(description, buyDAO.getBuy(id).getDescription());
    }

    @Test
    public void testUpdateBuy() {
        final String description = "Deliver only during night time";
        final String client = "GothGamerGhoul282";

        final long id = 1L;

        buyDAO.setBuy(description, client);

        final String newDescription = "Deliver only during night time";
        buyDAO.updateBuy(id, newDescription, client);

        assertEquals(newDescription, buyDAO.getBuy(id).getDescription());
    }
}
