package com.farimarwat.loom.core.media

import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import com.farimarwat.loom.core.ui.MediaControls


@Composable
actual fun LoomVideoPlayer(
    modifier: Modifier,
    playerState: VideoPlayerState,
    isFullScreen:Boolean) {
    Box(
        modifier = Modifier
            .then(modifier)
    ){
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = playerState.player
                    useController = false
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            update = { playerview ->
                playerview.player = playerState.player
            }
        )
        MediaControls(
            state = playerState
        )
    }
}