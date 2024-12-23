package ru.goth.repository.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.goth.entity.Author;
import ru.goth.entity.Buy;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BuyRowMapper implements RowMapper<Buy> {
    @Override
    public Buy mapRow(ResultSet rs, int rowNum) throws SQLException {
        Buy buy = new Buy();
        buy.setId(rs.getInt("id"));
        buy.setDescription(rs.getString("description"));
        buy.setClient(rs.getString("client"));
        return buy;
    }
}
