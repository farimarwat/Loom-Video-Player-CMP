package com.farimarwat.loom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.farimarwat.loom.core.media.LoomVideoPlayer
import com.farimarwat.loom.core.media.rememberLoopVideoPlayerState
import com.farimarwat.loom.presentation.ui.MovieItem
import com.farimarwat.loom.presentation.viewmodel.MovieViewModel
import com.farimarwat.loom.utils.ScreenUtils
import com.farimarwat.loom.utils.rememberScreenUtilsState
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(
    viewModel: MovieViewModel = koinViewModel()
) {
    MaterialTheme {
        val screenUtils = rememberScreenUtilsState()
        val movies by viewModel.movies.collectAsStateWithLifecycle()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            val playerState = rememberLoopVideoPlayerState()
            var videoUrl by remember { mutableStateOf("") }
            LaunchedEffect(Unit){
                if(viewModel.movies.value.isEmpty()){
                    viewModel.getMovies()
                }
            }

            LaunchedEffect(videoUrl){
               if(videoUrl.isNotEmpty()){
                   playerState.loadVideo(videoUrl)
               }
            }
            AnimatedVisibility(
                visible = videoUrl.isNotEmpty()
            ){
                LoomVideoPlayer(
                    modifier = Modifier,
                    playerState = playerState,
                    onToggleFullScreen = {
                        screenUtils.setLandScape(it)
                    }
                )
            }
            LazyColumn {
                items(movies){item ->
                    MovieItem(item){
                        videoUrl = item.sources.first().replace("http","https")
                    }
                }
            }
        }
    }
}