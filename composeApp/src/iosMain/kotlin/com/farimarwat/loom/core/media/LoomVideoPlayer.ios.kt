package com.farimarwat.loom.core.media

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.loom.core.ui.MediaControls
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue
import kotlinx.cinterop.useContents
import kotlinx.coroutines.delay
import platform.AVFoundation.AVLayerVideoGravityResizeAspect
import platform.AVFoundation.AVLayerVideoGravityResizeAspectFill
import platform.AVFoundation.AVPlayerLayer
import platform.AVFoundation.play
import platform.AVKit.AVPlayerViewController
import platform.CoreGraphics.CGRectMake
import platform.CoreGraphics.CGRectZero
import platform.UIKit.NSLayoutConstraint
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
    ) {
        val playbackLayer = remember{AVPlayerLayer()}.apply {
            player = playerState.player
            videoGravity = AVLayerVideoGravityResizeAspect
        }

        UIKitView(
            factory = {
                val container = object : UIView(CGRectZero.readValue()) {
                    override fun layoutSubviews() {
                        super.layoutSubviews()
                        playbackLayer.frame = bounds
                    }
                }
                container.layer.addSublayer(playbackLayer)
                container
            },
            update = {
                fullScreen = !fullScreen
                fullScreen = !fullScreen
            },
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
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



