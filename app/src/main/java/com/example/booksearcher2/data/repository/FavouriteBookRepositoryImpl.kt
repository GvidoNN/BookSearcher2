package com.example.booksearcher2.data.repository
//        val dao = DataBaseObject.getInstance(context).favouriteBookDao()

import com.example.booksearcher2.data.database.FavouriteBookDao
import com.example.booksearcher2.domain.repository.FavouriteBookReposotiry
import javax.inject.Inject

class FavouriteBookRepositoryImpl @Inject constructor(private val favouriteBookDao: FavouriteBookDao): FavouriteBookReposotiry {

    override fun getDaoDb(): FavouriteBookDao{

        return favouriteBookDao
    }
}