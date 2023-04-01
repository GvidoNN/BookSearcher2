//package com.example.booksearcher2.presentation.favourite
//
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.booksearcher2.data.database.FavouriteBookDao
//import com.example.booksearcher2.data.repository.FavouriteBookRepositoryImpl
//import com.example.booksearcher2.domain.usecase.GetDaoDbUseCase
//
//class FavouriteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
//
//    private val favouriteBookRepository by lazy {FavouriteBookRepositoryImpl(context = context)}
//    private val getDaoDbUseCase by lazy {GetDaoDbUseCase(favouriteBookRepository)}
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if(modelClass.isAssignableFrom(FavouriteViewModel::class.java)){
//            return FavouriteViewModel(getDaoDbUseCase.getDaoDb()) as T
//        }
//        throw IllegalArgumentException("Unknown View Model Class")
//    }
//}