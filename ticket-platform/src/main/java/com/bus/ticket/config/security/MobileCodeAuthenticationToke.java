package com.bus.ticket.config.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author honglixiang@tiduyun.com
 * @date 2023/8/1
 */
public class MobileCodeAuthenticationToke extends AbstractAuthenticationToken {

    private static final long serialVersionUID = 1L;

    private final Object principal;

    private Object credentials;

    public MobileCodeAuthenticationToke(Object principal, Object credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    /**
     * @param authorities
     */
    public MobileCodeAuthenticationToke(Object principal, Object credentials,
        Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
