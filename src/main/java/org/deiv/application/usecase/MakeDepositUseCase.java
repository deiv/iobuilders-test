package org.deiv.application.usecase;

import org.deiv.application.dto.DepositDto;
import org.deiv.domain.entity.Movement;
import org.deiv.domain.entity.Wallet;
import org.deiv.domain.error.InvalidDataException;
import org.deiv.domain.repository.MovementRepositoryPort;
import org.deiv.domain.repository.WalletRepositoryPort;

import javax.inject.Singleton;
import java.time.LocalDateTime;

@Singleton
public class MakeDepositUseCase {

    private GetWalletUseCase getWalletUseCase;

    private final WalletRepositoryPort walletRepositoryPort;
    private final MovementRepositoryPort movementRepositoryPort;

    public MakeDepositUseCase(
        WalletRepositoryPort walletRepositoryPort,
        MovementRepositoryPort movementRepositoryPort,
        GetWalletUseCase getWalletUseCase)
    {
        this.walletRepositoryPort = walletRepositoryPort;
        this.movementRepositoryPort = movementRepositoryPort;
        this.getWalletUseCase = getWalletUseCase;
    }

    public Movement makeDeposit(DepositDto depositDto) throws InvalidDataException
    {
        validate(depositDto);

        Wallet wallet = getWalletUseCase.getWallet(depositDto.getUserName());

        /*
         * XXX: TODO: para la transaccionalidad, simplemente nos basamos en:
         *              - si falla la inserción del movimiento, no hay rollback.. todo ok
         *              - si falla el update de la cartera, eliminamos el movimiento previamente insertado
         */
        Movement movement = new Movement()
            .amountChange(depositDto.getAmount())
            .type(Movement.MovementType.DEPOSIT)
            .walletId(wallet.getId())
            .createdAt(LocalDateTime.now());
        movementRepositoryPort.add(movement);

        wallet.setBalance(wallet.getBalance().add(depositDto.getAmount()));

        try {
            walletRepositoryPort.update(wallet);

        } catch (Exception ex) {
            movementRepositoryPort.delete(movement);
            throw  ex;
        }

        return movement;
    }

    private void validate(DepositDto depositDto) throws InvalidDataException
    {
        if (depositDto.getUserName() == null || depositDto.getUserName().isEmpty()) {
            throw new InvalidDataException("el usuario debe tener nombre");
        }

        if (depositDto.getAmount() == null) {
            throw new InvalidDataException("la cantidad es obligatoria");
        }
    }
}
