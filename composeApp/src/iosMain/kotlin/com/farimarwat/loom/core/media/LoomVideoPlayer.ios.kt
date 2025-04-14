package com.farimarwat.loom.core.media

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVLayerVideoGravityResizeAspect
import platform.AVFoundation.AVPlayerLayer
import platform.UIKit.UIColor
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun LoomVideoPlayer(
    modifier: Modifier,
    playerState: VideoPlayerState,
    isFullScreen: Boolean
) {
    UIKitView(
        factory = {
            val view = UIView()
            val playerLayer = AVPlayerLayer()
            playerLayer.player = playerState.player
            playerLayer.videoGravity = AVLayerVideoGravityResizeAspect
            view.layer.addSublayer(playerLayer)
            playerLayer.frame = view.bounds
            view.backgroundColor = UIColor.blackColor
            view
        },
        modifier = modifier
    )
}


