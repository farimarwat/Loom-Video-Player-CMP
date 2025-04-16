package com.farimarwat.loom.di

import com.farimarwat.loom.data.remote.MovieApi
import com.farimarwat.loom.data.repository.MovieRepositoryImpl
import com.farimarwat.loom.domain.repository.MovieRepository
import com.farimarwat.loom.domain.usecase.GetMoviesUseCase
import com.farimarwat.loom.presentation.viewmodel.MovieViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sharedModules = module {
    singleOf(::MovieApi)
    singleOf(::MovieRepositoryImpl).bind<MovieRepository>()
    singleOf(::GetMoviesUseCase)
    viewModelOf(::MovieViewModel)
}

expect val platformModules:Module
expect val client:HttpClient