package com.farimarwat.loom.core.media

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    onToggleFullScreen:(fullScreen:Boolean)->Unit
) {
   Box(
       modifier = Modifier
           .then(modifier)
   ){
       UIKitView(
           factory = {
               val view = UIView()
               val playerLayer = AVPlayerLayer()
               playerLayer.player = playerState.player
               playerLayer.videoGravity = AVLayerVideoGravityResizeAspectFill
               view.layer.addSublayer(playerLayer)
               playerLayer.frame = view.bounds
               view.backgroundColor = UIColor.blackColor
               view
           },
           modifier = modifier
       )
       MediaControls(
           state = playerState
       )
   }
}


