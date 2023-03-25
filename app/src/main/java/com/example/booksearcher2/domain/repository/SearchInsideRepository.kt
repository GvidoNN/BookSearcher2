package com.example.booksearcher2.domain.repository

import com.example.booksearcher2.domain.models.api.DataResponce
import retrofit2.Response

interface SearchInsideRepository {

    suspend fun getSearchInside(text: String): Response<DataResponce>?
}