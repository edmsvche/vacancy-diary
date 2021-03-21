package com.example.vacancydiary.model.enums;

import com.example.vacancydiary.model.Permission;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Roles {
    USER(Set.of(Permission.USERS_READ, Permission.USERS_WRITE, Permission.USERS_UPDATE)),
    ADMIN(Set.of(Permission.USERS_READ, Permission.USERS_WRITE, Permission.USERS_UPDATE));

    Roles(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    private final Set<Permission> permissions;

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
