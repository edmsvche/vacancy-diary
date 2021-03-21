package com.example.vacancydiary.model;

public enum Permission {
    USERS_READ("users:read"),
    USERS_WRITE("users:write"),
    USERS_UPDATE("users:update");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
