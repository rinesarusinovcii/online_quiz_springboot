package com.rinesarusinovci.online_quizzes_vue_back.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write");


    @Getter
    private final String permission;
}
