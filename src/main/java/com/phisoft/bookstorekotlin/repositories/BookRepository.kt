package com.phisoft.bookstorekotlin.repositories

import com.phisoft.bookstorekotlin.models.Author
import com.phisoft.bookstorekotlin.models.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 * Book's repository
 */
@Repository
interface BookRepository: JpaRepository<Book,Long> {

    /**
     * Fetch book using book's title
     * @param title book's title
     * @return Book
     */
    fun findBookByTitle(title: String): Book

    /**
     * fetch book using author
     * @param author book's author
     * @return list of authors
     */
   fun findBookByAuthors(author: Author): List<Book>
}