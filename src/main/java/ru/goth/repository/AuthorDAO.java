package ru.goth.repository;

import ru.goth.config.DataBaseConfig;
import ru.goth.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class AuthorDAO {
    private static final Logger logger = Logger.getLogger(AuthorDAO.class.getName());

    private final Connection connection;

    public AuthorDAO() throws SQLException {
        this.connection = DataBaseConfig.getDataSource().getConnection();
    }

    public AuthorDAO(Connection connection) {
        this.connection = connection;
    }

    public Author getAuthor(long id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT author_id, name_author\n" +
                "FROM public.author\n" +
                "WHERE author_id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Author author = new Author();
            while (resultSet.next()) {
                author.setName(resultSet.getString("name_author"));
            }
            return author;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public void setAuthor(String name) {
        try (PreparedStatement statement = connection.prepareStatement("\n" +
                "INSERT INTO public.author \n" +
                "(name_author) VALUES (?)")) {

            statement.setString(1, name);
            statement.executeUpdate();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    public void updateAuthor(long id, String name) {
        try (PreparedStatement statement = connection.prepareStatement("\n" +
                "UPDATE public.author\n" +
                "SET name_author = (?)\n" +
                "WHERE author_id = ?")) {

            statement.setString(1, name);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

    public void deleteAuthor(String name) {
        try (PreparedStatement statement = connection.prepareStatement("\n" +
                "DELETE FROM public.author \n" +
                "WHERE name_author = (?)")) {

            statement.setString(1, name);
            statement.executeUpdate();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}