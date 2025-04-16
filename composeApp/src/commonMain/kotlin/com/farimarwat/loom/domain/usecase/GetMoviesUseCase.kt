package com.farimarwat.loom.domain.usecase

import com.farimarwat.loom.data.remote.MovieApi
import com.farimarwat.loom.domain.model.Movie
import com.farimarwat.loom.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase(
    private val repo:MovieRepository
) {
    operator suspend fun invoke():List<Movie>{
        return repo.getMovies()
    }
}