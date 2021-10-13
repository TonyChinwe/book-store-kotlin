package com.phisoft.bookstorekotlin.dtos

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.annotations.ApiModelProperty

class BooksResponse {

    @JsonProperty(value = "books")
    @ApiModelProperty(notes = "This is list of books to be sent to client")
    private var bookResponseDto: List<BookResponseDto>? = null


    fun getBookResponseDto(): List<BookResponseDto>? {
        return bookResponseDto
    }

    fun setBookResponseDto(bookResponseDto: List<BookResponseDto>) {
        this.bookResponseDto = bookResponseDto
    }
}