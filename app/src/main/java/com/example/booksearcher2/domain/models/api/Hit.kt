package com.example.booksearcher2.domain.models.api

data class Hit(
    val edition: Edition,
    val highlight: Highlight,
    var isExpandable: Boolean = false
)