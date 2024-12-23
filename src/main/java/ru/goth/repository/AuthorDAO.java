package ru.goth.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.goth.entity.Author;
import ru.goth.repository.rowmapper.AuthorRowMapper;

@Repository
public class AuthorDAO {
    private final NamedParameterJdbcTemplate template;

    public AuthorDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.template = namedParameterJdbcTemplate;
    }

    public Author getAuthor(long id) {
        String sql = "SELECT author_id, name_author\n" +
                "FROM public.author\n" +
                "WHERE author_id = :id";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return template.queryForObject(sql, parameterSource, new AuthorRowMapper());
    }

    public void setAuthor(String name) {
        String sql = "INSERT INTO public.author \n" +
                "(name_author) VALUES (:name)";
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        template.update(sql, parameterSource);
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

    public void deleteAuthor(String name) {
        String sql = "DELETE FROM public.author \n" +
                "WHERE name_author = :name";
        SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        template.update(sql, parameterSource);
    }
}