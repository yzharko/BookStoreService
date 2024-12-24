package ru.goth.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.goth.entity.Buy;
import ru.goth.repository.rowmapper.BuyRowMapper;

import java.util.List;

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
        List<Buy> results = template.query(sql, parameterSource, new BuyRowMapper());
        return results.isEmpty() ? null : results.get(0);
    }

    public Long setBuy(String description, String client) {
        String sql = "INSERT INTO public.buy\n" +
                "(buy_description, client) VALUES (:description, :client) RETURNING buy_id";
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
                .addValue("client", client)
                .addValue("id", id);
        template.update(sql, parameterSource);
    }

    public void deleteBuy(long id) {
        String sql = "DELETE FROM public.buy \n" +
                "WHERE buy_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        template.update(sql, parameterSource);
    }
}
