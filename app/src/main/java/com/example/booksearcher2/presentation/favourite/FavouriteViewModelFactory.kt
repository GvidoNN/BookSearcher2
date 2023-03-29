package com.example.booksearcher2.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksearcher2.data.database.FavouriteBookDao

class FavouriteViewModelFactory(
    private val dao: FavouriteBookDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavouriteViewModel::class.java)){
            return FavouriteViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}