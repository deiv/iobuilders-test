package org.deiv.factories;

import com.github.javafaker.Faker;
import factoryduke.FactoryDuke;
import factoryduke.TFactory;
import factoryduke.generators.Generators;
import factoryduke.generators.SequenceGenerator;
import org.deiv.domain.entity.Movement;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MovementFactory implements TFactory {

    SequenceGenerator ids = Generators.sequence();

    @Override
    public void define() {
        Faker faker = new Faker(new Locale("es"));

        FactoryDuke.define(Movement.class, movement -> {
            Long nextId = ids.nextValue();

            movement
                .id(nextId)
                .walletId(nextId)
                .type(nextId % 2 == 0 ? Movement.MovementType.DEPOSIT : Movement.MovementType.TRANSFER)
                .amountChange(new BigDecimal(faker.number().randomDouble(2, 1, 1000)))
                .createdAt(faker.date().past(6, TimeUnit.DAYS).toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime());
        });
    }
}
