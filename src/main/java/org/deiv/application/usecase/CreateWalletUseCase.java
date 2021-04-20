package org.deiv.application.usecase;

import org.deiv.application.dto.UserDto;
import org.deiv.domain.entity.User;
import org.deiv.domain.entity.Wallet;
import org.deiv.domain.error.InvalidDataException;
import org.deiv.domain.error.WalletAlreadyExistsException;
import org.deiv.domain.repository.UserRepositoryPort;
import org.deiv.domain.repository.WalletRepositoryPort;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class CreateWalletUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final WalletRepositoryPort walletRepositoryPort;

    CreateWalletUseCase(WalletRepositoryPort walletRepositoryPort, UserRepositoryPort userRepositoryPort) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    public Wallet addWallet(UserDto dto) throws InvalidDataException, WalletAlreadyExistsException
    {
        validate(dto);

        Optional<User> user = userRepositoryPort.findByName(dto.getName());

        if (!user.isPresent()) {
            throw new InvalidDataException();
        }

        long userId = user.get().getId();

        if (walletRepositoryPort.findByUserId(userId).isPresent()) {
            throw new WalletAlreadyExistsException(dto.getName());
        }

        return walletRepositoryPort.add(new Wallet().userId(userId));
    }

    private void validate(UserDto user) throws InvalidDataException
    {
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new InvalidDataException("el usuario debe tener nombre");
        }
    }
}
