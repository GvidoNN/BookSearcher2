package com.example.booksearcher2.presentation.favourite

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.booksearcher2.R
import com.example.booksearcher2.data.database.FavouriteBookDataBase
import com.example.booksearcher2.domain.models.database.FavouriteBook

class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    private lateinit var btAdd: Button
    private lateinit var edTitle: EditText
    private lateinit var edAuthor: EditText
    private lateinit var edUrl: EditText

    private lateinit var viewModel: FavouriteViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btAdd = requireView().findViewById(R.id.btAddToBD)
        edTitle = requireView().findViewById(R.id.edTitle)
        edAuthor = requireView().findViewById(R.id.edAuthor)
        edUrl = requireView().findViewById(R.id.edUrl)

        val dao = FavouriteBookDataBase.getInstance(requireContext()).favouriteBookDao()
        val factory = FavouriteViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(FavouriteViewModel::class.java)

        btAdd.setOnClickListener{
            saveBookData()
        }


    }

    private fun saveBookData() {
//        val title = edTitle.text.toString()
//        val author = edAuthor.text.toString()
//        val url = edUrl.text.toString()
//        val book = FavouriteBook(title,author,url)
//        viewModel.insertBook(book)
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