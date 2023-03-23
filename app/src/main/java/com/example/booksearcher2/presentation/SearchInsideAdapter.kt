package com.example.booksearcher2.presentation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksearcher2.R
import com.example.booksearcher2.domain.models.api.DataResponce
import com.example.booksearcher2.domain.models.api.Hit

class SearchInsideAdapter() :
    RecyclerView.Adapter<SearchInsideAdapter.InsideSearchViewHolder>() {
    private lateinit var context: Context

    var searchInsideList = mutableListOf<Hit>()

    fun setMovieList(book: List<Hit>) {
        this.searchInsideList = book.toMutableList()
        notifyDataSetChanged()
    }

    class InsideSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTextName: TextView = itemView.findViewById(R.id.tvTextName)
        val tvAuthorName: TextView = itemView.findViewById(R.id.tvAuthorName)
        val imCoverBook: ImageView = itemView.findViewById(R.id.imCoverBook)
        val tvSubject: TextView = itemView.findViewById(R.id.tvSubject)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsideSearchViewHolder {
        context = parent.context
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.inside_search_item, parent, false)
        return InsideSearchViewHolder(view)

    }

    override fun getItemCount(): Int {
        return searchInsideList.size
    }

    override fun onBindViewHolder(holder: InsideSearchViewHolder, position: Int) {
        Log.d("MyLog", "долшло")
        val bookData = searchInsideList[position]
        try {
            val url = "https:" + bookData.edition.cover_url
            Glide.with(holder.itemView).load(url).into(holder.imCoverBook)
            holder.tvTextName.text = bookData.edition.title
            holder.tvAuthorName.text = bookData.edition.authors[0].name
            holder.tvSubject.text = bookData.highlight.text[0]
            val isExpandable: Boolean = bookData.isExpandable
            holder.tvSubject.visibility = if (isExpandable) View.VISIBLE else View.GONE
            holder.constraintLayout.setOnClickListener {
                isAnyItemExpanded(position)
                bookData.isExpandable = !bookData.isExpandable
                notifyItemChanged(position)
            }
        } catch (e: Exception) {
            val url = "https:" + "//covers.openlibrary.org/w/id/511582-M.jpg"
            Glide.with(holder.itemView).load(url).into(holder.imCoverBook)
            holder.tvTextName.text = "Non Title"
            holder.tvAuthorName.text = "Non Author"
            holder.tvSubject.text = bookData.highlight.text[0]
            val isExpandable: Boolean = bookData.isExpandable
            holder.tvSubject.visibility = if (isExpandable) View.VISIBLE else View.GONE
            holder.constraintLayout.setOnClickListener {
                isAnyItemExpanded(position)
                bookData.isExpandable = !bookData.isExpandable
                notifyItemChanged(position)
            }

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
}