package org.deiv.test.unit.application.usecase.mock;

import org.deiv.domain.entity.Movement;
import org.deiv.domain.repository.MovementRepositoryPort;

import java.util.List;

public class MovementRepositoryPortTest implements MovementRepositoryPort {

    @Override
    public Movement add(Movement movement)
    {
        return movement;
    }

    @Override
    public List<Movement> getAll(long walletId)
    {
        return null;
    }

    @Override
    public Movement delete(Movement movement)
    {
        return null;
    }

}