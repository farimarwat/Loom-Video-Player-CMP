package com.farimarwat.loom.core.media

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext


actual class VideoPlayerState
    (
    private val context: Context
){
    val player = ExoPlayer.Builder(context).build()
    private var isMediaLoaded = false

    actual var isPlaying: Boolean = false
    actual var progress: Float = 0f
    actual var duration: Long = 0L
    actual var currentPosition: Long = 0L


    init {
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                this@VideoPlayerState.isPlaying = isPlaying
            }
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_READY){
                    currentPosition = player.currentPosition
                }
            }

        })
    }

    actual fun loadVideo(url: String) {
        isMediaLoaded = false
        player.setMediaItem(MediaItem.fromUri(url))
        player.prepare()
        player.play()
    }

    actual fun togglePlay() {
        if (player.isPlaying) player.pause() else player.play()
    }

    actual fun seekTo(progress: Float) {
        player.seekTo((progress * duration).toLong())
    }

    actual fun release() {
        player.release()
    }
}

@Composable
actual fun rememberLoopVideoPlayerState(): VideoPlayerState {
    val context = LocalContext.current
    return remember { com.farimarwat.loom.core.media.VideoPlayerState(context) }.also {
        DisposableEffect(it) {
            onDispose { it.release() }
        }
    }
}
