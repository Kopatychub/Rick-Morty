package com.example.rickandmorty2.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDao {
    @Query("select * from persons")
    fun getAllPass() : LiveData<List<PersItem>>

    @Update
    fun updatePass(todoItem: PersItem)

    @Insert
    fun addPass(todoItem: PersItem)

    @Delete
    fun deleteTodo(todoItem: PersItem)
}