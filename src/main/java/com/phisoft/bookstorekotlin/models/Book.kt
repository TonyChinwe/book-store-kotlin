package com.phisoft.bookstorekotlin.models

import java.time.LocalDate
import java.util.*
import javax.persistence.*


@Entity
@Table(name="BOOK")
class Book() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, unique = true)
    private var id: Long? = null

    @Column(name = "TITLE", nullable = false, unique = true)
    private var title: String? = null

    @Column(name = "ISBN", nullable = false, unique = true)
    private var isbn: String? = null

    @Column(name = "NUMBER_OF_PAGES")
    private var numberOfPages: Int = 0

    @Column(name = "PUBLISH_DATE")
    private var publishedDate: LocalDate? = null

    @ManyToMany
    @JoinTable(name = "BOOK_AUTHOR_TABLE", joinColumns = arrayOf(JoinColumn(name = "BOOK_ID")), inverseJoinColumns = arrayOf(JoinColumn(name = "AUTHOR_ID")))
    private var authors: List<Author> = ArrayList()

    @ManyToOne
    @JoinColumn(name = "CATEGORY")
    private var category: Category? = null

    @ManyToOne
    @JoinColumn(name = "OWNER")
    private var account: Account? = null


    @ManyToOne
    @JoinColumn(name = "PUBLISHER")
    private var publisher: Publisher? = null

    @ManyToOne
    @JoinColumn(name = "TYPE")
    private var type: BookType? = null


    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

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

    fun getAccount(): Account? {
        return account
    }

    fun setAccount(account: Account) {
        this.account = account
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