package com.example.booksearcher2.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearcher2.domain.models.database.FavouriteBook
import com.example.booksearcher2.domain.usecase.GetDaoDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val getDaoDbUseCase: GetDaoDbUseCase): ViewModel() {

    var books = getDaoDbUseCase.getDaoDb().getAllBooks()

    fun updateBook(book: FavouriteBook) = viewModelScope.launch(Dispatchers.IO) {
        getDaoDbUseCase.getDaoDb().updateBook(book)
    }

    fun deleteBook(book: FavouriteBook) = viewModelScope.launch(Dispatchers.IO) {
        getDaoDbUseCase.getDaoDb().deleteBook(book)
    }

}