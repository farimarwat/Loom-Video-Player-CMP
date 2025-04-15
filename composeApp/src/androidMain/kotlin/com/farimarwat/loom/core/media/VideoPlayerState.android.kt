package com.farimarwat.loom.core.media

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


actual class VideoPlayerState
    (
    private val context: Context
){
    val player = ExoPlayer.Builder(context).build()
    private var isMediaLoaded  by mutableStateOf(false)

    actual var isPlaying: Boolean by mutableStateOf(false)
    actual var progress: Float by mutableStateOf(0f)
    actual var duration: Long by mutableStateOf(0L)
    actual var currentPosition: Long by mutableStateOf(0L)


    init {
        player.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                this@VideoPlayerState.isPlaying = isPlaying
            }
            override fun onPlaybackStateChanged(state: Int) {
                if (state == Player.STATE_READY) {
                    this@VideoPlayerState.duration = player.duration
                }
            }

        })

        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                delay(1000)
                if (player.isPlaying) {
                    currentPosition = player.currentPosition
                    progress = if (duration > 0) currentPosition.toFloat() / duration else 0f
                }
            }
        }
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
    return remember {VideoPlayerState(context) }.also {
        DisposableEffect(it) {
            onDispose { it.release() }
        }
    }
}
