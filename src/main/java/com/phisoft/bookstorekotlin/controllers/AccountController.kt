package com.phisoft.bookstorekotlin.controllers

import com.phisoft.bookstorekotlin.authentication.AccountService
import com.phisoft.bookstorekotlin.dtos.AccountDto
import com.phisoft.bookstorekotlin.repositories.AccountRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
@RequestMapping(path = ["/api/account"])
@Api(value = "account", description = "Enables users to register with username and password.After which one can go to /login to login for access to our book store")
class AccountController(var accountService: com.phisoft.bookstorekotlin.services.AccountService) {


    @PostMapping("/register")
    @ApiOperation(value = "Registered a user and creates an account", notes = "This creates an account in the book store. Through the account, a user can upload books details and also view book's details")
    fun registerAccount(@Valid @RequestBody accountDto: AccountDto): String {
        return if (accountService.saveAccount(accountDto)) {
            "You have been registered successfully. You can then log in with your credentials"
        } else {
            "Registration failed"
        }
    }


}