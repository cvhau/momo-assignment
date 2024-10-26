package com.cvhau.assignment.model;

import java.io.Serial;
import java.io.Serializable;

public class Provider implements Serializable {
    @Serial
    private static final long serialVersionUID = 3898856095603227006L;

    private final Long id;
    private final String name;

    public Provider(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
