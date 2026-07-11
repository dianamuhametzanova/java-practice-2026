package Task7.main.java.ru.itis.shop.user.infrastructure.persistence.jdbc;

import ru.itis.shop.infrastructure.persistence.jbbc.RowMapper;
import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final DataSource dataSource;

    private final RowMapper<User> userRowMapper = row -> new User(
            row.getInt("id"),
            row.getString("name"),
            row.getString("email"),
            row.getString("password"),
            row.getString("profileDescription")
    );

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save (User user) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("insert into account(name, email, password, profileDescription) values(?, ?, ?, ?)")) {

                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getEmail());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getProfileDescription());

                int affectedRowsCount = preparedStatement.executeUpdate();

                if (affectedRowsCount != 1) {
                    throw new SQLException("Can't insert");
                }
                System.out.println("Пользователь успешно добавлен");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String findEmail) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from account where email = ?"
            )){

                preparedStatement.setString(1, findEmail);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return Optional.of(userRowMapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(Integer findId) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from account where id = ?"
            )){

                preparedStatement.setInt(1, findId);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    return Optional.of(userRowMapper.mapRow(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public void updateData(String findEmail, String newProfileDescription) {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "update account set profileDescription = ? where email = ?"
            )) {
                preparedStatement.setString(1, newProfileDescription);
                preparedStatement.setString(2, findEmail);

                int affectedRowsCount = preparedStatement.executeUpdate();

                if (affectedRowsCount != 1) {
                    throw new SQLException("Пользователь с таким email не найден");
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Optional<User>> findByProfileDescription(String newProfileDescription) {
        List<Optional<User>> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from account where profileDescription = ?"
            )) {

                preparedStatement.setString(1, newProfileDescription);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    users.add(Optional.of(userRowMapper.mapRow(resultSet)));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account")) {
                    while (resultSet.next()) {
                        users.add(userRowMapper.mapRow(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }
}
