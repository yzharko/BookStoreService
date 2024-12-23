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
        buyBook.setId(rs.getInt("id"));
        buyBook.setBuy(rs.getObject("buy", Buy.class));
        buyBook.setBook(rs.getObject("book", Book.class));
        buyBook.setAmount(rs.getInt("amount"));
        return buyBook;
    }
}
