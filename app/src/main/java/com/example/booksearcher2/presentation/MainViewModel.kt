package com.example.booksearcher2.presentation

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearcher2.data.repository.SearchInsideRepositoryImpl
import com.example.booksearcher2.domain.models.api.DataResponce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val searchInsideRepository: SearchInsideRepositoryImpl): ViewModel() {
    fun searchResponce() = viewModelScope.launch {
        searchInsideRepository.getSearchInside()
    }

    val searchInside : LiveData<DataResponce>
        get() = searchInsideRepository.searchInside

}