package com.example.rickandmorty2.RNM

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {

    @GET("character")
    fun getDataFromApi(@Query("page") page: Int) : Call<RickAndMortyList>

    @GET("character/{id}")
    suspend fun getPerson(@Path("id") id: Int): PersonsList

    @GET("location/{id}")
    suspend fun getLocation(@Path("id") id: Int): LocationList

}