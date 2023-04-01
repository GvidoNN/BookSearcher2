package com.example.booksearcher2.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearcher2.R
import com.example.booksearcher2.data.api.DataObject
import com.example.booksearcher2.data.api.DataService
import com.example.booksearcher2.domain.models.database.FavouriteBook
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var apiService: DataService
    lateinit var recyclerView: RecyclerView
    lateinit var edEnter: EditText
    lateinit var adapter: SearchInsideAdapter
    lateinit var btSearch: ImageButton
    lateinit var progressBar: ProgressBar
    lateinit var errorContainer: LinearLayout
    lateinit var btErrorTryAgain: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findItems()
        apiService = DataObject.getInstance()
        adapter = SearchInsideAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        saveBookData()

        mainViewModel.searchInside.observe(viewLifecycleOwner) { result ->
            if (result != null) {
                adapter.setMovieList(result.hits.hits)
                errorContainer.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            } else {
                errorContainer.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
        }

        btSearch.setOnClickListener {
            setOnClick()
        }

        btErrorTryAgain.setOnClickListener {
            setOnClick()
        }

        adapter.setOnItemClickListener(object: SearchInsideAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {

                Log.d("MyLog","$position")
            }

        })


    }

    fun setOnClick() {
        var text = edEnter.text.toString()
        mainViewModel.searchResponce(text)

        mainViewModel.progressBar.observe(viewLifecycleOwner) {
            if (it == true) {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
                btSearch.isClickable = false
            } else {
                progressBar.visibility = View.GONE
                btSearch.isClickable = true
            }
        }
    }

    fun findItems() {
        recyclerView = requireView().findViewById(R.id.recyclerView)
        btSearch = requireView().findViewById(R.id.btSearch)
        edEnter = requireView().findViewById(R.id.edEnter)
        progressBar = requireView().findViewById(R.id.progressBar)
        errorContainer = requireView().findViewById(R.id.errorContainer)
        btErrorTryAgain = requireView().findViewById(R.id.btErrorTryAgain)
    }

    private fun saveBookData() {
        mainViewModel.insertBook(
            book = FavouriteBook(
                id = 0,
                title = "ligma",
                author = "ligma",
                coverUrl = "https://ligma.com"
            )
        )
    }
}