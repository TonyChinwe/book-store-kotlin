package com.phisoft.bookstorekotlin.services

import com.phisoft.bookstorekotlin.models.Category
import com.phisoft.bookstorekotlin.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service


/**
 * {@inheritDoc}
 */
@Service
@Qualifier("category-impl-one")
class CategoryServiceImpl(var categoryRepository: CategoryRepository) : CategoryService {

    /**
     * {@inheritDoc}
     */
    override fun findAllBookCategory(): List<Category> {
        return categoryRepository.findAll()
    }
}