package com.example.booksearcher2.presentation.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksearcher2.R
import com.example.booksearcher2.domain.models.api.Hit

class SearchInsideAdapter() :
    RecyclerView.Adapter<SearchInsideAdapter.InsideSearchViewHolder>() {

    private lateinit var context: Context
    private lateinit var favouriteBookListener: OnItemClickListener

    var searchInsideList = mutableListOf<Hit>()

    fun setMovieList(book: List<Hit>) {
        this.searchInsideList = book.toMutableList()
        notifyDataSetChanged()
    }

    class InsideSearchViewHolder(itemView: View, listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val tvTextName: TextView = itemView.findViewById(R.id.tvTextName)
        val tvAuthorName: TextView = itemView.findViewById(R.id.tvAuthorName)
        val imCoverBook: ImageView = itemView.findViewById(R.id.imCoverBook)
        val tvSubject: TextView = itemView.findViewById(R.id.tvSubject)
        val imAddToFavourite: ImageButton = itemView.findViewById(R.id.imAddToFavourite)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
        init {
            imAddToFavourite.setOnClickListener{
                imAddToFavourite.setImageResource(R.drawable.icon_favourite_true)
                imAddToFavourite.isClickable = false
                listener.onItemClick(adapterPosition)
                Log.d("MyLog","Click on this shit")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsideSearchViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.inside_search_item, parent, false)
        return InsideSearchViewHolder(view, favouriteBookListener)

    }

    override fun getItemCount(): Int {
        return searchInsideList.size
    }

    override fun onBindViewHolder(holder: InsideSearchViewHolder, position: Int) {
        val bookData = searchInsideList[position]
        try {
            val url = "https:" + bookData.edition.cover_url
            Glide.with(holder.itemView).load(url).into(holder.imCoverBook)
        } catch (e: Exception) {
            holder.imCoverBook.setImageResource(R.drawable.nocover)
        }
        try {
            holder.tvTextName.text = bookData.edition.title
        } catch (e: Exception) {
            holder.tvTextName.text = "Non Title"
        }
        try {
            holder.tvAuthorName.text = bookData.edition.authors[0].name
        } catch (e: java.lang.Exception) {
            holder.tvAuthorName.text = "Non Author"
        }
        var textSub = bookData.highlight.text[0]
        holder.tvSubject.text = editSubjectText(textSub)
        val isExpandable: Boolean = bookData.isExpandable
        holder.tvSubject.visibility = if (isExpandable) View.VISIBLE else View.GONE
        holder.imAddToFavourite.visibility = if(isExpandable) View.GONE else View.VISIBLE

        holder.constraintLayout.setOnClickListener {
            isAnyItemExpanded(position)
            bookData.isExpandable = !bookData.isExpandable
            notifyItemChanged(position)
        }
    }

    private fun isAnyItemExpanded(position: Int) {
        val temp = searchInsideList.indexOfFirst {
            it.isExpandable
        }
        if (temp >= 0 && temp != position) {
            searchInsideList[temp].isExpandable = false
            notifyItemChanged(temp, 0)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        favouriteBookListener = listener
    }

    private fun editSubjectText(text: String): String{
        val newtext = text.replace("{{{","| ").replace("}}}"," |")
        return newtext
    }

}