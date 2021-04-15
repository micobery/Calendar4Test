package com.uniqmaster.calendar4test.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    TEACHER,
    STUDENT,
    ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
