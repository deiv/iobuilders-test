package org.deiv.application.usecase;

import org.deiv.domain.entity.User;
import org.deiv.domain.entity.Wallet;
import org.deiv.domain.error.InvalidDataException;
import org.deiv.domain.repository.UserRepositoryPort;
import org.deiv.domain.repository.WalletRepositoryPort;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class GetWalletUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final WalletRepositoryPort walletRepositoryPort;

    public GetWalletUseCase(WalletRepositoryPort walletRepositoryPort, UserRepositoryPort userRepositoryPort) {
        this.walletRepositoryPort = walletRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
    }

    public Wallet getWallet(String userName) throws InvalidDataException
    {
        Optional<User> user = userRepositoryPort.findByName(userName);

        if (!user.isPresent()) {
            throw new InvalidDataException();
        }

        Optional<Wallet> wallet = walletRepositoryPort.findByUserId(user.get().getId());

        if (!wallet.isPresent()) {
            throw new InvalidDataException();
        }

        return wallet.get();
    }

    private void validate(String userName) throws InvalidDataException
    {
        if (userName == null || userName.isEmpty()) {
            throw new InvalidDataException("el usuario debe tener nombre");
        }
    }
}
