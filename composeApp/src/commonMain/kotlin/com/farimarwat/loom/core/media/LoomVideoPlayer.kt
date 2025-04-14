package com.farimarwat.loom.core.media

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
expect fun LoomVideoPlayer(
    modifier:Modifier,
    playerState: VideoPlayerState,
    isFullScreen: Boolean
)