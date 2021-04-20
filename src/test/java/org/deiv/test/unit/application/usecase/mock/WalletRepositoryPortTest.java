package org.deiv.test.unit.application.usecase.mock;

import org.deiv.domain.entity.Wallet;
import org.deiv.domain.repository.WalletRepositoryPort;

import java.math.BigDecimal;
import java.util.Optional;

public class WalletRepositoryPortTest implements WalletRepositoryPort {

    private Wallet testWallet = new Wallet()
        .balance(new BigDecimal(1000))
        .id(1L)
        .userId(1L);

    @Override
    public Wallet add(Wallet wallet)
    {
        return null;
    }

    @Override
    public void update(Wallet wallet)
    {
        if (testWallet.getId().equals(wallet.getId())) {
            testWallet = wallet;
        }
    }

    @Override
    public Optional<Wallet> findById(long id)
    {
        return Optional.empty();
    }

    @Override
    public Optional<Wallet> findByUserId(long userId)
    {
        if (testWallet.getUserId().equals(userId)) {
            return Optional.of(testWallet);
        }

        return Optional.empty();
    }

}
