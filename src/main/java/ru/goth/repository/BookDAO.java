package ru.goth.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.goth.entity.Author;
import ru.goth.entity.Book;
import ru.goth.repository.rowmapper.BookRowMapper;

import java.util.List;

@Repository
public class BookDAO {

    private final NamedParameterJdbcTemplate template;
    private AuthorDAO authorDAO;

    public BookDAO(NamedParameterJdbcTemplate template, AuthorDAO authorDAO) {
        this.template = template;
        this.authorDAO = authorDAO;
    }

    public Book getBook(long id) {
        String sql = "SELECT *\n" +
                "FROM public.book\n" +
                "WHERE book_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        List<Book> bookList = template.query(sql, parameterSource, new BookRowMapper());

        if (bookList.isEmpty()) {
            return null;
        } else {
            Book book = bookList.get(0);
            book.setAuthor(authorDAO.getAuthor(book.getAuthor().getId()));
            return book;
        }
    }

    public Long setBook(String title, Author author, String genre, float price, int amount) {
        String sql = "INSERT INTO public.book\n" +
                "(title, author_id, genre, price, amount)\n" +
                "VALUES (:title, :authorId, :genre, :price, :amount) RETURNING book_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("title", title)
                .addValue("authorId", author.getId())
                .addValue("genre", genre)
                .addValue("price", price)
                .addValue("amount", amount);
        return template.queryForObject(sql, parameterSource, Long.class);
    }

    public void updateBook(long id, String title, Author author, String genre, float price, int amount) {
        String sql = "UPDATE public.book\n" +
                "SET title = :title, author_id = :authorId, genre = :genre, price = :price, amount = :amount\n" +
                "WHERE book_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("title", title)
                .addValue("authorId", author.getId())
                .addValue("genre", genre)
                .addValue("price", price)
                .addValue("amount", amount)
                .addValue("id", id);
        template.update(sql, parameterSource);
    }

    public void deleteBook(long id) {
        String sql = "DELETE FROM public.book \n" +
                "WHERE book_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        template.update(sql, parameterSource);
    }
}
