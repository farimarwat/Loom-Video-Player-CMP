package com.farimarwat.loom.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farimarwat.loom.domain.model.Movie
import com.farimarwat.loom.domain.usecase.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase
):ViewModel() {
    private var _movies:MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    val movies = _movies.asStateFlow()
     fun getMovies() = viewModelScope.launch(Dispatchers.IO){
        try{
            _movies.value =  getMoviesUseCase()
            println("Movies: ${_movies.value}")
        }catch (ex:Exception){
            println(ex.message)
        }
    }
}