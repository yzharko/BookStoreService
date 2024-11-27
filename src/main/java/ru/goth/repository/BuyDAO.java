package ru.goth.repository;

import ru.goth.config.DataBaseConfig;
import ru.goth.entity.Buy;

import java.sql.*;
import java.util.logging.Logger;

public class BuyDAO {
    private static final Logger logger = Logger.getLogger(BuyDAO.class.getName());

    private final Connection connection;

    public BuyDAO() throws SQLException {
        this.connection = DataBaseConfig.getDataSource().getConnection();
    }

    public Buy getBuy(long id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT buy_description, client\n" +
                "FROM public.buy\n" +
                "WHERE buy_id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Buy buy = new Buy();
            while (resultSet.next()) {
                buy.setDescription(resultSet.getString("buy_description"));
                buy.setClient(resultSet.getString("client"));
            }
            return buy;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public int setBuy(String description, String client) {
        try (PreparedStatement statement = connection.prepareStatement("\n" +
                "INSERT INTO public.buy\n" +
                "(buy_description, client) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, description);
            statement.setString(2, client);
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            int generatedId = 0;
            if (resultSet.next()) {
                generatedId = resultSet.getInt(1);
            }
            return generatedId;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return 0;
        }
    }

    public void updateBuy(long id, String description, String client) {
        try (PreparedStatement statement = connection.prepareStatement("\n" +
                "UPDATE public.buy\n" +
                "SET buy_description = ?, client = ?\n" +
                "WHERE buy_id = ?")) {

            statement.setString(1, description);
            statement.setString(2, client);
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
