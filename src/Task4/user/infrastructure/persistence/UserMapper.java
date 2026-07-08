package Task4.user.infrastructure.persistence;

import Task4.user.domain.User;

public class UserMapper {
    public User fromLine(String line) {
        String[] parts = line.split("\\|");

        User user = new User(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));

        return user;
    }

    public String toLine(User user) {
        return user.getId() + "|" +
                user.getEmail() + "|" +
                user.getPassword() + "|" +
                user.getAge();
    }
}
