package com.phisoft.bookstorekotlin.services;


import com.phisoft.bookstorekotlin.dtos.BookDto;
import com.phisoft.bookstorekotlin.dtos.BookResponseDto;
import com.phisoft.bookstorekotlin.dtos.BooksResponse;
import com.phisoft.bookstorekotlin.dtos.UpdateBookDto;
import com.phisoft.bookstorekotlin.error.BookNotFoundException;

/**
 * The service contract for book related operations in our book store
 */
public interface BookService {
    /**
     * Saves book to our store
     * @param book to be saved
     * @return the saved book representation in the form of data transfer object
     */
    BookDto saveBook(BookDto book);

    /**
     * Returns books in our book store that satisfies the query parameters like below:
     * @param page the book search starting position
     * @param size the number of books to return at a given time time
     * @param sortField the sorting parameter
     * @param sortDirection the sorting direction. Either sort in descending order or ascending order
     * @return Data transfer object that has all the book retrieved from our store
     */
    BooksResponse allBooks(int page, int size, String sortField, String sortDirection);

    /**
     * Get a book frm our store using bookId
     * @param bookId particular book id
     * @return Data transfer object that represent the book retrieved
     * @throws BookNotFoundException
     */
    BookResponseDto getBookById(Long bookId) throws BookNotFoundException;

    /**
     * Delete a book using book id
     * @param bookId id of the book to be deleted
     */
    void deleteBookById(Long bookId);

    /**
     * Update a book using book id and the payload of data to update a book with
     * @param bookId the id of the book to be updated
     * @param bookDto Data representation of the payload
     * @return Data data representing the book updated
     */
    BookResponseDto updateBook(Long bookId, UpdateBookDto bookDto);

    /**
     * Get a book from our store using title
     * @param title particular book title
     * @return Data transfer object that represent the book retrieved
     * @return Data data representing the book updated
     */
    BookResponseDto getBookByTitle(String title);

    /**
     * Get a book from our store using title
     * @param authorFirstName book author first-name
     * @return Data transfer object that represent the book retrieved
     */
    BooksResponse getBookByAuthor(String authorFirstName);


}
