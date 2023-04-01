package com.example.booksearcher2.domain.usecase

import android.util.Log
import com.example.booksearcher2.data.database.FavouriteBookDao
import com.example.booksearcher2.domain.repository.FavouriteBookReposotiry
import javax.inject.Inject

class GetDaoDbUseCase @Inject constructor(private val favouriteBookRepository: FavouriteBookReposotiry) {

    fun getDaoDb(): FavouriteBookDao {
        Log.d("MyLog","в GetDaoDBUseCase был")
        return favouriteBookRepository.getDaoDb()
    }
}