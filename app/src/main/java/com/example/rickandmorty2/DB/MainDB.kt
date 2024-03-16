package com.example.rickandmorty2.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database([PersItem::class, PathI::class], version = 2)
abstract class MainDB : RoomDatabase() {
    abstract fun personDao(): PersonDao

    companion object{
        var db: MainDB? = null
        fun getDataBase(context: Context): MainDB{
            if(db==null){
                db = Room.databaseBuilder(context, MainDB::class.java, "presData").build()
            }
            return db!!
        }
    }
}