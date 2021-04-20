package org.deiv.application.usecase;

import org.deiv.domain.entity.Movement;
import org.deiv.domain.entity.Wallet;
import org.deiv.domain.error.InvalidDataException;
import org.deiv.domain.repository.MovementRepositoryPort;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ViewWalletMovementsUseCase {

    private GetWalletUseCase getWalletUseCase;

    private final MovementRepositoryPort movementRepositoryPort;

    public ViewWalletMovementsUseCase(
        MovementRepositoryPort movementRepositoryPort,
        GetWalletUseCase getWalletUseCase)
    {
        this.movementRepositoryPort = movementRepositoryPort;
        this.getWalletUseCase = getWalletUseCase;
    }

    public List<Movement> getMovements(String userName) throws InvalidDataException
    {
        validate(userName);

        Wallet wallet = getWalletUseCase.getWallet(userName);

        return movementRepositoryPort.getAll(wallet.getId());
    }

    private void validate(String userName) throws InvalidDataException
    {
        if (userName == null || userName.isEmpty()) {
            throw new InvalidDataException("el usuario debe tener nombre");
        }
    }
}
