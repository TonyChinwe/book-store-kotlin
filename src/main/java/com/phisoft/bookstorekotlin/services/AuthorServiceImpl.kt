package com.phisoft.bookstorekotlin.services

import com.phisoft.bookstorekotlin.models.Author
import com.phisoft.bookstorekotlin.repositories.AuthorRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
@Qualifier("author-impl-one")
class AuthorServiceImpl(var authorRepository: AuthorRepository) : AuthorService {

    /**
     * {@inheritDoc}
     */

    override fun findAllAuthors(): List<Author> {
        return authorRepository.findAll()

    }


}