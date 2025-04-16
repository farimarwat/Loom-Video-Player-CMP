package com.farimarwat.loom.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val description: String,
    val sources: List<String>,
    val subtitle: String,
    val thumb: String,
    val title: String
){
    fun getThumbnailUrl():String{
        return "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/$thumb"
    }
}