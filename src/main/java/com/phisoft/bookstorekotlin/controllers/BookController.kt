package com.phisoft.bookstorekotlin.controllers
import com.phisoft.bookstorekotlin.dtos.BookResponseDto
import com.phisoft.bookstorekotlin.dtos.BooksResponse
import com.phisoft.bookstorekotlin.dtos.UpdateBookDto
import com.phisoft.bookstorekotlin.services.BookService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(path = ["/api/v1/books"])
@Api(value = "books-store", description = "Operations pertaining to books in our book store. Like uploading books,searching for books and a lot more ")
class BookController(var bookService: BookService) {

    @GetMapping("/all-books")
    @ApiOperation(value = "Fetches books from the book store based on certain filters", notes = "This fetches book from the book store based on certain filters. It alse applies pagination", response = BooksResponse::class)
    fun getAllBooks(@RequestParam(required = false, name = "page", defaultValue = "1") page: Int,
                    @RequestParam(required = false, name = "size", defaultValue = "5") size: Int,
                    @RequestParam(required = false, name = "sortField", defaultValue = "id") sortField: String,
                    @RequestParam(required = false, name = "direction", defaultValue = "ascend") direction: String): BooksResponse {
        return bookService.allBooks(page, size, sortField, direction)
    }


    @GetMapping("/book/{id}")
    @ApiOperation(value = "Fetches a book from our book store based on the id of the book", notes = "This fetches a particular book from our book store based on the id of the book", response = BookResponseDto::class)
    fun getBookById(@PathVariable("id") bookId: Long?): BookResponseDto {
        return bookService.getBookById(bookId)

    }

    @DeleteMapping("/book/{id}")
    @ApiOperation(value = "Deletes a book from our book store using id", notes = "This deletes a book from our book store using id", response = String::class)
    fun deleteBookById(@PathVariable("id") bookId: Long?): String {
        bookService.deleteBookById(bookId)
        return "Book deleted"
    }

    @PutMapping("/book/{id}")
    @ApiOperation(value = "Updates a book from our book store using id", notes = "This Updates a book from our book store using id", response = BookResponseDto::class)
    fun updateBook(@PathVariable("id") bookId: Long?, @Valid @RequestBody bookDto: UpdateBookDto): BookResponseDto {
        return bookService.updateBook(bookId, bookDto)
    }

    @GetMapping("/book/title/{title}")
    @ApiOperation(value = "Fetches a book from our book store based on the title of the book", notes = "This fetches a particular book from our book store based on the title of the book", response = BookResponseDto::class)
    fun getBookByTitle(@PathVariable("title") title: String): BookResponseDto {
        return bookService.getBookByTitle(title)
    }

    @GetMapping("/author/first-name/{firstName}")
    @ApiOperation(value = "Fetches books from the book store written by a particular author", notes = "This fetches books from the book store written by a particular author . It does it using the author's first-name", response = BooksResponse::class)
    fun getBookByAuthor(@PathVariable("firstName") authorFirstName: String): BooksResponse {
        return bookService.getBookByAuthor(authorFirstName)
    }


}