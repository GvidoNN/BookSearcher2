package com.example.booksearcher2.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearcher2.domain.models.api.DataResponse
import com.example.booksearcher2.domain.models.database.FavouriteBook
import com.example.booksearcher2.domain.usecase.GetDaoDbUseCase
import com.example.booksearcher2.domain.usecase.SearchInsideUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val searchInsideUseCase: SearchInsideUseCase, private val getDaoDbUseCase: GetDaoDbUseCase): ViewModel() {

    private val searchInsideLiveData = MutableLiveData<DataResponse>()
    private var progressBarLiveData = MutableLiveData<Boolean>()

    val searchInside : LiveData<DataResponse>
    get() = searchInsideLiveData

    val progressBar: LiveData<Boolean>
    get() = progressBarLiveData

    fun searchResponce(text: String) = viewModelScope.launch {
        progressBarLiveData.postValue(true)
        var result = searchInsideUseCase.getSearchInside(text)
        searchInsideLiveData.postValue(result?.body() ?: null)
        progressBarLiveData.postValue(false)
    }

    fun insertBook(book: FavouriteBook) = viewModelScope.launch {
        getDaoDbUseCase.getDaoDb().insertBook(book)
    }

}