package org.deiv.infrastructure.persistence;

import org.deiv.domain.entity.Movement;
import org.deiv.domain.repository.MovementRepositoryPort;

import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Singleton
public class InMemoryMovementRepository implements MovementRepositoryPort {

    private ConcurrentHashMap<Long, Movement> movements = new ConcurrentHashMap<>();


    @Override
    public Movement add(Movement movement)
    {
        movement.setId(movements.mappingCount());
        movements.put(movement.getId(), movement);
        return movement;
    }

    @Override
    public Movement delete(Movement movement)
    {
        movements.remove(movement.getId());

        return movement;
    }


    @Override
    public List<Movement> getAll(long walletId)
    {
        return movements.values()
                    .stream()
                    .filter(movement -> movement.getWalletId().equals(walletId))
                    .sorted((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt()) )
                    .collect(Collectors.toList());
    }

}
