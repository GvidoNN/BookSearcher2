package com.example.booksearcher2.presentation.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
    private lateinit var recyclerView: RecyclerView
    private lateinit var edEnter: EditText
    private lateinit var adapter: SearchInsideAdapter
    private lateinit var btSearch: ImageButton
    private lateinit var progressBar: ProgressBar
    private lateinit var errorContainer: LinearLayout
    private lateinit var btErrorTryAgain: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findItems()
        apiService = DataObject.getInstance()
        adapter = SearchInsideAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

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
                saveBookData(position = position)
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

    private fun saveBookData(position: Int) {
        mainViewModel.insertBook(
            mainViewModel.checkBook(position = position, adapter = adapter)
        )

    }
}