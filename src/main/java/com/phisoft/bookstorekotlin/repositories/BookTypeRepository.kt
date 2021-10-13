package com.phisoft.bookstorekotlin.repositories

import com.phisoft.bookstorekotlin.models.BookType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Book-type repository
 */
@Repository
interface BookTypeRepository :JpaRepository<BookType,Long> {
}