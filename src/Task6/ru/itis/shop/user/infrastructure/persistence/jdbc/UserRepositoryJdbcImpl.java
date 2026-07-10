package Task6.ru.itis.shop.user.infrastructure.persistence.jdbc;

import Task6.ru.itis.shop.infrastructure.persistence.jbbc.RowMapper;
import Task6.ru.itis.shop.user.api.dto.UserDto;
import Task6.ru.itis.shop.user.domain.User;
import Task6.ru.itis.shop.user.repository.UserRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    private final RowMapper<UserDto> userDtoRowMapper = row -> new UserDto(
            row.getInt("id"),
            row.getString("name"),
            row.getString("email"),
            row.getString("profileDescription")
    );

    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save (User user) {
    }

    @Override
    public Optional<UserDto> findByEmail(String findEmail) {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account where email = findEmail")) {
                    if (resultSet.next()) {
                        return Optional.of(userDtoRowMapper.mapRow(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<UserDto> findById(Integer findId) {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account where id = findId")) {
                    if (resultSet.next()) {
                        return Optional.of(userDtoRowMapper.mapRow(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Optional<User>> findByProfileDescription(String profileDescription) {
        List<Optional<User>> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery("select * from account where profileDescription = 'student'")) {
                    while (resultSet.next()) {
                        users.add(Optional.of(userRowMapper.mapRow(resultSet)));
                    }
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
