package Task4.user.infrastructure.persistence;

import Task4.user.domain.User;
import Task4.user.repository.UserRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public void updateData(String email, int age) {

        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {

                User user = userMapper.fromLine(line);

                if (user.getEmail().equals(email)) {
                    user.setAge(age);
                }
                users.add(user);
            }

        } catch (IOException e1) {
            throw new IllegalStateException(e1);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            users.forEach(u -> {
                try {
                    writer.write(userMapper.toLine(u));
                    writer.newLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}