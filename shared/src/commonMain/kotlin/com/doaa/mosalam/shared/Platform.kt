package com.doaa.mosalam.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform


