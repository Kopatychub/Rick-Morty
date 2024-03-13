package com.example.rickandmorty2

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.rickandmorty2.RNM.CharacterData

class CharacterListDataSourseFactory():DataSource.Factory<Int, CharacterData>() {
    private var mutableLiveData: MutableLiveData<CharacterListDataSource>? = null

    init {

        mutableLiveData = MutableLiveData()
    }

    override fun create(): DataSource<Int, CharacterData> {
        val listDataSourse = CharacterListDataSource()
        mutableLiveData?.postValue(listDataSourse)
        return listDataSourse
    }
}