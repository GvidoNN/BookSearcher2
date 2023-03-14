package com.example.booksearcher2.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.booksearcher2.data.api.DataService
import com.example.booksearcher2.domain.models.api.DataResponce
import com.example.booksearcher2.domain.repository.SearchInsideRepository
import retrofit2.Response
import javax.inject.Inject

class SearchInsideRepositoryImpl @Inject constructor(private val dataService: DataService): SearchInsideRepository{
    override suspend fun getSearchInside(text: String): Response<DataResponce> {
        val result = dataService.getInsideSearch(text)
        return result
    }
}