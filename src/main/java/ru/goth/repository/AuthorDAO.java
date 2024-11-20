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
}
