package com.example.booksearcher2.domain.usecase

import com.example.booksearcher2.data.database.FavouriteBookDao
import com.example.booksearcher2.data.repository.FavouriteBookRepositoryImpl
import kotlin.contracts.Returns

class GetDaoDbUseCase(private val favouriteBookRepository: FavouriteBookRepositoryImpl) {


    fun getDaoDb(): FavouriteBookDao {
        return favouriteBookRepository.getDaoDb()
    }
}