package com.example.rickandmorty2

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.rickandmorty2.RNM.PersonsList

class CharacterListDataSourseFactory():DataSource.Factory<Int, PersonsList>() {
    private var mutableLiveData: MutableLiveData<CharacterListDataSource>? = null

    init {

        mutableLiveData = MutableLiveData()
    }

    override fun create(): DataSource<Int, PersonsList> {
        val listDataSourse = CharacterListDataSource()
        mutableLiveData?.postValue(listDataSourse)
        return listDataSourse
    }
}