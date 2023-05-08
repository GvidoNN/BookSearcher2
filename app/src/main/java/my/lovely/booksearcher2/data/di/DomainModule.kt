package my.lovely.booksearcher2.data.di

import my.lovely.booksearcher2.domain.repository.FavouriteBookReposotiry
import my.lovely.booksearcher2.domain.repository.SearchInsideRepository
import my.lovely.booksearcher2.domain.usecase.GetDaoDbUseCase
import my.lovely.booksearcher2.domain.usecase.SearchInsideUseCase
import my.lovely.booksearcher2.domain.usecase.SpeechRecognizerUseCase
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

    @Provides
    fun provideSpeechRecognizerUseCase(): SpeechRecognizerUseCase {
        return SpeechRecognizerUseCase()
    }
}