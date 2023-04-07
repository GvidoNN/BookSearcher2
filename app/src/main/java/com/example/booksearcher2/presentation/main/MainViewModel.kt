package com.example.booksearcher2.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.example.booksearcher2.R
import com.example.booksearcher2.domain.models.api.DataResponse
import com.example.booksearcher2.domain.models.database.FavouriteBook
import com.example.booksearcher2.domain.usecase.GetDaoDbUseCase
import com.example.booksearcher2.domain.usecase.SearchInsideUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.FieldPosition
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val searchInsideUseCase: SearchInsideUseCase, private val getDaoDbUseCase: GetDaoDbUseCase, private val apl: Application): ViewModel() {

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

    fun checkBook(position: Int, adapter: SearchInsideAdapter): FavouriteBook{
        return try{
            FavouriteBook(
                id = 0,
                title = adapter.searchInsideList[position].edition.title,
                author = adapter.searchInsideList[position].edition.authors[0].name,
                coverUrl = adapter.searchInsideList[position].edition.cover_url
            )
        }
        catch (e: java.lang.NullPointerException){
            FavouriteBook(
                id = 0,
                title = apl.getString(R.string.nan_title),
                author = apl.getString(R.string.nan_author),
                coverUrl = "null"
            )
        }

    }

}