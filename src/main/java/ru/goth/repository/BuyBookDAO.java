package ru.goth.repository;

import ru.goth.config.DataBaseConfig;
import ru.goth.entity.Book;
import ru.goth.entity.Buy;
import ru.goth.entity.BuyBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BuyBookDAO {
    public BuyBook getBuyBook(long id) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("\n" +
                     "SELECT buy_id, book_id, amount\n" +
                     "FROM public.buy_book\n" +
                     "WHERE buy_book_id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            BuyBook buyBook = new BuyBook();
            Buy buy = new Buy();
            Book book = new Book();
            while (resultSet.next()) {
                buy.setId(resultSet.getLong("buy_id"));
                book.setId(resultSet.getLong("book_id"));
                buyBook.setAmount(resultSet.getInt("amount"));
            }

            BookDAO bookDAO = new BookDAO();
            book = bookDAO.getBook(book.getId());
            buyBook.setBook(book);

            BuyDAO buyDAO = new BuyDAO();
            buy.setDescription(buyDAO.getBuy(buy.getId()).getDescription());
            buy.setClient(buyDAO.getBuy(buy.getId()).getClient());
            buyBook.setBuy(buy);

            return buyBook;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
            return null;
        }
    }
    public void setBuyBook(int buyId, int bookId, int amount) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("\n" +
                     "INSERT INTO public.buy_book\n" +
                     "(buy_id, book_id, amount)\n" +
                     "VALUES (?, ?, ?)")) {

            statement.setInt(1, buyId);
            statement.setInt(2, bookId);
            statement.setInt(3, amount);
            statement.executeUpdate();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
    public void deleteBuyBook(long id) {
        try (Connection connection = DataBaseConfig.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement("\n" +
                     "DELETE FROM public.buy_book \n" +
                     "WHERE buy_book_id = (?)")) {

            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }
    }
}
