package org.deiv.test.unit.application.usecase;

import org.deiv.application.dto.DepositDto;
import org.deiv.application.usecase.GetWalletUseCase;
import org.deiv.application.usecase.MakeDepositUseCase;
import org.deiv.domain.entity.Movement;
import org.deiv.domain.entity.Wallet;
import org.deiv.domain.error.InvalidDataException;
import org.deiv.test.unit.application.usecase.mock.MovementRepositoryPortTest;
import org.deiv.test.unit.application.usecase.mock.UserRepositoryPortTest;
import org.deiv.test.unit.application.usecase.mock.WalletRepositoryPortTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestMakeDepositUseCase {

    @Test
    public void testMakeDepositOk() throws InvalidDataException
    {
        DepositDto depositDto = new DepositDto()
            .userName("prueba")
            .amount(new BigDecimal(100));

        GetWalletUseCase getWalletUseCase = new GetWalletUseCase(
            new WalletRepositoryPortTest(),
            new UserRepositoryPortTest());
        MakeDepositUseCase useCase = new MakeDepositUseCase(
            new WalletRepositoryPortTest(),
            new MovementRepositoryPortTest(),
            getWalletUseCase);

        Movement outMovement = useCase.makeDeposit(depositDto);

        Assertions.assertEquals(new BigDecimal(100), outMovement.getAmountChange());
        Assertions.assertEquals(Movement.MovementType.DEPOSIT, outMovement.getType());

        Wallet wallet = getWalletUseCase.getWallet("prueba");
        Assertions.assertEquals(new BigDecimal(1100), wallet.getBalance());
    }

    @Test
    public void testMakeDepositKoByUnknownUser() throws InvalidDataException
    {
        DepositDto depositDto = new DepositDto()
            .userName("pruebaNotExistent")
            .amount(new BigDecimal(100));

        GetWalletUseCase getWalletUseCase = new GetWalletUseCase(
            new WalletRepositoryPortTest(),
            new UserRepositoryPortTest());
        MakeDepositUseCase useCase = new MakeDepositUseCase(
            new WalletRepositoryPortTest(),
            new MovementRepositoryPortTest(),
            getWalletUseCase);

        Assertions.assertThrows(InvalidDataException.class, () -> {
            Movement outMovement = useCase.makeDeposit(depositDto);
        });
    }
}
