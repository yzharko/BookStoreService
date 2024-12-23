package ru.goth.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.goth.entity.BuyBook;
import ru.goth.repository.rowmapper.BuyBookRowMapper;

@Repository
public class BuyBookDAO {

    private final NamedParameterJdbcTemplate template;

    public BuyBookDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public BuyBook getBuyBook(long id) {
        String sql = "SELECT buy_book_id, buy_id, book_id, amount\n" +
                "FROM public.buy_book\n" +
                "WHERE buy_book_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return template.queryForObject(sql, parameterSource, new BuyBookRowMapper());
    }

    public void setBuyBook(long buyId, int bookId, int amount) {
        String sql = "INSERT INTO public.buy_book\n" +
                "(buy_id, book_id, amount)\n" +
                "VALUES (:buyId, :bookId, :amount)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("buyId", buyId)
                .addValue("bookId", bookId)
                .addValue("amount", amount);
        template.update(sql, parameterSource);
    }

    public void updateBuyBook(long buyBookId, long buyId, long bookId, int amount) {
        String sql = "UPDATE public.buy_book\n" +
                "SET buy_id=:buyId, book_id=:bookId, amount=:amount\n" +
                "WHERE buy_book_id = :buyBookId";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("buyId", buyId)
                .addValue("bookId", bookId)
                .addValue("amount", amount)
                .addValue("buyBookId", buyBookId);
        template.update(sql, parameterSource);
    }

    public void deleteBuyBook(long id) {
        String sql = "DELETE FROM public.buy_book \n" +
                "WHERE buy_book_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        template.update(sql, parameterSource);
    }
}
