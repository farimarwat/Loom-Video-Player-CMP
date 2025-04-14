package com.farimarwat.loom.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize

@Composable
expect fun getPlatformWindowSize(): DpSize

expect fun Long.formatAsTime(): String
