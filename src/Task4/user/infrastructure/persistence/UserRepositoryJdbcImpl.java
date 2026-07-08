package Task4.user.infrastructure.persistence;

import Task4.user.domain.User;
import Task4.user.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.SplittableRandom;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final String DB_URL;

    private final String DB_USER;

    private final String DB_PASSWORD;

    public UserRepositoryJdbcImpl(String DB_URL, String DB_USER, String DB_PASSWORD) {
        this.DB_URL = DB_URL;
        this.DB_USER = DB_USER;
        this.DB_PASSWORD = DB_PASSWORD;
    }

    @Override
    public void save (User user) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void updateData(String email, int age) {

    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account")) {
                    while (resultSet.next()) {
                        User user = new User(String.valueOf(resultSet.getInt("id")), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getInt("age"));
                        users.add(user);
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }
}
