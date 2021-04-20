package org.deiv.domain.repository;

import org.deiv.domain.entity.Movement;

import javax.inject.Singleton;
import java.util.List;

@Singleton
public interface MovementRepositoryPort {

    Movement add(Movement movement);

    List<Movement> getAll(long walletId);

    Movement delete(Movement movement);
}
