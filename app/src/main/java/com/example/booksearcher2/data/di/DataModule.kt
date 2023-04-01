package com.example.booksearcher2.data.di

import android.content.Context
import com.example.booksearcher2.data.api.DataService
import com.example.booksearcher2.data.repository.FavouriteBookRepositoryImpl
import com.example.booksearcher2.data.repository.SearchInsideRepositoryImpl
import com.example.booksearcher2.domain.repository.FavouriteBookReposotiry
import com.example.booksearcher2.domain.repository.SearchInsideRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideSearchInsideRepositoryImpl(dataService: DataService) : SearchInsideRepository {
        return SearchInsideRepositoryImpl(dataService = dataService)

    }

    @Provides
    @Singleton
    fun provideFavouriteBookRespositoryImpl(@ApplicationContext context: Context): FavouriteBookReposotiry {
        return FavouriteBookRepositoryImpl(context = context)
    }
}