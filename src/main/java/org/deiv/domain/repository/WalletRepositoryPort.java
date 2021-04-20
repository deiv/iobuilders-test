package org.deiv.domain.repository;

import org.deiv.domain.entity.Wallet;

import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public interface WalletRepositoryPort {

    Wallet add(Wallet wallet);

    void update(Wallet wallet);

    Optional<Wallet> findById(long id);

    Optional<Wallet> findByUserId(long userId);
}
