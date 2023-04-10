package com.example.booksearcher2.domain.models.api

import com.google.gson.annotations.SerializedName

data class Edition(
    val authors: List<Author>,
    @SerializedName("borrow_url")
    val borrowUrl: String,
    @SerializedName("cover_url")
    val coverUrl: String,
    val key: String,
    val title: String,
    val url: String,
    @SerializedName("work_key")
    val workKey: String
)