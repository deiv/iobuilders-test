package org.deiv.application.dto;

import java.math.BigDecimal;

public class TransferDto {

    private String userName;
    private String destUserName;
    private BigDecimal amount;

    public String getUserName()
    {
        return userName;
    }

    public TransferDto userName(String userName)
    {
        this.userName = userName;
        return this;
    }

    public String getDestUserName()
    {
        return destUserName;
    }

    public TransferDto destUserName(String destUserName)
    {
        this.destUserName = destUserName;
        return this;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public TransferDto amount(BigDecimal amount)
    {
        this.amount = amount;
        return this;
    }

}
