package com.phisoft.bookstorekotlin.authentication;

public enum Permission {

    READ("read"),WRITE("write"),CREATE("create");
    private final String name;
    Permission(String name) {
        this.name=name;
    }

    public String getPermission() {
        return name;
    }
}
