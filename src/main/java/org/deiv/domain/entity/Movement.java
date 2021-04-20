package org.deiv.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Movement {

    public enum MovementType {
        DEPOSIT,
        TRANSFER
    }

    private Long id;
    private Long walletId;
    private MovementType type;
    private BigDecimal amountChange;
    private LocalDateTime createdAt;

    public Movement id(long id)
    {
        this.id = id;
        return this;
    }

    public Movement walletId(long walletId)
    {
        this.walletId = walletId;
        return this;
    }

    public Movement type(MovementType type)
    {
        this.type = type;
        return this;
    }

    public Movement amountChange(BigDecimal amountChange)
    {
        this.amountChange = amountChange;
        return this;
    }

    public Movement createdAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
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

    public Long getWalletId()
    {
        return walletId;
    }

    public void setWalletId(Long walletId)
    {
        this.walletId = walletId;
    }

    public MovementType getType()
    {
        return type;
    }

    public void setType(MovementType type)
    {
        this.type = type;
    }

    public BigDecimal getAmountChange()
    {
        return amountChange;
    }

    public void setAmountChange(BigDecimal amountChange)
    {
        this.amountChange = amountChange;
    }

    public LocalDateTime getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt)
    {
        this.createdAt = createdAt;
    }
}
