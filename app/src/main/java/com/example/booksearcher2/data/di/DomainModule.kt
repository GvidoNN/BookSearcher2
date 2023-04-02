package com.example.booksearcher2.data.di

import com.example.booksearcher2.domain.repository.FavouriteBookReposotiry
import com.example.booksearcher2.domain.repository.SearchInsideRepository
import com.example.booksearcher2.domain.usecase.GetDaoDbUseCase
import com.example.booksearcher2.domain.usecase.SearchInsideUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideSearchInsideUseCase(searchInsideRepository: SearchInsideRepository): SearchInsideUseCase{
        return SearchInsideUseCase(searchInsideRepository = searchInsideRepository)
    }

    @Provides
    fun provideGetDaoDBUseCase(favouriteBookReposotiry: FavouriteBookReposotiry): GetDaoDbUseCase {
        return GetDaoDbUseCase(favouriteBookRepository = favouriteBookReposotiry)
    }
}