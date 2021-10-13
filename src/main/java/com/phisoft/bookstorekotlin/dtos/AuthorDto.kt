package com.phisoft.bookstorekotlin.dtos

import com.phisoft.bookstorekotlin.models.Book

class AuthorDto {

    private var firstName: String? = null
    private var lastName: String? = null
    private var books: List<Book>? = null

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String) {
        this.firstName = firstName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String) {
        this.lastName = lastName
    }

    fun getBooks(): List<Book>? {
        return books
    }

    fun setBooks(books: List<Book>) {
        this.books = books
    }
}