package com.example.booksearcher2.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.booksearcher2.data.api.DataService
import com.example.booksearcher2.domain.models.api.DataResponce

class SearchInsideRepositoryImpl(private val dataService: DataService) {

    private val searchInsideLiveData = MutableLiveData<DataResponce>()

    val searchInside : LiveData<DataResponce>
    get() = searchInsideLiveData

    suspend fun getSearchInside(){
        val result = dataService.getInsideSearch()
        if(result.body() != null){
            searchInsideLiveData.postValue(result.body())
        }
    }
}