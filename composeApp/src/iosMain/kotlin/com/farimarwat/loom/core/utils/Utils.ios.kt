package com.farimarwat.loom.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.Foundation.NSNumber
import platform.Foundation.setValue
import platform.UIKit.UIDevice
import platform.UIKit.UIInterfaceOrientationLandscapeRight
import platform.UIKit.UIInterfaceOrientationPortrait
import platform.UIKit.UIScreen



@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun getPlatformWindowSize(): DpSize {
    val screenBounds = UIScreen.mainScreen.bounds.useContents {
        val scale = UIScreen.mainScreen.scale
        val widthDp = (size.width / scale).dp
        val heightDp = (size.height / scale).dp
        DpSize(widthDp, heightDp)
    }
    return screenBounds
}

actual fun Long.formatAsTime(): String {
    val totalSeconds = this / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    fun Long.padTwoDigits() = this.toString().padStart(2, '0')

    return if (hours > 0) {
        "${hours.padTwoDigits()}:${minutes.padTwoDigits()}:${seconds.padTwoDigits()}"
    } else {
        "${minutes.padTwoDigits()}:${seconds.padTwoDigits()}"
    }
}

