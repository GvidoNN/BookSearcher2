package com.example.booksearcher2.data.repository

import android.content.Context
import com.example.booksearcher2.data.database.DataBaseObject
import com.example.booksearcher2.data.database.FavouriteBookDao
import com.example.booksearcher2.domain.repository.FavouriteBookReposotiry
import javax.inject.Inject

class FavouriteBookRepositoryImpl @Inject constructor(private val context: Context): FavouriteBookReposotiry {

    override fun getDaoDb(): FavouriteBookDao{
        val dao = DataBaseObject.getInstance(context).favouriteBookDao()
        return dao
    }
}