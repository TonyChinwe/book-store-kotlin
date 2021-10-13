package com.phisoft.bookstorekotlin.repositories

import com.phisoft.bookstorekotlin.models.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * Account repository
 */
@Repository
interface AccountRepository: JpaRepository<Account,Long> {

    /**
     * Fetch account by username
     * @param username username
     * @return account
     */
    fun getAccountByUsername(username: String): Optional<Account>


}