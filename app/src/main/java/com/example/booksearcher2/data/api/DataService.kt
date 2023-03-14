package com.example.booksearcher2.data.api

import com.example.booksearcher2.domain.models.api.DataResponce
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface DataService {
//    https://openlibrary.org/search

    @GET("inside.json?q=rostova&limit=5")
    suspend fun getInsideSearch() : Response<DataResponce>
}