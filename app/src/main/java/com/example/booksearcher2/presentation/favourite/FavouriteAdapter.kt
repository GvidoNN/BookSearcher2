package com.example.booksearcher2.presentation.favourite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearcher2.R
import com.example.booksearcher2.domain.models.database.FavouriteBook
import com.example.booksearcher2.presentation.main.SearchInsideAdapter

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.FavouriteBookViewHolder>() {

    private lateinit var context: Context
    var favouriteBookList = mutableListOf<FavouriteBook>()
    class FavouriteBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFavouriteTitle: TextView = itemView.findViewById(R.id.tvFavouriteTitle)
        val tvAuthorName: TextView = itemView.findViewById(R.id.tvFavoriteAuthor)
        val imCoverBook: ImageView = itemView.findViewById(R.id.imFavouriteCover)
    }

    fun setFavouriteList(favouriteBook: List<FavouriteBook>){
        this.favouriteBookList = favouriteBook.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteBookViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favourite_book_item, parent, false)
        return FavouriteBookViewHolder(view)

    }
    override fun onBindViewHolder(holder: FavouriteBookViewHolder, position: Int) {
        val favouriteBookData = favouriteBookList[position]
        holder.tvAuthorName.text = favouriteBookData.author
        holder.tvFavouriteTitle.text = favouriteBookData.title
        holder.imCoverBook.setImageResource(R.drawable.nocover)
    }
    override fun getItemCount(): Int {
        return favouriteBookList.size
    }
}