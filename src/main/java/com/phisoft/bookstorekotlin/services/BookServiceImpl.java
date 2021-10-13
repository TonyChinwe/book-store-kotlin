package com.phisoft.bookstorekotlin.services;

import com.phisoft.bookstorekotlin.dtos.BookDto;
import com.phisoft.bookstorekotlin.dtos.BookResponseDto;
import com.phisoft.bookstorekotlin.dtos.BooksResponse;
import com.phisoft.bookstorekotlin.dtos.UpdateBookDto;
import com.phisoft.bookstorekotlin.error.BookNotFoundException;
import com.phisoft.bookstorekotlin.models.*;
import com.phisoft.bookstorekotlin.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * {@inheritDoc}
 */
@Service
@Qualifier("book-impl-one")
@Slf4j
public class BookServiceImpl implements BookService{

    private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private BookTypeRepository bookTypeRepository;
    private CategoryRepository categoryRepository;
    private PublisherRepository publisherRepository;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           BookTypeRepository bookTypeRepository,
                           CategoryRepository categoryRepository,
                           PublisherRepository publisherRepository){
     this.bookRepository=bookRepository;
     this.authorRepository=authorRepository;
     this.bookTypeRepository=bookTypeRepository;
     this.categoryRepository=categoryRepository;
     this.publisherRepository=publisherRepository;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BookDto saveBook(BookDto bookDto) {
        logger.info("Inside book service-impl in the saveBook method Saving a book in the repository");
        Book book=new Book();
        book.setTitle(bookDto.getTitle());
        book.setNumberOfPages(bookDto.getNumberOfPages());
        book.setPublishedDate(bookDto.getPublishedDate());
        assignBookAttributes(bookDto, book);
        bookRepository.save(book);
        return bookDto;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BooksResponse allBooks(int page, int size, String sortField, String sortDirection) {
        logger.info("Inside book service-impl in the allBooks method fetching all the books");
        Pageable pagingSorted;
        if(sortDirection.equalsIgnoreCase("desc")) {
            pagingSorted = PageRequest.of(page, size, Sort.by(sortField).descending());
        }else {
            pagingSorted = PageRequest.of(page, size, Sort.by(sortField).ascending());
        }
        Page<Book> bookList=bookRepository.findAll(pagingSorted);
        BooksResponse bookResponse=new BooksResponse();
        List<BookResponseDto> bookResponseDtoList=new ArrayList<>();
        ModelMapper modelMapper=new ModelMapper();
        BookResponseDto bookResponseDto;
        for(Book book:bookList){
        bookResponseDto=modelMapper.map(book,BookResponseDto.class);
        bookResponseDtoList.add(bookResponseDto);
        }
        bookResponse.setBookResponseDto(bookResponseDtoList);
        return bookResponse;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BookResponseDto getBookById(Long bookId) throws BookNotFoundException {
        logger.info("Inside book service implementation in the getBookById method fetching a particular book based on id");

        ModelMapper modelMapper=new ModelMapper();
        Book book=bookRepository.findById(bookId).orElse(null);
        if (Objects.nonNull(book)){
         return modelMapper.map(book,BookResponseDto.class);
        }
        throw new BookNotFoundException("The Book is not in our database");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteBookById(Long bookId) {
        logger.info("Inside book service implementation in the deleteBookById method Trying to delete a book based on id");
        Book book=bookRepository.findById(bookId).orElse(null);
        if (Objects.nonNull(book)){
            bookRepository.delete(book);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BookResponseDto updateBook(Long bookId, UpdateBookDto bookDto) {
        logger.info("Inside book service implementation in the updateBook method Trying to update a book in the repository");
        Book book=bookRepository.findById(bookId).orElse(null);
       if(Objects.nonNull(book)){
         if(Objects.nonNull(bookDto.getTitle())&&!bookDto.getTitle().isEmpty()){
             book.setTitle(bookDto.getTitle());
         }
           if(Objects.nonNull(bookDto.getCatNumber())){
               book.setCategory(categoryRepository.findById(bookDto.getCatNumber()).orElse(null));
           }
           if(Objects.nonNull(bookDto.getPubNumber())){
               book.setPublisher(publisherRepository.findById(bookDto.getPubNumber()).orElse(null));
           }
           if(Objects.nonNull(bookDto.gettNumber())){
               book.setType(bookTypeRepository.findById(bookDto.gettNumber()).orElse(null));
           }
           if(Objects.nonNull(bookDto.getNumberOfPages())){
               book.setNumberOfPages(bookDto.getNumberOfPages());
           }
           if(Objects.nonNull(bookDto.getPublishedDate())){
               book.setPublishedDate(bookDto.getPublishedDate());
           }
           updateBookAuthors(bookDto, book);
           bookRepository.save(book);
           ModelMapper modelMapper=new ModelMapper();
           return modelMapper.map(book,BookResponseDto.class);
       }
       return new BookResponseDto();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BookResponseDto getBookByTitle(String title) {
        logger.info("Inside book service implementation in the getBookByTitle method Trying to fetch a book using the book title ");
        ModelMapper modelMapper=new ModelMapper();
        Book book=bookRepository.findBookByTitle(title);
        if (Objects.nonNull(book)){
            return modelMapper.map(book,BookResponseDto.class);
        }
      return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BooksResponse getBookByAuthor(String authorFirstName) {
        logger.info("Inside book service implementation in the getBookByAuthor method Trying to fetch a book using the book author ");
        Author authorModel=authorRepository.getAuthorByFirstName(authorFirstName);
        if(Objects.nonNull(authorModel)){
            List<Book> bookList=bookRepository.findBookByAuthors(authorModel);
            BooksResponse bookResponse=new BooksResponse();
            List<BookResponseDto> bookResponseDtoList=new ArrayList<>();
            ModelMapper modelMapper=new ModelMapper();
            BookResponseDto bookResponseDto;
            for(Book book:bookList){
                bookResponseDto=modelMapper.map(book,BookResponseDto.class);
                bookResponseDtoList.add(bookResponseDto);
            }
            bookResponse.setBookResponseDto(bookResponseDtoList);
            return bookResponse;
        }
           BooksResponse emptyResponse=new BooksResponse();
           emptyResponse.setBookResponseDto(new ArrayList<>());
           return emptyResponse;

    }


    private void updateBookAuthors(UpdateBookDto bookDto, Book book) {
        List<Long> authorsId = bookDto.getWriters();
        assignAuthorsToBook(book, authorsId);
    }

    private void assignAuthorsToBook(Book book, List<Long> authorsId) {
        if (Objects.nonNull(authorsId) && !authorsId.isEmpty()) {
            Author author;
            for (Long id : authorsId) {
                author = authorRepository.findById(id).orElse(null);
                if (Objects.nonNull(author)) {
                    book.getAuthors().add(author);
                }
            }
        }
    }

    private void saveBookAuthors(BookDto bookDto, Book book) {
        List<Long> authorsId = bookDto.getWriters();
        assignAuthorsToBook(book, authorsId);
    }

    private void assignBookAttributes(BookDto bookDto, Book book) {
        saveBookAuthors(bookDto, book);
        BookType bookType=bookTypeRepository.findById(bookDto.gettNumber()).orElse(null);
        if(Objects.nonNull(bookType)){
            book.setType(bookType);
        }
        Category category=categoryRepository.findById(bookDto.getCatNumber()).orElse(null);
        if(Objects.nonNull(category)){
            book.setCategory(category);
        }
        Publisher publisher=publisherRepository.findById(bookDto.getPubNumber()).orElse(null);
        if(Objects.nonNull(publisher)){
            book.setPublisher(publisher);
        }
        book.setIsbn(UUID.randomUUID().toString());
    }
}
