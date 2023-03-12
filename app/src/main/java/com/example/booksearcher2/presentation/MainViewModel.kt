package com.example.booksearcher2.presentation

import android.provider.ContactsContract.Data
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearcher2.data.repository.SearchInsideRepositoryImpl
import com.example.booksearcher2.domain.models.api.DataResponce
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val searchInsideRepositoryImpl: SearchInsideRepositoryImpl): ViewModel() {

    init{
        viewModelScope.launch(Dispatchers.IO) {
            searchInsideRepositoryImpl.getSearchInside()
        }
    }

    val searchInside : LiveData<DataResponce>
    get() = searchInsideRepositoryImpl.searchInside



}