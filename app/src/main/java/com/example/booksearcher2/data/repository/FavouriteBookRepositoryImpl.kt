package com.example.booksearcher2.data.repository

import android.util.Log
import com.example.booksearcher2.data.database.FavouriteBookDao
import com.example.booksearcher2.domain.repository.FavouriteBookReposotiry
import javax.inject.Inject

class FavouriteBookRepositoryImpl @Inject constructor(private val favouriteBookDao: FavouriteBookDao): FavouriteBookReposotiry {

    override fun getDaoDb(): FavouriteBookDao{
        Log.d("MyLog","FavouriteBookRepositoryImpl был")
//        val dao = DataBaseObject.getInstance(context).favouriteBookDao()
        return favouriteBookDao
    }
}