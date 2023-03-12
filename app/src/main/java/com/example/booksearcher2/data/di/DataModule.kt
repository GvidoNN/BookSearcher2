package com.example.booksearcher2.data.di

import com.example.booksearcher2.data.api.DataService
import com.example.booksearcher2.data.repository.SearchInsideRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DataModule {
    @Provides
    fun provideSearchInsideRepositoryImpl(dataService: DataService) : SearchInsideRepositoryImpl{
        return SearchInsideRepositoryImpl(dataService = dataService)

    }
}