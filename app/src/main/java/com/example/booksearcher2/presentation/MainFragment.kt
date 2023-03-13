package com.example.booksearcher2.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SearchInsideAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = DataObject.getInstance()

        recyclerView = requireView().findViewById(R.id.recyclerView)
        adapter = SearchInsideAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mainViewModel.searchResponce()

        mainViewModel.searchInside.observe(viewLifecycleOwner){
            adapter.setMovieList(it.hits.hits)
            it.hits.hits.forEach {
                Log.d("MyLog","")
                Log.d("MyLog","${it.edition.title}")
                Log.d("MyLog","${it.edition.isExpandable}")
            }
        }
    }
}