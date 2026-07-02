package Task2.ru.itis.shop.user.infrastructure.persistence;

import Task2.ru.itis.shop.user.domain.User;
import Task2.ru.itis.shop.user.repository.UserRepository;

import java.io.*;
import java.util.Optional;
import java.util.UUID;

public class UserFileRepository implements UserRepository {

    private final String fileName;

    private final UserMapper userMapper;

    public UserFileRepository(String fileName, UserMapper userMapper) {
        this.fileName = fileName;
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String id = UUID.randomUUID().toString();
            user.setId(id);
            writer.write(userMapper.toLine(user));
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {

                User user = userMapper.fromLine(line);

                if (user.getEmail().equals(email)) {
                    return Optional.of(user);
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts[0].equals(id)) {
                    return Optional.of(userMapper.fromLine(line));
                }
            }

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        return Optional.empty();
    }
}