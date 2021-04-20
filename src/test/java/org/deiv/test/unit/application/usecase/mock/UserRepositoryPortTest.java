package org.deiv.test.unit.application.usecase.mock;

import org.deiv.domain.entity.User;
import org.deiv.domain.repository.UserRepositoryPort;

import java.util.Optional;

public class UserRepositoryPortTest implements UserRepositoryPort {

    private User testUser = new User().name("prueba").id(1L);

    @Override
    public User add(User user)
    {
        return null;
    }

    @Override
    public Optional<User> findByName(String name)
    {
        if (testUser.getName() == name) {
            return Optional.of(testUser);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findByUserIdAndId(Long userId)
    {
        return Optional.empty();
    }

}
