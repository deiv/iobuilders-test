package org.deiv.domain.repository;

import org.deiv.domain.entity.User;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public interface UserRepositoryPort {

    User add(User user);

    Optional<User> findByName(String name);

    Optional<User> findByUserIdAndId(Long userId);
}
