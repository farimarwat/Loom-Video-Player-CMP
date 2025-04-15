package com.farimarwat.loom.utils

import androidx.compose.runtime.Composable

expect class ScreenUtils{
    fun isLandScape():Boolean
    fun setLandScape(isLandScape:Boolean)
}

@Composable
expect fun rememberScreenUtilsState():ScreenUtils