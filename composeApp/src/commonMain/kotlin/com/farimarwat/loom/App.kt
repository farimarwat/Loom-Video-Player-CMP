package com.farimarwat.loom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farimarwat.loom.core.media.LoomVideoPlayer
import com.farimarwat.loom.core.media.rememberLoopVideoPlayerState
import com.farimarwat.loom.utils.ScreenUtils
import com.farimarwat.loom.utils.rememberScreenUtilsState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val screenUtils = rememberScreenUtilsState()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            val playerState = rememberLoopVideoPlayerState()
            LaunchedEffect(Unit){
                playerState.loadVideo("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4")
            }

            LoomVideoPlayer(
                modifier = Modifier,
                playerState = playerState,
                onToggleFullScreen = {
                    screenUtils.setLandScape(it)
                }
            )
        }
    }
}