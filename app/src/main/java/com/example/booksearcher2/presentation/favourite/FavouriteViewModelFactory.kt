package com.example.booksearcher2.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.booksearcher2.data.database.FavouriteBookDao
import com.example.booksearcher2.domain.usecase.GetDaoDbUseCase

class FavouriteViewModelFactory(
    private val getDaoDbUseCase: GetDaoDbUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FavouriteViewModel::class.java)){
            return FavouriteViewModel(getDaoDbUseCase.getDaoDb()) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}