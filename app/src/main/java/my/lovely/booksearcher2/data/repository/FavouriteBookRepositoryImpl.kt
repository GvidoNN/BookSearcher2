package my.lovely.booksearcher2.data.repository

import my.lovely.booksearcher2.data.database.FavouriteBookDao
import my.lovely.booksearcher2.domain.repository.FavouriteBookReposotiry
import javax.inject.Inject

class FavouriteBookRepositoryImpl @Inject constructor(private val favouriteBookDao: FavouriteBookDao): FavouriteBookReposotiry {

    override fun getDaoDb(): FavouriteBookDao{
        return favouriteBookDao
    }
}