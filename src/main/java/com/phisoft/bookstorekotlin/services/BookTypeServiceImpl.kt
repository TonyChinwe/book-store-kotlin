package com.phisoft.bookstorekotlin.services

import com.phisoft.bookstorekotlin.models.BookType
import com.phisoft.bookstorekotlin.repositories.BookTypeRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

/**
 * {@inheritDoc}
 */
@Service
@Qualifier("book-type-impl-one")
class BookTypeServiceImpl(var bookTypeRepository: BookTypeRepository) : BookTypeService {
    /**
     * {@inheritDoc}
     */
    override fun findAllBookTypes(): List<BookType> {
        return bookTypeRepository.findAll()

    }


}