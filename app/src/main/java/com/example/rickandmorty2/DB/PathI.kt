package com.example.rickandmorty2.DB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("path")
data class PathI(
    @PrimaryKey(false) val id: Int?= null,
    val path: String
)
