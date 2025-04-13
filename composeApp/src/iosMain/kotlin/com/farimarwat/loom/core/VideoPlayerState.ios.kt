package com.farimarwat.loom.core

import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.*
import platform.AVFoundation.addPeriodicTimeObserverForInterval
import platform.CoreMedia.CMTimeGetSeconds
import platform.CoreMedia.CMTimeMake
import platform.Foundation.NSURL
import platform.Foundation.removeObserver
import platform.darwin.NSObject
import platform.darwin.dispatch_get_main_queue
import androidx.compose.runtime.*


actual class VideoPlayerState {

    private var player:AVPlayer? = null
    private var timeObserver:Any? = null
    actual var isPlaying: Boolean = false
    actual var progress: Float = 0F
    actual var duration: Long = 0L
    actual var currentPosition: Long = 0L

    @OptIn(ExperimentalForeignApi::class)
    actual fun loadVideo(url: String) {
        release()
        val nsUrl = NSURL.URLWithString(url)
        nsUrl?.let{nu ->
            player = AVPlayer(uRL = nu).apply {
                timeObserver = addPeriodicTimeObserverForInterval(
                    interval = CMTimeMake(1,10),
                    queue = dispatch_get_main_queue()
                ) { time ->
                    currentPosition = (CMTimeGetSeconds(time)*100).toLong()
                }
            }
        }
    }

    actual fun togglePlay() {
        if(isPlaying){
            player?.pause()
        } else {
            player?.play()
        }
        isPlaying = !isPlaying
    }

    actual fun seekTo(progress: Float) {

    }

    actual fun release() {
        (timeObserver as? NSObject)?.let { observer ->
            player?.removeTimeObserver(observer)
        }
        (timeObserver as NSObject).let { observer ->
            player?.currentItem?.removeObserver(observer, "duration")
        }
        player?.replaceCurrentItemWithPlayerItem(null)
        player = null
        isPlaying = false
        progress = 0f
        duration = 0L
        currentPosition = 0L
    }

}

@Composable
actual fun rememberVideoPlayerState(): VideoPlayerState {
    return remember { VideoPlayerState() }.also {
        DisposableEffect(it) {
            onDispose { it.release() }
        }
    }
}
