package my.lovely.booksearcher2.domain.repository

import my.lovely.booksearcher2.domain.models.api.DataResponse
import retrofit2.Response

interface SearchInsideRepository {

    suspend fun getSearchInside(text: String): Response<DataResponse>?
}