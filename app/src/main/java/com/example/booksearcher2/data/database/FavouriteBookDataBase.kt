package com.example.booksearcher2.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booksearcher2.domain.models.database.FavouriteBook

@Database(entities = [FavouriteBook::class], version = 1, exportSchema = false)
abstract class FavouriteBookDataBase : RoomDatabase() {

    abstract fun favouriteBookDao(): FavouriteBookDao

}