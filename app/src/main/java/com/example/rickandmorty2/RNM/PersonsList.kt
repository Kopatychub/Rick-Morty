package com.example.rickandmorty2.RNM

data class RickAndMortyList(val results: ArrayList<PersonsList>)
data class PersonsList(
    val created: String? = "",
//    val episode: List<String>? = arrayListOf(),
    val gender: String? = "",
    val id: Int? = 0,
    val image: String? = "",
    val location: Location? = Location("", ""),
    val name: String? = "",
    val origin: Origin? = Origin("", ""),
    val species: String? = "",
    val status: String? = "",
    val type: String? = "",
    val url: String? = ""
){
    data class Origin(
        val name: String?,
        val url: String?
    )
    data class Location(
        val name: String?,
        val url: String?
    )
}
data class LocationList(
    val residents: List<String>,
)