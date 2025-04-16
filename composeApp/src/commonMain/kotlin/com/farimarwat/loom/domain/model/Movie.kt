package com.farimarwat.loom.domain.model

data class Movie(
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
)