package com.example.rickandmorty2

import androidx.paging.PageKeyedDataSource
import com.example.rickandmorty2.RNM.PersonsList
import com.example.rickandmorty2.RNM.RetroInstance
import com.example.rickandmorty2.RNM.RetroService
import com.example.rickandmorty2.RNM.RickAndMortyList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterListDataSource(): PageKeyedDataSource<Int, PersonsList>() {

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PersonsList>) {
        val  retroInstance = RetroInstance.getRetroInstence().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(params.key)
        call.enqueue(object : Callback<RickAndMortyList>{

            override fun onResponse(
                call: Call<RickAndMortyList>,
                response: Response<RickAndMortyList>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response?.body()?.results!!, params.key+1)
                }
            }

            override fun onFailure(call: Call<RickAndMortyList>, t: Throwable) {

            }
        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PersonsList>) {

    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PersonsList>
    ) {
        val  retroInstance = RetroInstance.getRetroInstence().create(RetroService::class.java)
        val call = retroInstance.getDataFromApi(1)
        call.enqueue(object : Callback<RickAndMortyList>{

            override fun onResponse(
                call: Call<RickAndMortyList>,
                response: Response<RickAndMortyList>
            ) {
                if (response.isSuccessful) {
                    callback.onResult(response?.body()?.results!!, null, 2)
                }
            }

            override fun onFailure(call: Call<RickAndMortyList>, t: Throwable) {

            }
        })
    }


}