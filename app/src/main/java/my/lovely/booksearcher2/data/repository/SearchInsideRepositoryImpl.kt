package my.lovely.booksearcher2.data.repository

import my.lovely.booksearcher2.data.api.DataService
import my.lovely.booksearcher2.domain.models.api.DataResponse
import my.lovely.booksearcher2.domain.repository.SearchInsideRepository
import retrofit2.Response
import javax.inject.Inject

class SearchInsideRepositoryImpl @Inject constructor(private val dataService: DataService): SearchInsideRepository{
    override suspend fun getSearchInside(text: String): Response<DataResponse>?{
        return try{
            val result = dataService.getInsideSearch(text)
            result
        } catch (e: java.net.UnknownHostException){
            null
        } catch (e: java.net.SocketTimeoutException){
            null
        }
    }
}

