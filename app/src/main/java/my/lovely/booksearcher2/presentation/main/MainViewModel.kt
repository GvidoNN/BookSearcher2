package my.lovely.booksearcher2.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import my.lovely.booksearcher2.domain.models.api.DataResponse
import my.lovely.booksearcher2.domain.models.database.FavouriteBook
import my.lovely.booksearcher2.domain.usecase.GetDaoDbUseCase
import my.lovely.booksearcher2.domain.usecase.SearchInsideUseCase
import my.lovely.booksearcher2.domain.usecase.SpeechRecognizerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import my.lovely.booksearcher2.domain.ResourseWrapper
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchInsideUseCase: SearchInsideUseCase,
    private val getDaoDbUseCase: GetDaoDbUseCase,
    private val resourseWrapper: ResourseWrapper,
    private val speechRecognizerUseCase: SpeechRecognizerUseCase
) : ViewModel() {

    private val searchInsideLiveData = MutableLiveData<DataResponse>()
    private var progressBarLiveData = MutableLiveData<Boolean>()
    private var statusOfSpeakingLiveData = speechRecognizerUseCase.statusOfSpeaking
    private val textToSpeechLiveData = speechRecognizerUseCase.text

    val searchInside: LiveData<DataResponse>
        get() = searchInsideLiveData

    val progressBar: LiveData<Boolean>
        get() = progressBarLiveData

    val statusOfSpeaking: LiveData<Boolean>
        get() = statusOfSpeakingLiveData

    val textToSpeech: LiveData<String>
        get() = textToSpeechLiveData
    fun searchResponce(text: String) = viewModelScope.launch(Dispatchers.IO) {
        progressBarLiveData.postValue(true)
        var result = searchInsideUseCase.getSearchInside(text)
        searchInsideLiveData.postValue(result?.body() ?: null)
        progressBarLiveData.postValue(false)
    }

    fun insertBook(book: FavouriteBook) = viewModelScope.launch(Dispatchers.IO) {
        getDaoDbUseCase.getDaoDb().insertBook(book)
    }

    fun checkBook(position: Int, adapter: SearchInsideAdapter): FavouriteBook {
        val bookData = adapter.searchInsideList[position].edition
        return try {
            FavouriteBook(
                id = 0,
                title = bookData.title,
                author = bookData.authors[0].name,
                coverUrl = bookData.coverUrl,
                borrowUrl = bookData.borrowUrl
            )
        } catch (e: java.lang.NullPointerException) {
            FavouriteBook(
                id = 0,
                title = resourseWrapper.getTitle(),
                author = resourseWrapper.getAuthor(),
                coverUrl = "null",
                borrowUrl = "null"
            )
        }
    }

    fun giveSpeechInterface(): SpeechRecognizerUseCase{
        return speechRecognizerUseCase
    }

}