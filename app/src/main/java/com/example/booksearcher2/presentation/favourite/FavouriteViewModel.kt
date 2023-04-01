package com.example.booksearcher2.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearcher2.data.database.FavouriteBookDao
import com.example.booksearcher2.domain.models.database.FavouriteBook
import com.example.booksearcher2.domain.repository.FavouriteBookReposotiry
import com.example.booksearcher2.domain.usecase.GetDaoDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//private val favouriteBookReposotiry: FavouriteBookReposotiry
@HiltViewModel
class FavouriteViewModel @Inject constructor(private val getDaoDbUseCase: GetDaoDbUseCase): ViewModel() {

    var books = getDaoDbUseCase.getDaoDb().getAllBooks()

    fun insertBook(book: FavouriteBook) = viewModelScope.launch {
        getDaoDbUseCase.getDaoDb().insertBook(book)
    }

    fun updateBook(book: FavouriteBook) = viewModelScope.launch {
        getDaoDbUseCase.getDaoDb().updateBook(book)
    }

    fun deleteBook(book: FavouriteBook) = viewModelScope.launch {
        getDaoDbUseCase.getDaoDb().deleteBook(book)
    }

}