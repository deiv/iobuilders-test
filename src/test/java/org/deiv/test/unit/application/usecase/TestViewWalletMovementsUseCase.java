package org.deiv.test.unit.application.usecase;

import factoryduke.FactoryDuke;
import org.deiv.application.usecase.GetWalletUseCase;
import org.deiv.application.usecase.ViewWalletMovementsUseCase;
import org.deiv.domain.entity.Movement;
import org.deiv.domain.error.InvalidDataException;
import org.deiv.test.unit.application.usecase.mock.MovementRepositoryPortRandomTest;
import org.deiv.test.unit.application.usecase.mock.UserRepositoryPortTest;
import org.deiv.test.unit.application.usecase.mock.WalletRepositoryPortTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestViewWalletMovementsUseCase {

    @BeforeEach
    public void setUp() {
        FactoryDuke.load("org.deiv.factories");
    }

    @Test
    public void testMakeDepositOk() throws InvalidDataException
    {
        GetWalletUseCase getWalletUseCase = new GetWalletUseCase(
            new WalletRepositoryPortTest(),
            new UserRepositoryPortTest());
        ViewWalletMovementsUseCase useCase = new ViewWalletMovementsUseCase(
            new MovementRepositoryPortRandomTest(),
            getWalletUseCase);

        List<Movement> outMovements = useCase.getMovements("prueba");

        Assertions.assertEquals(100, outMovements.size());
        Assertions.assertEquals(1, outMovements.get(0).getId());
        Assertions.assertEquals(100, outMovements.get(99).getId());
    }
}
