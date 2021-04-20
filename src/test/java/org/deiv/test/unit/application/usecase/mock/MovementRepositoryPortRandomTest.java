package org.deiv.test.unit.application.usecase.mock;

import factoryduke.FactoryDuke;
import org.deiv.domain.entity.Movement;
import org.deiv.domain.repository.MovementRepositoryPort;

import java.util.ArrayList;
import java.util.List;

public class MovementRepositoryPortRandomTest implements MovementRepositoryPort {

    @Override
    public Movement add(Movement movement)
    {
        return movement;
    }

    @Override
    public List<Movement> getAll(long walletId)
    {
        if (walletId == 1L) {
            return FactoryDuke.build(Movement.class).times(100).toList();
        }

        return new ArrayList<>();
    }

    @Override
    public Movement delete(Movement movement)
    {
        return null;
    }
}
