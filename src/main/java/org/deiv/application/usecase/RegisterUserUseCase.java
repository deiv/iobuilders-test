package org.deiv.application.usecase;

import org.deiv.application.dto.UserDto;
import org.deiv.domain.entity.User;
import org.deiv.domain.error.AccountAlreadyExistsException;
import org.deiv.domain.error.InvalidDataException;
import org.deiv.domain.repository.UserRepositoryPort;

import javax.inject.Singleton;

@Singleton
public class RegisterUserUseCase {

    private final UserRepositoryPort repository;

    RegisterUserUseCase(UserRepositoryPort repository) {
        this.repository = repository;
    }

    public void registerUser(UserDto dto) throws InvalidDataException, AccountAlreadyExistsException
    {
        validate(dto);

        if (repository.findByName(dto.getName()).isPresent()) {
            throw new AccountAlreadyExistsException(dto.getName());
        }

        repository.add(new User().name(dto.getName()));
    }

    private void validate(UserDto user) throws InvalidDataException
    {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new InvalidDataException("el usuario debe tener nombre");
        }
    }
}
