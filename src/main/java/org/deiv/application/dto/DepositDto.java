package org.deiv.application.dto;

import java.math.BigDecimal;

public class DepositDto {

    private String userName;
    private BigDecimal amount;

    public String getUserName()
    {
        return userName;
    }

    public DepositDto userName(String userName)
    {
        this.userName = userName;
        return this;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public DepositDto amount(BigDecimal amount)
    {
        this.amount = amount;
        return this;
    }

}
