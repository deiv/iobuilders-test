package org.deiv.domain.entity;

import java.math.BigDecimal;

public class Wallet {

    private Long id;
    private Long number;
    private Long userId;
    private BigDecimal balance;

    public Wallet id(Long id)
    {
        this.id = id;
        return this;
    }

    public Wallet number(Long number)
    {
        this.number = number;
        return this;
    }

    public Wallet userId(Long userId)
    {
        this.userId = userId;
        return this;
    }

    public Wallet balance(BigDecimal balance)
    {
        this.balance = balance;
        return this;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getNumber()
    {
        return number;
    }

    public void setNumber(Long number)
    {
        this.number = number;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public BigDecimal getBalance()
    {
        return balance;
    }

    public void setBalance(BigDecimal balance)
    {
        this.balance = balance;
    }
}
