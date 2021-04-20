package org.deiv.infrastructure.persistence;

import org.deiv.domain.entity.User;
import org.deiv.domain.repository.UserRepositoryPort;

import javax.inject.Singleton;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class InMemoryUserRepository implements UserRepositoryPort {

    private ConcurrentHashMap<Long, User> users = new ConcurrentHashMap<>();

    @Override
    public User add(User account)
    {
        account.setId(users.mappingCount());
        users.put(account.getId(), account);
        return account;
    }

    @Override
    public Optional<User> findByName(String name) {
        return users.values()
                    .stream()
                    .filter(account -> account.getName().equals(name))
                    .findFirst();
    }

    @Override
    public Optional<User> findByUserIdAndId(Long userId)
    {
        return Optional.empty();
    }
}
