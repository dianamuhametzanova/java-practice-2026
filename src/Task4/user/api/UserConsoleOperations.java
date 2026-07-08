package Task4.user.api;

import Task4.user.application.UserService;
import Task4.user.domain.User;

import java.util.List;
import java.util.Scanner;

public class UserConsoleOperations {

    private final UserService userService;
    private final Scanner scanner;

    public UserConsoleOperations(UserService userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        printMainMenu();

        String command = scanner.nextLine();

        switch (command) {
            case "1": {
                signUp();
            }
            break;
            case "2": {
                signIn();

            }
            break;
            case "3": {
                findUserById();
            }
            break;
            case "4": {
                updateData();
            }
            break;
            case "5": {
                findAll();
            }
            break;
            case "0": {
                System.exit(0);
            }
        }
    }


    private static void printMainMenu() {
        System.out.println("1. Регистрация пользователя");
        System.out.println("2. Вход в состему");
        System.out.println("3. Найти пользователя по id");
        System.out.println("4. Обновить данные пользователя");
        System.out.println("5. Показать всех пользователей");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регестрировать пользователя");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите возраст:");
        int age = scanner.nextInt();

        userService.signUp(email, password, age);
    }


    private void signIn() {
        System.out.println("Вы можете войти в приложение");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();

        if (userService.signIn(email, password)) {
            System.out.println("Вы вошли в приложение");
        } else {
            System.out.println("email или пароль не верны");
        }
    }

    private void findUserById() {
        System.out.println("Введите id:");
        String id = scanner.nextLine();
        User user = userService.findUserById(id);
        if (user != null) {
            System.out.println(user.getEmail());
        } else {
            System.out.println("Пользователь не найден");
        }
    }

    private void updateData() {
        System.out.println("Введите email");
        String email = scanner.nextLine();
        User user = userService.findUserByEmail(email);
        if (user != null) {
            System.out.println("Введите новое описание профиля");
            int age = scanner.nextInt();
            userService.updateData(email, age);
            System.out.println("Данные были обновлены");
        } else {
            System.out.println("Пользователя с такоим email не существет");
        }
    }

    public void findAll() {
        System.out.println("Все пользователи:");
        List<User> users = userService.findAll();
        users.forEach(a -> System.out.println(a.toString()));
    }
}