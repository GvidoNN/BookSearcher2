package com.example.booksearcher2.presentation.favourite

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearcher2.R
import com.example.booksearcher2.data.database.FavouriteBookDao
import com.example.booksearcher2.data.database.FavouriteBookDataBase
import com.example.booksearcher2.domain.models.database.FavouriteBook
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouriteFragment : Fragment(R.layout.fragment_favourite) {


    private val viewModel: FavouriteViewModel by viewModels()
//    private lateinit var viewModel: FavouriteViewModel
    private lateinit var btAdd: Button
    private lateinit var btDelete: Button
    private lateinit var edTitle: EditText
    private lateinit var edAuthor: EditText
    private lateinit var edUrl: EditText
    private lateinit var favouriteRecyclerView: RecyclerView
    private lateinit var adapter: FavouriteAdapter
//    val favouriteBookRepositoryImpl by lazy {FavouriteBookRepositoryImpl(requireContext())}
//    val getDaoDbUseCase by lazy {GetDaoDbUseCase(favouriteBookRepositoryImpl)}


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btAdd = requireView().findViewById(R.id.btAddToBD)
        edTitle = requireView().findViewById(R.id.edTitle)
        edAuthor = requireView().findViewById(R.id.edAuthor)
        edUrl = requireView().findViewById(R.id.edUrl)
        btDelete = requireView().findViewById(R.id.btDelete)

//        val factory = FavouriteViewModelFactory(requireContext())
//        viewModel = ViewModelProvider(this, factory).get(FavouriteViewModel::class.java)

        displayAllBooks()

        favouriteRecyclerView = requireView().findViewById(R.id.recyclerViewFavouriteBooks)
        favouriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = FavouriteAdapter()
        favouriteRecyclerView.adapter = adapter




//        val dao = DataBaseObject.getInstance(requireContext()).favouriteBookDao()


        btAdd.setOnClickListener{
            saveBookData()
        }




    }

    private fun displayAllBooks(){
        viewModel.books.observe(viewLifecycleOwner) {
            Log.d("MyLog", "изменения прошли")
            adapter.setFavouriteList(it)
            adapter.notifyDataSetChanged()
        }

    }

    private fun deleteBookData(){
        viewModel.deleteBook(
            book = FavouriteBook(
                id = 0,
                title = edTitle.text.toString(),
                author = edAuthor.text.toString(),
                coverUrl = edUrl.text.toString()
            )
        )
    }
    private fun saveBookData() {
        viewModel.insertBook(
            book = FavouriteBook(
                id = 0,
                title = edTitle.text.toString(),
                author = edAuthor.text.toString(),
                coverUrl = edUrl.text.toString()
            )
        )
    }
}