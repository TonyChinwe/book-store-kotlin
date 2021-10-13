package com.phisoft.bookstorekotlin.authentication;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {

    ADMIN(Arrays.asList(Permission.READ,Permission.WRITE,Permission.CREATE)),
    MANAGER(Arrays.asList(Permission.READ,Permission.WRITE));
    private final List<Permission> permissionList;
    Role(List<Permission>permissionList) {
        this.permissionList = permissionList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    /**
     * <p>Authorities attached to an account</p>
     * @return all the granted authorities attached to an account
     */
    public List<SimpleGrantedAuthority> grantedAuthorityList() {
        List<SimpleGrantedAuthority> authorities = getPermissionList().stream()
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

    }
