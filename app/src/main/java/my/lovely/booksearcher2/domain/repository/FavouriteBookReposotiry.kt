package my.lovely.booksearcher2.domain.repository

import my.lovely.booksearcher2.data.database.FavouriteBookDao

interface FavouriteBookReposotiry {

    fun getDaoDb(): FavouriteBookDao

}