package my.lovely.booksearcher2.data.di

import my.lovely.booksearcher2.data.database.FavouriteBookDao
import my.lovely.booksearcher2.data.database.FavouriteBookDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
class DataBaseModule {
    @Provides
    fun provideFavouriteBookDao(appDatabase: FavouriteBookDataBase): FavouriteBookDao {
        return appDatabase.favouriteBookDao()
    }
}