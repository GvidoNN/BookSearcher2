package com.example.booksearcher2.data.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseObject {

    @Volatile
    private var INSTANCE: FavouriteBookDataBase? = null

    @Provides
    @Singleton
    fun getInstance(@ApplicationContext context: Context): FavouriteBookDataBase {
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