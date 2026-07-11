package Task7.main.java.ru.itis.shop.infrastructure.persistence.jbbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper <T> {
    T mapRow(ResultSet row) throws SQLException;
}
