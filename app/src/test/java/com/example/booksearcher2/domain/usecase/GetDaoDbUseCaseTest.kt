package com.example.booksearcher2.domain.usecase

import com.example.booksearcher2.data.database.FavouriteBookDao
import com.example.booksearcher2.data.repository.FavouriteBookRepositoryImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class GetDaoDbUseCaseTest {

    private val favouriteBookDao = mock<FavouriteBookDao>()
    private val favouriteBookRepositoryImpl = FavouriteBookRepositoryImpl(favouriteBookDao)
    private val getDaoDbUseCase = GetDaoDbUseCase(favouriteBookRepository = favouriteBookRepositoryImpl)

    @Test
    fun `get Dao from Repository to UseCase`(){
        assertEquals(getDaoDbUseCase.getDaoDb(),favouriteBookRepositoryImpl.getDaoDb())
    }
}