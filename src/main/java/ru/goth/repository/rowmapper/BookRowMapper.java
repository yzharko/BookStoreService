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
        book.setId(rs.getLong("book_id"));
        book.setTitle(rs.getString("title"));

        Author author = new Author();
        author.setId(rs.getLong("author_id"));
        book.setAuthor(author);

        book.setPrice(rs.getInt("price"));
        book.setGenre(rs.getString("genre"));
        book.setAmount(rs.getInt("amount"));
        return book;
    }
}
