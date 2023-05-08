package my.lovely.booksearcher2.presentation.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import my.lovely.booksearcher2.R
import my.lovely.booksearcher2.domain.models.api.Hit

class SearchInsideAdapter() :
    RecyclerView.Adapter<SearchInsideAdapter.InsideSearchViewHolder>() {

    private lateinit var context: Context
    private lateinit var favouriteBookListener: OnItemClickListener

    var searchInsideList = mutableListOf<Hit>()

    fun setMovieList(book: List<Hit>) {
        this.searchInsideList = book.toMutableList()
        notifyDataSetChanged()
    }

    class InsideSearchViewHolder(itemView: View, favouriteListener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
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
                favouriteListener.onItemClick(adapterPosition)
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
            val url = "https:" + bookData.edition.coverUrl
            Glide.with(holder.itemView).load(url).into(holder.imCoverBook)
        } catch (e: Exception) {
            holder.imCoverBook.setImageResource(R.drawable.nocover)
        }
        try {
            holder.tvTextName.text = bookData.edition.title
        } catch (e: Exception) {
            holder.tvTextName.text = context.getString(R.string.nan_title)
        }
        try {
            holder.tvAuthorName.text = bookData.edition.authors[0].name
        } catch (e: java.lang.Exception) {
            holder.tvAuthorName.text = context.getString(R.string.nan_author)
        }
        var textSub = bookData.highlight.text[0]
        holder.tvSubject.text = editSubjectText(textSub)
        val isExpandable: Boolean = bookData.isExpandable
        holder.tvSubject.isVisible = isExpandable
        holder.imAddToFavourite.isVisible = !isExpandable

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

    fun setOnFavouriteBookListener(listener: OnItemClickListener){
        favouriteBookListener = listener
    }

    private fun editSubjectText(text: String): String{
        val newtext = text.replace("{{{","| ").replace("}}}"," |")
        return newtext
    }

}