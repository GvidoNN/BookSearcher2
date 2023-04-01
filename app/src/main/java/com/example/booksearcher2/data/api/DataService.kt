package com.example.booksearcher2.data.api

import com.example.booksearcher2.domain.models.api.DataResponce
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DataService {
    @GET("inside.json?&limit=25")
    suspend fun getInsideSearch(@Query("q") text: String) : Response<DataResponce>
}