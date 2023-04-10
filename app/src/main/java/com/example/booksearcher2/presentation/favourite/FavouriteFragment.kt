package com.example.booksearcher2.presentation.favourite

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearcher2.R
import com.example.booksearcher2.domain.models.database.FavouriteBook
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouriteFragment : Fragment(R.layout.fragment_favourite) {


    private val viewModel: FavouriteViewModel by viewModels()
    private lateinit var favouriteRecyclerView: RecyclerView
    private lateinit var adapter: FavouriteAdapter
    private lateinit var tvInfo: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayAllBooks()

        tvInfo = requireView().findViewById(R.id.tvFavouriteInfo)
        favouriteRecyclerView = requireView().findViewById(R.id.recyclerViewFavouriteBooks)
        favouriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavouriteAdapter()
        favouriteRecyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : FavouriteAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                deleteBookData(
                    id = adapter.favouriteBookList[position].id,
                    title = adapter.favouriteBookList[position].title,
                    author = adapter.favouriteBookList[position].author,
                    coverUrl = adapter.favouriteBookList[position].coverUrl
                )
            }

        })
    }

    private fun displayAllBooks() {
        viewModel.books.observe(viewLifecycleOwner) {
            if (it.size == 0) tvInfo.isVisible = true else tvInfo.isVisible = false
            adapter.setFavouriteList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun deleteBookData(id: Int, title: String, author: String, coverUrl: String) {
        viewModel.deleteBook(
            book = FavouriteBook(
                id = id,
                title = title,
                author = author,
                coverUrl = coverUrl
            )
        )
    }

}