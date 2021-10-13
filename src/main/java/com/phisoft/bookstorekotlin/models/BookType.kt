package com.phisoft.bookstorekotlin.models

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name="BOOK_TYPE")
class BookType() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonProperty(value = "book_type_id")
    private var id: Long? = null

    @Column(name = "NAME")
    @JsonProperty(value = "book_type_name")
    private var name: String? = null

    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

}