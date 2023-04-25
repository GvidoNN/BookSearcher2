package com.example.booksearcher2.presentation.favourite

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearcher2.R
import com.example.booksearcher2.databinding.FragmentFavouriteBinding
import com.example.booksearcher2.databinding.FragmentMainBinding
import com.example.booksearcher2.domain.models.database.FavouriteBook
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouriteFragment : Fragment(R.layout.fragment_favourite) {


    private val viewModel: FavouriteViewModel by viewModels()
    private lateinit var favouriteRecyclerView: RecyclerView
    private lateinit var adapter: FavouriteAdapter
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var bundle: Bundle

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavouriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayAllBooks()

        favouriteRecyclerView = requireView().findViewById(R.id.recyclerViewFavouriteBooks)
        favouriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavouriteAdapter()
        favouriteRecyclerView.adapter = adapter

        adapter.setOnDeleteBookClickListener(object : FavouriteAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                deleteBookData(
                    id = adapter.favouriteBookList[position].id,
                    title = adapter.favouriteBookList[position].title,
                    author = adapter.favouriteBookList[position].author,
                    coverUrl = adapter.favouriteBookList[position].coverUrl,
                    borrowUrl = adapter.favouriteBookList[position].borrowUrl
                )
            }
        })

        adapter.setOnReadBookListener(object  : FavouriteAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                bundle = Bundle()
                if(adapter.favouriteBookList[position].borrowUrl != "null"){
                    bundle.putString("url", adapter.favouriteBookList[position].borrowUrl)
                    findNavController().navigate(R.id.action_favouriteFragment_to_webViewFragmentReadBook, bundle)
                } else {
                    Toast.makeText(requireContext(), R.string.nan_borrow, Toast.LENGTH_SHORT).show()
                }

            }
        })
    }

    private fun displayAllBooks() {
        viewModel.books.observe(viewLifecycleOwner) {
            binding.tvFavouriteInfo.isVisible = it.size == 0
            adapter.setFavouriteList(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun deleteBookData(id: Int, title: String, author: String, coverUrl: String, borrowUrl: String) {
        viewModel.deleteBook(
            book = FavouriteBook(
                id = id,
                title = title,
                author = author,
                coverUrl = coverUrl,
                borrowUrl = borrowUrl
            )
        )
    }

}