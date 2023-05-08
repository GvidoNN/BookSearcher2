package my.lovely.booksearcher2.data.api

import my.lovely.booksearcher2.domain.models.api.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {
    @GET("inside.json?&limit=25")
    suspend fun getInsideSearch(@Query("q") text: String) : Response<DataResponse>
}