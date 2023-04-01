package com.example.booksearcher2.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.booksearcher2.domain.models.database.FavouriteBook
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Dao
interface FavouriteBookDao {

    @Insert
    suspend fun insertBook(book: FavouriteBook)

    @Update
    suspend fun updateBook(book: FavouriteBook)

    @Delete
    suspend fun deleteBook(book: FavouriteBook)

    @Query("SELECT * FROM favourite_book_data_table")
    fun getAllBooks(): LiveData<List<FavouriteBook>>
}