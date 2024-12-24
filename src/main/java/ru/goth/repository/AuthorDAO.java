package ru.goth.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.goth.entity.Author;
import ru.goth.repository.rowmapper.AuthorRowMapper;

import java.util.List;

@Repository
public class AuthorDAO {
    private final NamedParameterJdbcTemplate template;

    public AuthorDAO(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public Author getAuthor(long id) {
        String sql = "SELECT author_id, name_author\n" +
                "FROM public.author\n" +
                "WHERE author_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        List<Author> results = template.query(sql, parameterSource, new AuthorRowMapper());
        return results.isEmpty() ? null : results.get(0);
    }

    public Long setAuthor(String name) {
        String sql = "INSERT INTO public.author \n" +
                "(name_author) VALUES (:name) RETURNING author_id";

        SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        return template.queryForObject(sql, parameterSource, Long.class);
    }

    public void updateAuthor(long id, String name) {
        String sql = "UPDATE public.author\n" +
                "SET name_author = :name\n" +
                "WHERE author_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", name)
                .addValue("id", id);
        template.update(sql, parameterSource);
    }

    public void deleteAuthor(long id) {
        String sql = "DELETE FROM public.author \n" +
                "WHERE author_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        template.update(sql, parameterSource);
    }
}