package com.phisoft.bookstorekotlin.services

import com.phisoft.bookstorekotlin.models.BookType
/**
 * Service contract for fetching all the book types in our store
 */

interface BookTypeService {

    /**
     * Finds all the types a book can belong to in our store
     * @return list of book types in our store
     */
    abstract fun findAllBookTypes(): List<BookType>
}