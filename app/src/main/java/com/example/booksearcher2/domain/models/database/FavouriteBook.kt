package com.example.booksearcher2.domain.models.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favourite_book_data_table")
data class FavouriteBook(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "book_id")
    var id: Int,
    @ColumnInfo(name = "book_title")
    var title: String,
    @ColumnInfo(name = "book_author")
    var author: String,
    @ColumnInfo(name = "cover_url")
    var coverUrl: String
)
