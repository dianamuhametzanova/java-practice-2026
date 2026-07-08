package Task4;

import java.sql.*;

public class Main {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/java_2026";

    private static final String DB_USER = "diana";

    private static final String DB_PASSWORD = "56971729";

    static void main() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account")) {
                    while (resultSet.next()) {
                        System.out.println(resultSet.getInt("id") +
                            " " + resultSet.getString("first_name") +
                            " " + resultSet.getString("last_name") +
                            " " + resultSet.getInt("age"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
