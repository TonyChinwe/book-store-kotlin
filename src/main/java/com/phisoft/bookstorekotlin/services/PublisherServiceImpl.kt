package com.phisoft.bookstorekotlin.services

import com.phisoft.bookstorekotlin.models.Publisher
import com.phisoft.bookstorekotlin.repositories.PublisherRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

/**
 * {@inheritDoc}
 */
@Service
@Qualifier("publisher-impl-one")
class PublisherServiceImpl(var publisherRepository: PublisherRepository) : PublisherService {

    /**
     * {@inheritDoc}
     */
    override fun findAllBookPublisher(): List<Publisher> {
        return publisherRepository.findAll()

    }
}