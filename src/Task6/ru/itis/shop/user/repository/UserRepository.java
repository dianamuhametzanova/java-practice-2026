package Task6.ru.itis.shop.user.repository;

import Task6.ru.itis.shop.user.api.dto.UserDto;
import Task6.ru.itis.shop.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save (User user);

    Optional<UserDto> findByEmail (String email);

    Optional<UserDto> findById(Integer id);

    List<Optional<User>> findByProfileDescription(String profileDescription);

    List<User> findAll();
}