package com.catapult.backend.config;

import com.catapult.backend.service.UserService;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Web3Authentication extends AbstractAuthenticationToken {

    private String address;

    private String signature;

    public Web3Authentication(Collection<? extends GrantedAuthority> authorities, String address, String signature) {
        super(authorities);
        this.address = address;
        this.signature= signature;
    }

    public Web3Authentication(String address, String signature) {
        super(List.of());
        this.address = address;
        this.signature= signature;
    }

    @Override
    public Object getCredentials() {
        return signature;
    }

    @Override
    public Object getPrincipal() {
        return address;
    }
}
