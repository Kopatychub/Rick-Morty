package com.example.rickandmorty2.RNM

import android.location.Location

data class RickAndMortyList(val results: ArrayList<CharacterData>)
data class CharacterData(
    val name: String?,
    val status: String?,
    val species: String?,
    val origin: Origin?,
    val location: Location?,
    val image: String?
){

data class Origin(
    val name: String?,
    val url: String?
)
data class Location(
    val name: String?,
    val url: String?
)}