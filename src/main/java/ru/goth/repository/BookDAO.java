package ru.goth.repository;

import ru.goth.config.DataBaseConfig;
import ru.goth.entity.Author;
import ru.goth.entity.Book;

import java.sql.*;
import java.util.logging.Logger;

public class BookDAO {
    private static final Logger logger = Logger.getLogger(BookDAO.class.getName());

    private final Connection connection;
    private final AuthorDAO authorDAO;

    public BookDAO() throws SQLException {
        this.connection = DataBaseConfig.getDataSource().getConnection();
        this.authorDAO = new AuthorDAO();
    }

    public Book getBook(long id) {
        try (PreparedStatement statement = connection.prepareStatement("\n" +
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
            author.setName(authorDAO.getAuthor(author.getId()).getName());
            book.setAuthor(author);
            return book;
        } catch (Exception e) {
            logger.info(e.getMessage());
            return null;
        }
    }

    public int setBook(String title, Author author, String genre, float price, int amount) {
        try (PreparedStatement statement = connection.prepareStatement("\n" +
                "INSERT INTO public.book\n" +
                "(title, author_id, genre, price, amount)\n" +
                "VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, title);
            statement.setLong(2, author.getId());
            statement.setString(3, genre);
            statement.setFloat(4, price);
            statement.setInt(5, amount);
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

    public void updateBook(long id, String title, Author author, String genre, float price, int amount) {
        try (PreparedStatement statement = connection.prepareStatement("\n" +
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
            logger.info(e.getMessage());
        }
    }

    public void deleteBook(String title) {
        try (PreparedStatement statement = connection.prepareStatement("\n" +
                "DELETE FROM public.book \n" +
                "WHERE title = (?)")) {

            statement.setString(1, title);
            statement.executeUpdate();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
