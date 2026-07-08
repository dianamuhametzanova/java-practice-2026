package Task4.user.application;

import Task4.user.domain.User;
import Task4.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String email, String password, int age) {
        User user = new User(email, password, age);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    public User findUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public User findUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElse(null);
    }

    public void updateData(String email, int age) {
        userRepository.updateData(email, age);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}