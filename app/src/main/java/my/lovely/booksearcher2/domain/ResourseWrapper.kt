package my.lovely.booksearcher2.domain

import android.content.Context
import my.lovely.booksearcher2.R
import javax.inject.Inject


class ResourseWrapper @Inject constructor(private val context: Context) {

    fun getTitle() = context.getString(R.string.nan_title)

    fun getAuthor() = context.getString(R.string.nan_author)

}