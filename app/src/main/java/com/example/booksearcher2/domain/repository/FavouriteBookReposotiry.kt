package com.example.booksearcher2.domain.repository

import com.example.booksearcher2.data.database.FavouriteBookDao

interface FavouriteBookReposotiry {

    fun getDaoDb(): FavouriteBookDao

}