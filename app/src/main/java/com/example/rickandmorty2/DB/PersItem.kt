package com.example.rickandmorty2.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("persons_base")
data class PersItem(
    @PrimaryKey(false) val id: Int? = null,
    val gender: String,
    val image: String? = "",
    val image_url: String?,
    val location: String,
    val location_type: String?,
    val location_dim: String?,
    val name: String?,
    val origin: String,
    val species: String,
    val status: String,
    val type: String
)