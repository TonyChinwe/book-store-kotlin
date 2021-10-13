package com.phisoft.bookstorekotlin.models

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
@Table(name="PUBLISHER")
class Publisher() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    @JsonProperty(value = "book_publisher_id")
    private var id: Long? = null


    @Column(name = "NAME")
    @JsonProperty(value = "book_publisher_name")
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