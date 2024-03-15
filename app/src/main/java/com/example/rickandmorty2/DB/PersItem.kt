package com.example.rickandmorty2.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("persons")
data class PersItem(
    @PrimaryKey(false) val id: Int? = null,
    val created: String,
    val gender: String,
    val image: String,
    val location: String,
    val name: String,
    val origin: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)