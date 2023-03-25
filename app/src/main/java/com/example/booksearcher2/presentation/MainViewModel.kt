package com.example.booksearcher2.presentation
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksearcher2.domain.models.api.DataResponce
import com.example.booksearcher2.domain.usecase.SearchInsideUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val searchInsideUseCase: SearchInsideUseCase): ViewModel() {

    private val searchInsideLiveData = MutableLiveData<DataResponce>()
    private var progressBarLiveData = MutableLiveData<Boolean>()

    val searchInside : LiveData<DataResponce>
    get() = searchInsideLiveData

    val progressBar: LiveData<Boolean>
    get() = progressBarLiveData

    fun searchResponce(text: String) = viewModelScope.launch {
        progressBarLiveData.postValue(true)
        var result = searchInsideUseCase.getSearchInside(text)
//        if(result?.body() != null){
//            searchInsideLiveData.postValue(result.body())
//            Log.d("MyLog","тут $searchInsideLiveData")
//        }
        searchInsideLiveData.postValue(result?.body() ?: null)

        progressBarLiveData.postValue(false)
    }

}