package com.farimarwat.loom.core.media

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.loom.core.ui.MediaControls
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVLayerVideoGravityResizeAspect
import platform.AVFoundation.AVLayerVideoGravityResizeAspectFill
import platform.AVFoundation.AVPlayerLayer
import platform.UIKit.UIColor
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun LoomVideoPlayer(
    modifier: Modifier,
    playerState: VideoPlayerState,
    onToggleFullScreen: (fullScreen: Boolean) -> Unit
) {
    var fullScreen by remember { mutableStateOf(false) }
    Box(
        modifier = if (fullScreen) {
            Modifier.fillMaxSize()
        } else {
            Modifier
                .fillMaxWidth()
                .height(300.dp)
        }
            .then(
                modifier
                    .background(Color.Black)
            )
    ) {
        val playerLayer = AVPlayerLayer()
        playerLayer.player = playerState.player
        playerLayer.videoGravity = AVLayerVideoGravityResizeAspectFill
        UIKitView(
            factory = {
                val container = UIView()
                playerLayer.frame = container.bounds
                container.layer.addSublayer(playerLayer)
                container.backgroundColor = UIColor.blackColor
                container
            },
            modifier = modifier
        )
        MediaControls(
            state = playerState,
            onFullScreenClicked = {
                fullScreen = !fullScreen
                onToggleFullScreen(fullScreen)
            }
        )
    }
}


