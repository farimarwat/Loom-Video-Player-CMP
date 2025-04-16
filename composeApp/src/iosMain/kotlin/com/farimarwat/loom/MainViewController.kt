package com.farimarwat.loom

import androidx.compose.ui.window.ComposeUIViewController
import com.farimarwat.loom.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}