package ru.goth.repository;

import ru.goth.config.DataBaseConfig;
import ru.goth.entity.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthorDAO {
    public Author getAuthor(long id) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT author_id, name_author\n" +
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
            e.getStackTrace();
            return null;
        }
    }
    public void setAuthor(String name) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO public.author (name_author) VALUES (?)")) {

            statement.setString(1, name);
            statement.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public void updateAuthor(long id, String name) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE public.author SET name_author = (?) WHERE author_id = ?")) {

            statement.setString(1, name);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public void deleteAuthor(String name) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM public.author WHERE name_author = (?)")) {

            statement.setString(1, name);
            statement.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
