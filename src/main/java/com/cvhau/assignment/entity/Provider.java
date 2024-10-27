package com.cvhau.assignment.entity;

import java.io.Serial;
import java.io.Serializable;

public class Provider implements Entity<Long>, Serializable {
    @Serial
    private static final long serialVersionUID = 3898856095603227006L;

    private Long id;
    private String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
