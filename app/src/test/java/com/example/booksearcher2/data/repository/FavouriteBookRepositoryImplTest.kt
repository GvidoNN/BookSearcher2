package com.example.booksearcher2.data.repository

import com.example.booksearcher2.data.database.FavouriteBookDao
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class FavouriteBookRepositoryImplTest {

    private val favouriteBookDao = mock<FavouriteBookDao>()
    private val favouriteBookRepositoryImpl = FavouriteBookRepositoryImpl(favouriteBookDao)

    @Test
    fun `get DAO in Repository`(){
        assertEquals(favouriteBookDao, favouriteBookRepositoryImpl.getDaoDb())
    }
}