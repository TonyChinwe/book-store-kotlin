package com.phisoft.bookstorekotlin.authentication;

import com.phisoft.bookstorekotlin.models.Account;
import com.phisoft.bookstorekotlin.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This is responsible for loading registered  account from the database
 * It works in accordance with spring security
 */
@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> account= accountRepository.getAccountByUsername(username);
        Account acc=account.orElseThrow(()->new UsernameNotFoundException("Error! Account not found"));
        return new SecuredAccount(acc);
    }


}
