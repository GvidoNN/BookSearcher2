package com.example.booksearcher2.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
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
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var apiService: DataService
    lateinit var recyclerView: RecyclerView
    lateinit var edEnter: EditText
    lateinit var adapter: SearchInsideAdapter
    lateinit var btSearch: ImageButton
//    lateinit var progressBar: ProgressBar
//    lateinit var errorContainer: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiService = DataObject.getInstance()

        recyclerView = requireView().findViewById(R.id.recyclerView)
        btSearch = requireView().findViewById(R.id.btSearch)
        edEnter = requireView().findViewById(R.id.edEnter)
//        progressBar = requireView().findViewById(R.id.progressBar)
//        errorContainer = requireView().findViewById(R.id.errorContainer)
        adapter = SearchInsideAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        btSearch.setOnClickListener{
            var text = edEnter.text.toString()
            mainViewModel.searchResponce(text)
            mainViewModel.searchInside.observe(viewLifecycleOwner){ result ->
                adapter.setMovieList(result.hits.hits)
            }
        }
    }
}