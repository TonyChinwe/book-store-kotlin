package com.phisoft.bookstorekotlin.services

import com.phisoft.bookstorekotlin.models.Category

/**
 * Service contract for fetching all the book categories in our store
 */
interface CategoryService {

    /**
     * Finds all the book categories in our store
     * @return list of book categories in our store
     */
    abstract fun findAllBookCategory(): List<Category>

}