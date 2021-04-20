package org.deiv.infrastructure.persistence;

import org.deiv.domain.entity.Wallet;
import org.deiv.domain.repository.WalletRepositoryPort;

import javax.inject.Singleton;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Singleton
public class InMemoryWalletRepository implements WalletRepositoryPort {

    private ConcurrentHashMap<Long, Wallet> wallets = new ConcurrentHashMap<>();

    @Override
    public Wallet add(Wallet wallet)
    {
        wallet.id(wallets.mappingCount())
              .balance(new BigDecimal(0))
              .number(wallets.mappingCount());

        wallets.put(wallet.getId(), wallet);

        return wallet;
    }

    @Override
    public void update(Wallet wallet)
    {
        wallets.put(wallet.getId(), wallet);
    }

    @Override
    public Optional<Wallet> findById(long id)
    {
        return Optional.of(wallets.get(id));
    }

    @Override
    public Optional<Wallet> findByUserId(long userId)
    {
        return wallets.values()
                    .stream()
                    .filter(wallet -> wallet.getUserId().equals(userId))
                    .findFirst();
    }

}
