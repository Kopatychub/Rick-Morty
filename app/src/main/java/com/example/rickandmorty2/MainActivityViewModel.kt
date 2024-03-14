package com.example.rickandmorty2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.rickandmorty2.RNM.PersonsList
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivityViewModel: ViewModel() {

    private var characterList: LiveData<PagedList<PersonsList>>? = null

    init {
        initpaging()
    }

    private fun initpaging(){
        val factory = CharacterListDataSourseFactory()
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        val executor: Executor =  Executors.newFixedThreadPool(5)
        characterList = LivePagedListBuilder<Int, PersonsList>(factory, config)
            .setFetchExecutor(executor)
            .build()
    }

    fun getRecyclerListObserver(): LiveData<PagedList<PersonsList>>? {
        return characterList
    }
}