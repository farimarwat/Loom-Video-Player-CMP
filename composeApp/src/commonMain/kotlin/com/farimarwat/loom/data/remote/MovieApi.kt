package com.farimarwat.loom.data.remote

import com.farimarwat.loom.domain.model.Movie
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.serialization.json.Json

class MovieApi(private val client:HttpClient){
    suspend fun getMovies():List<Movie>{
        val response: String = client.get {
            url("https://raw.githubusercontent.com/farimarwat/Loom-Video-Player-CMP/main/movies.txt")
        }.body()

        val movies: List<Movie> = Json.decodeFromString(response)
        return movies
    }
}