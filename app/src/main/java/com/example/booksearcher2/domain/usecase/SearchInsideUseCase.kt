package com.example.booksearcher2.domain.usecase

import android.util.Log
import com.example.booksearcher2.domain.models.api.DataResponce
import com.example.booksearcher2.domain.repository.SearchInsideRepository
import retrofit2.Response
import javax.inject.Inject

class SearchInsideUseCase @Inject constructor(private val searchInsideRepository: SearchInsideRepository) {

    suspend fun getSearchInside(text: String): Response<DataResponce>? {
        val result = searchInsideRepository.getSearchInside(text)
        return result
    }
}