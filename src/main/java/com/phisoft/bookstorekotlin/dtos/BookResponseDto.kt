package com.phisoft.bookstorekotlin.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import com.phisoft.bookstorekotlin.models.Author
import com.phisoft.bookstorekotlin.models.BookType
import com.phisoft.bookstorekotlin.models.Category
import com.phisoft.bookstorekotlin.models.Publisher
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import java.util.*

class BookResponseDto {


    @JsonProperty(value = "book_title")
    @ApiModelProperty(notes = "Book title")
    private var title: String? = null

    @JsonProperty(value = "book_isbn")
    @ApiModelProperty(notes = "This is the Book isbn(International Standard Book Number)")
    private var isbn: String? = null

    @JsonProperty(value = "book_num_pages")
    private var numberOfPages: Int = 0

    @JsonProperty(value = "book_publish_date")
    @ApiModelProperty(notes = "The date the book was published")
    private var publishedDate: LocalDate? = null

    @JsonProperty(value = "book_authors")
    @ApiModelProperty(notes = "List of authors that wrote the book")
    private var authors: List<Author> = ArrayList<Author>()

    @JsonProperty(value = "book_category")
    @ApiModelProperty(notes = "This is the kind of the subject the book belong to")
    private var category: Category? = null

    @JsonProperty(value = "book_publisher")
    @ApiModelProperty(notes = "This is the publisher of the book")
    private var publisher: Publisher? = null

    @JsonProperty(value = "book_type")
    @ApiModelProperty(notes = "This is the type of book the book belond to. Like fiction or non-fiction")
    private var type: BookType? = null


    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
    }

    fun getIsbn(): String? {
        return isbn
    }

    fun setIsbn(isbn: String) {
        this.isbn = isbn
    }

    fun getNumberOfPages(): Int {
        return numberOfPages
    }

    fun setNumberOfPages(numberOfPages: Int) {
        this.numberOfPages = numberOfPages
    }

    fun getPublishedDate(): LocalDate? {
        return publishedDate
    }

    fun setPublishedDate(publishedDate: LocalDate) {
        this.publishedDate = publishedDate
    }

    fun getAuthors(): List<Author> {
        return authors
    }

    fun setAuthors(authors: List<Author>) {
        this.authors = authors
    }

    fun getCategory(): Category? {
        return category
    }

    fun setCategory(category: Category) {
        this.category = category
    }

    fun getPublisher(): Publisher? {
        return publisher
    }

    fun setPublisher(publisher: Publisher) {
        this.publisher = publisher
    }

    fun getType(): BookType? {
        return type
    }

    fun setType(type: BookType) {
        this.type = type
    }
}