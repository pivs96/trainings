package com.exadel.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class UserUtil {
    public static boolean hasRole(Integer role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            if( role==Integer.parseInt(authority.getAuthority())) {
                hasRole=true;
                break;
            }
        }
        return hasRole;
    }
}
