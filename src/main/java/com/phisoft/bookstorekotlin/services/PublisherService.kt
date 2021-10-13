package com.phisoft.bookstorekotlin.services

import com.phisoft.bookstorekotlin.models.Publisher
/**
 * Service contract for fetching all the book publishers in our store
 */
interface PublisherService {
    /**
     * Finds all the book publishers in our store
     * @return list of book publisher in our store
     */
    fun findAllBookPublisher(): List<Publisher>

}