package com.phisoft.bookstorekotlin.dtos

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.*;


class AccountDto() {

    @NotBlank(message = "The username must not be blank")
    @NotNull(message = "You must enter a username")
    @Length(min = 4, message = "The length of username must be more than four")
    private var username: String? = null
    @NotBlank(message = "The password must not be blank")
    @NotNull(message = "You must enter a password")
    @Length(min = 4, message = "The length of password must be more than four")
    private var password: String? = null


    fun getUsername(): String? {
        return username
    }

    fun setUsername(username: String) {
        this.username = username
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }


}