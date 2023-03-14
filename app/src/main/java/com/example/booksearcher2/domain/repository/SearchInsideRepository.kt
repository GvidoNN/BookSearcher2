package com.example.booksearcher2.domain.repository

import com.example.booksearcher2.domain.models.api.DataResponce
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Response

interface SearchInsideRepository {

    suspend fun getSearchInside(text: String): Response<DataResponce>
}