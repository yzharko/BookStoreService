package ru.goth.repository;

import ru.goth.config.DataBaseConfig;
import ru.goth.entity.Author;
import ru.goth.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookDAO {
    public Book getBook(long id) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT *\n" +
                     "FROM public.book\n" +
                     "WHERE book_id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            Book book = new Book();
            Author author = new Author();
            while (resultSet.next()) {
                book.setTitle(resultSet.getString("title"));
                author.setId(resultSet.getLong("author_id"));
                book.setGenre(resultSet.getString("genre"));
                book.setPrice(resultSet.getFloat("price"));
                book.setAmount(resultSet.getInt("amount"));
            }
            AuthorDAO authorDAO = new AuthorDAO();
            author.setName(authorDAO.getAuthor(author.getId()).getName());
            book.setAuthor(author);
            return book;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }
}
