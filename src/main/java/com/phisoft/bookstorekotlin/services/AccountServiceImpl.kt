package com.phisoft.bookstorekotlin.services

import com.phisoft.bookstorekotlin.authentication.Role
import com.phisoft.bookstorekotlin.dtos.AccountDto
import com.phisoft.bookstorekotlin.models.Account
import com.phisoft.bookstorekotlin.repositories.AccountRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

/**
 * {@inheritDoc}
 */

@Service
@Qualifier("account-impl-one")
class AccountServiceImpl( var accountRepository: AccountRepository,var passwordEncoder: PasswordEncoder ) : AccountService {
    override fun saveAccount(accountDto: AccountDto): Boolean {

        var account = Account()
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()))
        account.setUsername(accountDto.getUsername()!!)
        account.setEnabled(true)
        account.setNotExpired(true)
        account.setNotLocked(true)
        account.setCredentialNotExpired(true)
        val acc = accountRepository.getAccountByUsername(accountDto.getUsername()!!).orElse(null)
        if (acc == null) {
            accountRepository.save(account)
            return true
        }
        return false
    }
}