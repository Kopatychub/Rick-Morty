package com.example.rickandmorty2.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDao {
    @Query("select * from persons_base")
    fun getAllPerson() : LiveData<List<PersItem>>

    @Query("select * from persons_base where name = :name")
    fun getOnePerson(name: String?) : List<PersItem>

    @Update
    fun updatePerson(todoItem: PersItem)

//    @Query("select * from path where id = 1")
//    fun getPath() : List<PathI>

//    @Insert
//    fun setPath(pathI: PathI)

    @Insert
    fun addPerson(todoItem: PersItem)

    @Query("delete from persons_base where name = :name")
    fun deletePerson(name: String?)

    @Query("delete from persons_base")
    fun cleanTable()
}