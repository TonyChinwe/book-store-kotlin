package com.phisoft.bookstorekotlin.authentication;

import com.phisoft.bookstorekotlin.models.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * This is the actual user account used in logging in spring security
 * And this serves as a decorator to actual entity user account stored in the database
 */
public class SecuredAccount implements UserDetails {


    private final Account account;

    public SecuredAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account.getRole().grantedAuthorityList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String getPassword() {
        return account.getPassword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsername() {
        return account.getUsername();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        return account.isNotExpired();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {
        return account.isNotLocked();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return account.isCredentialNotExpired();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return account.isEnabled();
    }


}
