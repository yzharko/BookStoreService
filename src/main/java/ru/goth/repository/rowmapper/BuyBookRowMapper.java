package ru.goth.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.goth.entity.Book;
import ru.goth.entity.Buy;
import ru.goth.entity.BuyBook;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyBookRowMapper implements RowMapper<BuyBook> {
    @Override
    public BuyBook mapRow(ResultSet rs, int rowNum) throws SQLException {
        BuyBook buyBook = new BuyBook();
        buyBook.setId(rs.getLong("buy_book_id"));

        Buy buy = new Buy();
        buy.setId(rs.getLong("buy_id"));
        buyBook.setBuy(buy);

        Book book = new Book();
        book.setId(rs.getLong("book_id"));
        buyBook.setBook(book);

        buyBook.setAmount(rs.getInt("amount"));
        return buyBook;
    }
}
