package com.example.booksearcher2.domain.models.api

data class Edition(
    val authors: List<Author>,
    val borrow_url: String,
    val cover_url: String,
    val key: String,
    val title: String,
    val url: String,
    val work_key: String
)