package org.deiv.domain.entity;

import java.math.BigDecimal;

public class User {
    private Long id;
    private String name;

    public User id(Long id)
    {
        this.id = id;
        return this;
    }

    public User name(String name)
    {
        this.name = name;
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

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
