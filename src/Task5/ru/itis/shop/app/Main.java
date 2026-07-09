package Task5.ru.itis.shop.app;

import Task4.user.domain.User;
import Task5.ru.itis.shop.infrastructure.persistence.jbbc.DriverManagerDataSource;
import Task5.ru.itis.shop.user.api.UserConsoleOperations;
import Task5.ru.itis.shop.user.application.UserService;
import Task5.ru.itis.shop.user.infrastructure.persistence.jdbc.UserRepositoryJdbcImpl;
import Task5.ru.itis.shop.user.repository.UserRepository;

import javax.sql.DataSource;

public class Main {
    public static void main(String[] args) {
        DataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/shob_db",
                "diana", "56971729");

        UserRepository userRepository = new UserRepositoryJdbcImpl(dataSource);
        UserService userService = new UserService(userRepository);
        UserConsoleOperations userConsoleOperations = new UserConsoleOperations(userService);

        while (true) {
            userConsoleOperations.showMenu();
        }
    }
}