package Task6.ru.itis.shop.user.application;

import Task6.ru.itis.shop.user.api.dto.UserDto;
import Task6.ru.itis.shop.user.domain.User;
import Task6.ru.itis.shop.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(String name, String email, String password, String profileDescription) {
        User user = new User(name, email, password, profileDescription);
        userRepository.save(user);
    }

    public boolean signIn(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    public User findUserById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public UserDto findUserByEmail(String email) {
        Optional<UserDto> userOptional = userRepository.findByEmail(email);
        return userOptional.orElse(null);
    }

    public void updateData(String email, String profileDescription) {

    }

    public List<User> findByProfileDescription(String profileDescription) {
        List<Optional<User>> usersOptional = userRepository.findByProfileDescription(profileDescription);
        return usersOptional.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}