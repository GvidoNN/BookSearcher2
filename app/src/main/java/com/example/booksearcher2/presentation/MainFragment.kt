package com.example.booksearcher2.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.booksearcher2.R
import com.example.booksearcher2.data.api.DataObject
import com.example.booksearcher2.data.api.DataService
import com.example.booksearcher2.data.repository.SearchInsideRepositoryImpl
import dagger.hilt.android.AndroidEntryPoint

//    private lateinit var mainViewModel: MainViewModel

//        mainViewModel = ViewModelProvider(
//            requireActivity(),
//            MainViewModelFactory(searchInsideRepository)
//        ).get(MainViewModel::class.java)

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var apiService: DataService

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            apiService = DataObject.getInstance()


        mainViewModel.searchResponce()

        mainViewModel.searchInside.observe(viewLifecycleOwner){
            it.hits.hits.forEach {
                Log.d("MyLog","${it.edition.title}")
            }
        }
    }
}