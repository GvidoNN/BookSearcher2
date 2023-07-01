package my.lovely.booksearcher2.di

import android.content.Context
import my.lovely.booksearcher2.domain.repository.FavouriteBookReposotiry
import my.lovely.booksearcher2.domain.repository.SearchInsideRepository
import my.lovely.booksearcher2.domain.usecase.GetDaoDbUseCase
import my.lovely.booksearcher2.domain.usecase.SearchInsideUseCase
import my.lovely.booksearcher2.domain.usecase.SpeechRecognizerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import my.lovely.booksearcher2.domain.ResourseWrapper


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

    @Provides
    fun provideResourseWrapper(@ApplicationContext context: Context): ResourseWrapper {
        return ResourseWrapper(context = context)
    }
}