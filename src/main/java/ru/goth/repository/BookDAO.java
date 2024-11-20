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
             PreparedStatement statement = connection.prepareStatement("\n" +
                     "SELECT *\n" +
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
    public void setBook(String title, Author author, String genre, float price, int amount) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("\n" +
                     "INSERT INTO public.book\n" +
                     "(title, author_id, genre, price, amount)\n" +
                     "VALUES (?, ?, ?, ?, ?)")) {

            statement.setString(1, title);
            statement.setLong(2, author.getId());
            statement.setString(3, genre);
            statement.setFloat(4, price);
            statement.setInt(5, amount);
            statement.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public void updateBook(long id, String title, Author author, String genre, float price, int amount) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("\n" +
                     "UPDATE public.book\n" +
                     "SET title = ?, author_id = ?, genre = ?, price = ?, amount = ?\n" +
                     "WHERE book_id = ?")) {

            statement.setString(1, title);
            statement.setLong(2, author.getId());
            statement.setString(3, genre);
            statement.setFloat(4, price);
            statement.setInt(5, amount);
            statement.setLong(6, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
