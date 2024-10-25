package com.cvhau.assignment.model;

public class Provider {
    private final long id;
    private final String name;

    public Provider(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
