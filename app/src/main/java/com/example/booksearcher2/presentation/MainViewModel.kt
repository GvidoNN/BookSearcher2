package com.example.booksearcher2.presentation

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearcher2.data.app.Status
import com.example.booksearcher2.data.repository.SearchInsideRepositoryImpl
import com.example.booksearcher2.domain.models.api.DataResponce
import com.example.booksearcher2.domain.usecase.SearchInsideUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val searchInsideUseCase: SearchInsideUseCase): ViewModel() {

    private val searchInsideLiveData = MutableLiveData<DataResponce>()


    val searchInside : LiveData<DataResponce>
    get() = searchInsideLiveData
    fun searchResponce(text: String) = viewModelScope.launch {
        var result = searchInsideUseCase.getSearchInside(text)
        if(result.body() != null){
            searchInsideLiveData.postValue(result.body())
        }
        searchInsideUseCase.getSearchInside(text)
    }

}