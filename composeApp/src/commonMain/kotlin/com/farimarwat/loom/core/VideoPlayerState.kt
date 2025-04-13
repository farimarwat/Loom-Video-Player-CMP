package com.farimarwat.loom.core


import androidx.compose.runtime.*

expect class VideoPlayerState {
    var isPlaying: Boolean
    var progress: Float
    var duration: Long
    var currentPosition: Long

    fun loadVideo(url: String)
    fun togglePlay()
    fun seekTo(progress: Float)
    fun release()
}

@Composable
expect fun rememberVideoPlayerState():VideoPlayerState
