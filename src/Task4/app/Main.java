package Task4.app;

import Task4.user.api.UserConsoleOperations;
import Task4.user.application.UserService;
import Task4.user.infrastructure.persistence.UserFileRepository;
import Task4.user.infrastructure.persistence.UserMapper;
import Task4.user.infrastructure.persistence.UserRepositoryJdbcImpl;

public class Main {
    public static void main(String[] args) {
        UserRepositoryJdbcImpl userRepositoryJdbc = new UserRepositoryJdbcImpl("jdbc:postgresql://localhost:5432/java_2026", "diana", "56971729");
        UserService userService = new UserService(userRepositoryJdbc);

        UserConsoleOperations operations = new UserConsoleOperations(userService);

        while (true) {
            operations.showMenu();
        }
    }
}
