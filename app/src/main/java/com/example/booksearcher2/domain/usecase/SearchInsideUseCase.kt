package com.example.booksearcher2.domain.usecase

import com.example.booksearcher2.domain.models.api.DataResponse
import com.example.booksearcher2.domain.repository.SearchInsideRepository
import retrofit2.Response
import javax.inject.Inject

class SearchInsideUseCase @Inject constructor(private val searchInsideRepository: SearchInsideRepository) {

    suspend fun getSearchInside(text: String): Response<DataResponse>? {
        val result = searchInsideRepository.getSearchInside(text)
        return result
    }
}