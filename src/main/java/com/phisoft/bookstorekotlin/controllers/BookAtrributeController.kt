package com.phisoft.bookstorekotlin.controllers

import com.phisoft.bookstorekotlin.models.Author
import com.phisoft.bookstorekotlin.models.BookType
import com.phisoft.bookstorekotlin.models.Category
import com.phisoft.bookstorekotlin.models.Publisher
import com.phisoft.bookstorekotlin.services.AuthorService
import com.phisoft.bookstorekotlin.services.BookTypeService
import com.phisoft.bookstorekotlin.services.CategoryService
import com.phisoft.bookstorekotlin.services.PublisherService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * This is the Api layer that exposes all the Api endpoints for all the book's attribute in our store
 * Like Authors, categories of books, types of books and book publishers
 *
 */
@RestController
@RequestMapping("/api/v1/attributes")
@Api(value = "books-attribute", description = "Operations that enables us to get different attributes pertaining to books. Like book's author, publishers,etc")
class BookAtrributeController(var authorService: AuthorService,var categoryService: CategoryService,var publisherService: PublisherService,var bookTypeService: BookTypeService) {


    @GetMapping("/authors")
    @ApiOperation(value = "Fetches all the authors of books in our store", notes = "This fetches all the authors of books in our store.")
    fun getAllAuthors(authentication: Authentication): List<Author> {
        return authorService.findAllAuthors()
    }

    @GetMapping("/categories")
    @ApiOperation(value = "Fetches all books categories in our store", notes = "This fetches all books categories in our store.")
    fun getAllBookCategories(): List<Category> {
        return categoryService.findAllBookCategory()
    }

    @GetMapping("/publishers")
    @ApiOperation(value = "Fetches all books publishers in our store", notes = "This fetches all books publishers in our store.")
    fun getAlBookPublishers(): List<Publisher> {
        return publisherService.findAllBookPublisher()
    }

    @GetMapping("/book-type")
    @ApiOperation(value = "Fetches all books types registered in our store", notes = "This fetches all books types registered in our store.")
    fun getAllBookTypes(): List<BookType> {
        return bookTypeService.findAllBookTypes()
    }

}