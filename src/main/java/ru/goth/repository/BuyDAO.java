package ru.goth.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.goth.entity.Buy;
import ru.goth.repository.rowmapper.BuyRowMapper;

@Repository
public class BuyDAO {
    private final NamedParameterJdbcTemplate template;

    public BuyDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Buy getBuy(long id) {
        String sql = "SELECT buy_id, buy_description, client\n" +
                "FROM public.buy\n" +
                "WHERE buy_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return template.queryForObject(sql, parameterSource, new BuyRowMapper());
    }

    public long setBuy(String description, String client) {
        String sql = "INSERT INTO public.buy\n" +
                "(buy_description, client) VALUES (:description, :client) RETURNING ID";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("description", description)
                .addValue("client", client);
        return template.queryForObject(sql, parameterSource, Long.class);
    }

    public void updateBuy(long id, String description, String client) {
        String sql = "UPDATE public.buy\n" +
                "SET buy_description = :description, client = :client\n" +
                "WHERE buy_id = ?";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("description", description)
                .addValue("client", client);
        template.update(sql, parameterSource);
    }
}
