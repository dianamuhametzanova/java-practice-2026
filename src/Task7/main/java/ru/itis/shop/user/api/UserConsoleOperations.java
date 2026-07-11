package Task7.main.java.ru.itis.shop.user.api;

import ru.itis.shop.user.api.dto.UserDto;
import ru.itis.shop.user.application.UserService;

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
            case "6": {
                findByProfileDescription();
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
        System.out.println("4. Обновить описание пользователя по почте");
        System.out.println("5. Показать информацию о всех пользователях");
        System.out.println("6. Показать информацию о пользователях с заданным profileDescription");
        System.out.println("0. Выход");
    }

    private void signUp() {
        System.out.println("Сейчас будем регестрировать пользователя");
        System.out.println("Введите name:");
        String name = scanner.nextLine();
        System.out.println("Введите email:");
        String email = scanner.nextLine();
        System.out.println("Введите password:");
        String password = scanner.nextLine();
        System.out.println("Введите описание профиля:");
        String profileDescription = scanner.nextLine();

        userService.signUp(name, email, password, profileDescription);
        System.out.println("Вы зарегистрировались");
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
            System.out.println("email или password не верны");
        }
    }

    private void findUserById() {
        System.out.println("Введите id:");
        Integer id = scanner.nextInt();
        UserDto user = userService.findUserById(id);
        if (user != null) {
            System.out.println(user.getName());
        } else {
            System.out.println("Пользователь не найден");
        }
    }

    private void findByProfileDescription() {
        System.out.println("Введите profileDescription");
        String profileDescription = scanner.nextLine();
        List<UserDto> users = userService.findByProfileDescription(profileDescription);
        users.forEach(a -> System.out.println(a.getName()));
    }

    private void updateData() {
        System.out.println("Введите email");
        String email = scanner.nextLine();
        UserDto user = userService.findUserByEmail(email);
        if (user != null) {
            System.out.println("Введите новое описание профиля");
            String profileDescription = scanner.nextLine();
            userService.updateData(email, profileDescription);
            System.out.println("Данные были обновлены");
        } else {
            System.out.println("Пользователя с такоим email не существет");
        }

    }

    private void findAll() {
        System.out.println("Все пользователи:");
        List<UserDto> users = userService.findAll();
        users.forEach(a -> System.out.println(a.toString()));
    }
}