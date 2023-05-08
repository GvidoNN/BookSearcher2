package my.lovely.booksearcher2.data.di

import my.lovely.booksearcher2.data.api.DataService
import my.lovely.booksearcher2.data.database.FavouriteBookDao
import my.lovely.booksearcher2.data.repository.FavouriteBookRepositoryImpl
import my.lovely.booksearcher2.data.repository.SearchInsideRepositoryImpl
import my.lovely.booksearcher2.domain.repository.FavouriteBookReposotiry
import my.lovely.booksearcher2.domain.repository.SearchInsideRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideFavouriteBookRespositoryImpl(favouriteBookDao: FavouriteBookDao): FavouriteBookReposotiry {
        return FavouriteBookRepositoryImpl(favouriteBookDao = favouriteBookDao)
    }
}