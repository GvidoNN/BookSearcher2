package com.example.booksearcher2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booksearcher2.domain.models.database.FavouriteBook

@Database(entities = [FavouriteBook::class], version = 1, exportSchema = false)
abstract class FavouriteBookDataBase : RoomDatabase() {

    abstract fun favouriteBookDao(): FavouriteBookDao

    companion object {

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
}