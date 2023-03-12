package com.example.booksearcher2.domain.models.api

data class Hits(
    val hits: List<Hit>,
    val total: Int
)