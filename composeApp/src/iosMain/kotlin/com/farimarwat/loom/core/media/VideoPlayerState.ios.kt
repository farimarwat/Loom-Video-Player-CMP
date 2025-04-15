package com.farimarwat.loom.core.media

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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import platform.CoreMedia.CMTime
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.CoreMedia.CMTimeScale
import platform.darwin.NSEC_PER_SEC


actual class VideoPlayerState {

    var player:AVPlayer? = null
    private var timeObserver:Any? = null
    actual var isPlaying: Boolean by mutableStateOf(false)
    actual var progress: Float by mutableStateOf(0F)
    actual var duration: Long by mutableStateOf(0L)
    actual var currentPosition: Long by mutableStateOf(0L)

    @OptIn(ExperimentalForeignApi::class)
    actual fun loadVideo(url: String) {
        release()
        val nsUrl = NSURL.URLWithString(url)
        nsUrl?.let{nu ->
            player = AVPlayer(uRL = nu).apply {
                timeObserver = addPeriodicTimeObserverForInterval(
                    interval = CMTimeMakeWithSeconds(1.0, preferredTimescale = NSEC_PER_SEC.toInt())
                    ,
                    queue = dispatch_get_main_queue()
                ) { time ->
                    isPlaying = player?.rate != 0f
                    if(duration == 0L){
                        player?.currentItem?.let {
                            val totalDuration = CMTimeGetSeconds(it.duration)
                            if (totalDuration.isFinite()) {
                                duration = (totalDuration * 1000).toLong() // Also in milliseconds
                            }
                        }
                    }
                    currentPosition = (CMTimeGetSeconds(time) * 1000).toLong() // In milliseconds
                    progress = if (duration > 0) currentPosition.toFloat() / duration else 0f
                    println("Progress: ${progress}")
                }
            }

            player?.play()
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

    @OptIn(ExperimentalForeignApi::class)
    actual fun seekTo(progress: Float) {
        val targetTimeInSeconds = progress * duration / 1000.0 // duration is in milliseconds, so we divide by 1000
        val targetTime = CMTimeMakeWithSeconds(targetTimeInSeconds, preferredTimescale = NSEC_PER_SEC.toInt())
        player?.seekToTime(targetTime)
    }


    actual fun release() {
        if (timeObserver != null) {
            (timeObserver as? NSObject)?.let { observer ->
                try {
                    player?.removeTimeObserver(observer)
                    player?.currentItem?.removeObserver(observer, "duration")
                } catch (_: Exception) {
                    // Safely ignore if already removed or invalid
                }
            }
            timeObserver = null
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
actual fun rememberLoopVideoPlayerState(): VideoPlayerState {
    return remember { com.farimarwat.loom.core.media.VideoPlayerState() }.also {
        DisposableEffect(it) {
            onDispose { it.release() }
        }
    }
}
