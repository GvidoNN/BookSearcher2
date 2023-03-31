package com.example.booksearcher2.data.database

import android.content.Context
import androidx.room.Room

object DataBaseObject {

    @Volatile
    private var INSTANCE: FavouriteBookDataBase? = null
    fun getInstance(context: Context): FavouriteBookDataBase {
        synchronized(this) {
            var instance = INSTANCE
            if(instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavouriteBookDataBase::class.java,
                    "favourite_book_database"
                ).build()
            }
            return instance
        }
    }
}