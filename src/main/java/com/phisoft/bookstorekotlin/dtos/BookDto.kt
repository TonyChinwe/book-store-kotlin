package com.phisoft.bookstorekotlin.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import org.hibernate.validator.constraints.Length
import java.time.LocalDate
import javax.validation.constraints.*

class BookDto {

    @JsonProperty(value = "book_title")
    @NotBlank(message = "The book must have title")
    @NotNull(message = "The book must have title")
    @Length(min = 4, message = "What you entered cannot be a book title")
    @ApiModelProperty(notes = "Book title")
    private var title: String? = null

    @JsonProperty(value = "book_num_pages")
    @Min(value = 1, message = "The number of pages must be more than one")
    @Positive
    @ApiModelProperty(notes = "Book number of pages")
    private var numberOfPages: Int = 0

    @JsonProperty(value = "book_published_date")
    @Past
    @ApiModelProperty(notes = "Book published date")
    private var publishedDate: LocalDate? = null

    @JsonProperty(value = "book_author_id")
    @Size(min = 1, message = "A book must have at least one author")
    @NotNull(message = "A book must have at least one author")
    @ApiModelProperty(notes = "List of id belonging to authors that wrote the book")
    private var writers: List<Long>? = null

    @JsonProperty(value = "book_category_id")
    @NotNull(message = "A book must belong to a category")
    @Positive
    @ApiModelProperty(notes = "This is the id of the subject the book belong to")
    private var catNumber: Long? = null

    @JsonProperty(value = "book_publisher_id")
    @NotNull(message = "A book must have a publisher")
    @Positive
    @ApiModelProperty(notes = "This specifies the id of the book publisher")
    private var pubNumber: Long? = null

    @JsonProperty(value = "book_type_id")
    @NotNull(message = "A book must belong to a type")
    @Positive
    @ApiModelProperty(notes = "This specifies the id of the book type like fiction, non-fiction")
    private var tNumber: Long? = null


    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String) {
        this.title = title
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

    fun getWriters(): List<Long>? {
        return writers
    }

    fun setWriters(writers: List<Long>) {
        this.writers = writers
    }

    fun getCatNumber(): Long? {
        return catNumber
    }

    fun setCatNumber(catNumber: Long?) {
        this.catNumber = catNumber
    }

    fun getPubNumber(): Long? {
        return pubNumber
    }

    fun setPubNumber(pubNumber: Long?) {
        this.pubNumber = pubNumber
    }

    fun gettNumber(): Long? {
        return tNumber
    }

    fun settNumber(tNumber: Long?) {
        this.tNumber = tNumber
    }


}