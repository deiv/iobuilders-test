package org.deiv.domain.entity;

import java.math.BigDecimal;

public class Transfer {

    private long id;
    private long movementId;
    private long fromWalletId;
    private long toWalletId;
    private BigDecimal amount;
}
