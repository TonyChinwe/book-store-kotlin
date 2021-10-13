package com.phisoft.bookstorekotlin.repositories

import com.phisoft.bookstorekotlin.models.Author
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Author's repository
 */
@Repository
interface AuthorRepository: JpaRepository<Author,Long> {

    /**
     * Fetach author using first name
     * @param name first name
     * @return author
     */
    fun getAuthorByFirstName(name: String): Author
}