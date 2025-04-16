package com.farimarwat.loom.domain.repository

import com.farimarwat.loom.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovies():List<Movie>
}