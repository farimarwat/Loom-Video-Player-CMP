package com.farimarwat.loom.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
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