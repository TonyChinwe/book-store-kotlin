package com.phisoft.bookstorekotlin.services

import com.phisoft.bookstorekotlin.dtos.AccountDto

/**
 * The service class that handles creating of user account in our book store
 */
interface AccountService {

    /**
     * Creates and saves account in the repository
     * @param accountDto account data tranfer object
     * @return true if created successfully and false otherwise
     */
    fun saveAccount(accountDto: AccountDto): Boolean


}