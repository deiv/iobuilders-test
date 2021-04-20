package org.deiv.application.usecase;

import org.deiv.application.dto.TransferDto;
import org.deiv.domain.entity.Movement;
import org.deiv.domain.entity.Wallet;
import org.deiv.domain.error.InvalidDataException;
import org.deiv.domain.error.NotEnoughBalance;
import org.deiv.domain.repository.MovementRepositoryPort;
import org.deiv.domain.repository.WalletRepositoryPort;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Singleton
public class MakeTransferUseCase {

    private GetWalletUseCase getWalletUseCase;

    private final WalletRepositoryPort walletRepositoryPort;
    private final MovementRepositoryPort movementRepositoryPort;

    MakeTransferUseCase(
        WalletRepositoryPort walletRepositoryPort,
        MovementRepositoryPort movementRepositoryPort,
        GetWalletUseCase getWalletUseCase)
    {
        this.walletRepositoryPort = walletRepositoryPort;
        this.movementRepositoryPort = movementRepositoryPort;
        this.getWalletUseCase = getWalletUseCase;
    }

    public Movement makeTransfer(TransferDto transfer) throws InvalidDataException, NotEnoughBalance
    {
        validate(transfer);

        Wallet originWallet = getWalletUseCase.getWallet(transfer.getUserName());
        Wallet destWallet = getWalletUseCase.getWallet(transfer.getDestUserName());

        if (originWallet.getBalance().subtract(transfer.getAmount()).signum() < 0) {
            throw new NotEnoughBalance();
        }

        /*
         * XXX: TODO: para la transaccionalidad, simplemente nos basamos en:
         *              - si falla la inserción del movimiento, no hay rollback.. todo ok.
         *              - si falla el update de la cartera de origen, eliminamos el movimiento previamente insertado.
         *              - si falla el update de la cartera destino, eliminamos el movimiento previamente insertado y
         *                   restauramos el saldo.
         */
        Movement movementSrc = new Movement()
            .amountChange(transfer.getAmount().negate())
            .type(Movement.MovementType.TRANSFER)
            .walletId(originWallet.getId())
            .createdAt(LocalDateTime.now());
        movementRepositoryPort.add(movementSrc);

        Movement movementDest = new Movement()
            .amountChange(transfer.getAmount())
            .type(Movement.MovementType.TRANSFER)
            .walletId(destWallet.getId())
            .createdAt(LocalDateTime.now());
        movementRepositoryPort.add(movementDest);

        originWallet.setBalance(originWallet.getBalance().subtract(transfer.getAmount()));
        destWallet.setBalance(destWallet.getBalance().add(transfer.getAmount()));

        try {
            walletRepositoryPort.update(originWallet);

        } catch (Exception ex) {
            movementRepositoryPort.delete(movementSrc);
            movementRepositoryPort.delete(movementDest);
            throw  ex;
        }

        try {
            walletRepositoryPort.update(destWallet);

        } catch (Exception ex) {
            originWallet.getBalance().add(transfer.getAmount());
            walletRepositoryPort.update(originWallet);
            movementRepositoryPort.delete(movementSrc);
            movementRepositoryPort.delete(movementDest);
            throw  ex;
        }

        return movementSrc;
    }

    private void validate(TransferDto transfer) throws InvalidDataException
    {
        if (transfer.getUserName() == null || transfer.getUserName().isEmpty()) {
            throw new InvalidDataException("el usuario de origin debe tener nombre");
        }

        if (transfer.getDestUserName() == null || transfer.getDestUserName().isEmpty()) {
            throw new InvalidDataException("el usuario destino debe tener nombre");
        }

        if (transfer.getAmount() == null) {
            throw new InvalidDataException("la cantidad es obligatoria");
        }
    }
}
