package ru.goth.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.goth.entity.BuyBook;
import ru.goth.repository.rowmapper.BuyBookRowMapper;

import java.util.Optional;

@Repository
public class BuyBookDAO {

    private final NamedParameterJdbcTemplate template;
    private BookDAO bookDAO;
    private BuyDAO buyDAO;

    public BuyBookDAO(NamedParameterJdbcTemplate template, BookDAO bookDAO, BuyDAO buyDAO) {
        this.template = template;
        this.buyDAO = buyDAO;
        this.bookDAO = bookDAO;
    }

    public BuyBook getBuyBook(long id) {
        String sql = "SELECT buy_book_id, buy_id, book_id, amount\n" +
                "FROM public.buy_book\n" +
                "WHERE buy_book_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        BuyBook buyBook = template.queryForObject(sql, parameterSource, new BuyBookRowMapper());

        buyBook.setBook(bookDAO.getBook(buyBook.getBook().getId()));
        buyBook.setBuy(buyDAO.getBuy(buyBook.getBuy().getId()));

        return buyBook;
    }

    public Optional<Long> setBuyBook(long buyId, long bookId, int amount) {
        String sql = "INSERT INTO public.buy_book\n" +
                "(buy_id, book_id, amount)\n" +
                "VALUES (:buyId, :bookId, :amount) RETURNING buy_book_id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("buyId", buyId)
                .addValue("bookId", bookId)
                .addValue("amount", amount);
        return Optional.ofNullable(template.queryForObject(sql, parameterSource, Long.class));
    }

    public void updateBuyBook(long buyBookId, long buyId, long bookId, int amount) {
        String sql = "UPDATE public.buy_book\n" +
                "SET buy_id=:buyId, book_id=:bookId, amount=:amount\n" +
                "WHERE buy_book_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("buyId", buyId)
                .addValue("bookId", bookId)
                .addValue("amount", amount)
                .addValue("id", buyBookId);
        template.update(sql, parameterSource);
    }

    public void deleteBuyBook(long id) {
        String sql = "DELETE FROM public.buy_book \n" +
                "WHERE buy_book_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        template.update(sql, parameterSource);
    }
}
