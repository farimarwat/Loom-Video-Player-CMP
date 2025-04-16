package com.farimarwat.loom.data.remote

import com.farimarwat.loom.domain.model.Movie
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow

class MovieApi(private val client:HttpClient){
    suspend fun getMovies():Flow<List<Movie>>{
        return client.get {
            url()
        }
            .body<Flow<List<Movie>>>()
    }
}