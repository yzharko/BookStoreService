package ru.goth.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.goth.entity.Author;
import ru.goth.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getObject("author", Author.class));
        book.setPrice(rs.getInt("price"));
        book.setGenre(rs.getString("genre"));
        book.setAmount(rs.getInt("amount"));
        return book;
    }
}
