package com.farimarwat.loom.data.repository

import com.farimarwat.loom.data.remote.MovieApi
import com.farimarwat.loom.domain.model.Movie
import com.farimarwat.loom.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class MovieRepositoryImpl(
    private val movieApi: MovieApi
):MovieRepository {
    override suspend fun getMovies(): List<Movie> {
        return movieApi.getMovies()
    }
}