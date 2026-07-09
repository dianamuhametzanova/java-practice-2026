package Task5.ru.itis.shop.user.api;

import Task5.ru.itis.shop.user.application.UserService;
import Task5.ru.itis.shop.user.domain.User;

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
                findByProfileDescription();
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
        System.out.println("4. Показать информацию о пользователях с заданным profileDescription");
        System.out.println("5. Показать всех пользователей");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регестрировать пользователя");
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();
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

    }

    private void findByProfileDescription() {
        System.out.println("Введите profileDescription");
        String profileDescription = scanner.nextLine();
        List<User> users = userService.findByProfileDescription(profileDescription);
        users.forEach(a -> System.out.println(a.getName()));
    }

    private void findAll() {
        System.out.println("Все пользователи:");
        List<User> users = userService.findAll();
        users.forEach(a -> System.out.println(a.toString()));
    }
}