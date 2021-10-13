package com.phisoft.bookstorekotlin.repositories

import com.phisoft.bookstorekotlin.models.Publisher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Book's category repository
 */
@Repository
interface PublisherRepository : JpaRepository<Publisher,Long> {
}