package com.farimarwat.loom

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform