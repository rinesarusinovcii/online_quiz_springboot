package com.rinesarusinovci.online_quizzes_vue_back.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.rinesarusinovci.online_quizzes_vue_back.enums.Permission.*;


@RequiredArgsConstructor
public enum Role {
    ADMIN(Set.of(ADMIN_READ, ADMIN_WRITE)),

    USER(Collections.emptySet());

    @Getter
    private final Set<Permission> permissions;


    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = new java.util.ArrayList<>(permissions.stream().
                map(permission -> new SimpleGrantedAuthority(permission.getPermission())).
                toList())
                ;

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
