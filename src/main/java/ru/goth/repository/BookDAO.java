package ru.goth.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.goth.entity.Author;
import ru.goth.entity.Book;
import ru.goth.repository.rowmapper.BookRowMapper;

@Repository
public class BookDAO {

    private final NamedParameterJdbcTemplate template;

    public BookDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Book getBook(long id) {
        String sql = "SELECT *\n" +
                "FROM public.book\n" +
                "WHERE book_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return template.queryForObject(sql, parameterSource, new BookRowMapper());
    }

    public int setBook(String title, Author author, String genre, float price, int amount) {
        String sql = "INSERT INTO public.book\n" +
                "(title, author_id, genre, price, amount)\n" +
                "VALUES (:title, :authorId, :genre, :price, :amount) RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("title", title)
                .addValue("authorId", author.getId())
                .addValue("genre", genre)
                .addValue("price", price)
                .addValue("amount", amount);
        return template.queryForObject(sql, parameterSource, Integer.class);
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
                .addValue("amount", amount);
        template.update(sql, parameterSource);
    }

    public void deleteBook(String title) {
        String sql = "DELETE FROM public.book \n" +
                "WHERE title = :title";
        SqlParameterSource parameterSource = new MapSqlParameterSource("title", title);
        template.update(sql, parameterSource);
    }
}
