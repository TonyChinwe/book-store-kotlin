package com.phisoft.bookstorekotlin.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDate
import javax.validation.constraints.Min
import javax.validation.constraints.Past
import javax.validation.constraints.Positive

class UpdateBookDto {


    @JsonProperty(value = "book_title")
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
    @ApiModelProperty(notes = "List of id belonging to authors that wrote the book")
    private var writers: List<Long>? = null

    @JsonProperty(value = "book_category_id")
    @ApiModelProperty(notes = "This is the id of the subject the book belong to")
    private var catNumber: Long? = null

    @JsonProperty(value = "book_publisher_id")
    @ApiModelProperty(notes = "This specifies the id of the book publisher")
    private var pubNumber: Long? = null

    @JsonProperty(value = "book_type_id")
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