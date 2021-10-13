package com.phisoft.bookstorekotlin.services

import com.phisoft.bookstorekotlin.models.Author

/**
 * Service contract for fetching all the book authors in our store
 */
interface AuthorService {
    /**
     * Finds all the authors that can have their  books in our book store
     * @return list of authors that can have their books in our book store
     */
     fun findAllAuthors(): List<Author>

}