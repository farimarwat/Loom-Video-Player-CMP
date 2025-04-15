package com.farimarwat.loom.utils

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class ScreenUtils(
    private val context: Context
) {
    actual fun isLandScape(): Boolean {
        return context.resources.configuration.orientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    }

    actual fun setLandScape(isLandScape: Boolean) {
        val activity = (context as? Activity) ?: return
        activity.runOnUiThread {
            activity.requestedOrientation = if(isLandScape){
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            } else {
                ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
            }
        }
    }
}

@Composable
actual fun rememberScreenUtilsState(): ScreenUtils {
    val context = LocalContext.current
    return remember { ScreenUtils(context) }
}