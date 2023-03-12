package com.example.booksearcher2.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.booksearcher2.R
import com.example.booksearcher2.data.api.DataObject
import com.example.booksearcher2.data.api.DataService
import com.example.booksearcher2.data.repository.SearchInsideRepositoryImpl

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var apiService: DataService
    private val searchInsideRepository by lazy{SearchInsideRepositoryImpl(apiService)}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            apiService = DataObject.getInstance()

        mainViewModel = ViewModelProvider(
            requireActivity(),
            MainViewModelFactory(searchInsideRepository)
        ).get(MainViewModel::class.java)

        mainViewModel.searchInside.observe(viewLifecycleOwner){
            it.hits.hits.forEach {
                Log.d("MyLog","${it.edition.title}")

            }
        }
    }
}