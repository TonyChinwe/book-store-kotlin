package com.phisoft.bookstorekotlin.models

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name = "AUTHORS")
class Author() {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonProperty(value = "author_id")
    private var id: Long? = null

    @Column(name = "FIRST_NAME")
    @JsonProperty(value = "author_first_name")
    private var firstName: String? = null

    @Column(name = "LAST_NAME")
    @JsonProperty(value = "author_last_name")
    private var lastName: String? = null
    fun getId(): Long? {
        return id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getFirstName(): String? {
        return firstName
    }

    fun setFirstName(firstName: String) {
        this.firstName = firstName
    }

    fun getLastName(): String? {
        return lastName
    }

    fun setLastName(lastName: String) {
        this.lastName = lastName
    }



}