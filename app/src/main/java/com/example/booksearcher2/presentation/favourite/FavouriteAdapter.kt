package com.example.booksearcher2.presentation.favourite

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksearcher2.R
import com.example.booksearcher2.domain.models.database.FavouriteBook
import com.example.booksearcher2.presentation.main.SearchInsideAdapter

class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.FavouriteBookViewHolder>() {

    private lateinit var context: Context
    var favouriteBookList = mutableListOf<FavouriteBook>()
    private lateinit var deleteFavouriteBookListener: OnItemClickListener

    fun setFavouriteList(favouriteBook: List<FavouriteBook>){
        this.favouriteBookList = favouriteBook.toMutableList()
        notifyDataSetChanged()
    }

    class FavouriteBookViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvFavouriteTitle: TextView = itemView.findViewById(R.id.tvFavouriteTitle)
        val tvAuthorName: TextView = itemView.findViewById(R.id.tvFavoriteAuthor)
        val imCoverBook: ImageView = itemView.findViewById(R.id.imFavouriteCover)
        val imDeleteBook: ImageButton = itemView.findViewById(R.id.imFavouriteDeleteBook)
        init {
            imDeleteBook.setOnClickListener{
                listener.onItemClick(adapterPosition)
                Log.d("MyLog","Delete this shit id")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteBookViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.favourite_book_item, parent, false)
        return FavouriteBookViewHolder(view, deleteFavouriteBookListener)

    }
    override fun onBindViewHolder(holder: FavouriteBookViewHolder, position: Int) {
        val favouriteBookData = favouriteBookList[position]
        holder.tvAuthorName.text = favouriteBookData.author
        holder.tvFavouriteTitle.text = favouriteBookData.title
        val url = "https:" + favouriteBookData.coverUrl
        Glide.with(holder.itemView).load(url).into(holder.imCoverBook)
    }
    override fun getItemCount(): Int {
        return favouriteBookList.size
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        deleteFavouriteBookListener = listener
    }
}