package com.farimarwat.loom.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.Foundation.NSNumber
import platform.Foundation.numberWithInt
import platform.Foundation.setValue
import platform.UIKit.UIDevice
import platform.UIKit.UIInterfaceOrientationLandscapeRight
import platform.UIKit.UIInterfaceOrientationPortrait
import platform.UIKit.UIScreen

actual class ScreenUtils {
    @OptIn(ExperimentalForeignApi::class)
    actual fun isLandScape(): Boolean {
        val screenBounds = UIScreen.mainScreen.bounds.useContents {
            size.width > size.height
        }
        return screenBounds
    }

    actual fun setLandScape(isLandScape: Boolean) {
        val device = UIDevice.currentDevice
        val orientation = if (isLandScape) UIInterfaceOrientationLandscapeRight else UIInterfaceOrientationPortrait
        device.setValue(NSNumber.numberWithInt(orientation.toInt()), forKey = "orientation")
    }
}

@Composable
actual fun rememberScreenUtilsState() = remember { ScreenUtils() }