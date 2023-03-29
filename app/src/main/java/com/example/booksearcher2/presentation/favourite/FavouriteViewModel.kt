package com.example.booksearcher2.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearcher2.data.database.FavouriteBookDao
import com.example.booksearcher2.domain.models.database.FavouriteBook
import kotlinx.coroutines.launch

class FavouriteViewModel(private val dao: FavouriteBookDao): ViewModel() {

    val books = dao.getAllBooks()

    fun insertBook(book: FavouriteBook) = viewModelScope.launch {
        dao.insertBook(book)
    }

    fun updateBook(book: FavouriteBook) = viewModelScope.launch {
        dao.updateBook(book)
    }

    fun deleteBook(book: FavouriteBook) = viewModelScope.launch {
        dao.deleteBook(book)
    }




}