package com.codurance.twitterKata.valueObject;

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return name().equals(user.name());
    }

    @Override
    public int hashCode() {
        return name().hashCode();
    }
}
